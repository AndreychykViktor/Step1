package org.exemple.servlets;

import org.exemple.dao.LikeDaoImpl;
import org.exemple.dao.UserDaoImpl;
import org.exemple.service.ShowService;
import org.exemple.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MainServlet extends HttpServlet {
    private final UserDaoImpl userDao;
    private final LikeDaoImpl likeDao;

    public MainServlet(UserDaoImpl userDao, LikeDaoImpl likeDao) {
        this.userDao = userDao;
        this.likeDao = likeDao;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");
        if ("like".equals(action)) {
            User chosenUser = (User) req.getSession().getAttribute("chosenUser");
            if (chosenUser != null) {
                var clientId = Stream.of(req.getCookies()).filter(cookie -> cookie.getName().equals("J_ID")).findFirst().map(cookie -> Integer.parseInt(cookie.getValue())).orElse(null);
                likeDao.create(clientId, chosenUser.getId());
            } else {
                resp.getWriter().println("<h1>Нет выбранного пользователя</h1>");
            }

            resp.sendRedirect("/main");
        } else if ("dislike".equals(action)) {
            resp.sendRedirect("/main");
        } else {
            resp.getWriter().println("<h1>Неизвестное действие</h1>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        var clientId = Stream.of(req.getCookies()).filter(cookie -> cookie.getName().equals("J_ID")).findFirst().orElse(null);

        if (clientId == null) {
            resp.sendRedirect("/login?registered=true");
            return;
        }

        var showService = new ShowService(userDao, likeDao, Integer.parseInt(clientId.getValue()));

        Optional<User> userOpt = showService.getNextUser();

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            req.getSession().setAttribute("chosenUser", user);

            resp.getWriter().println("<h1>Пользователь:</h1>");
            resp.getWriter().println("<p>Имя: " + user.getName() + "</p>");
            resp.getWriter().println("<p>Возраст: " + user.getAge() + "</p>");
            resp.getWriter().println("<p>Био: " + user.getBio() + "</p>");

            resp.getWriter().println(
                    "<form method='post' action='/main'>" +
                    "<button type='submit' name='action' value='like'>Like</button>" +
                    "<button type='submit' name='action' value='dislike'>Dislike</button>" +
                    "</form>"
            );
        } else {
            resp.getWriter().println("<h1>Нет доступных пользователей</h1>");
        }
    }
}