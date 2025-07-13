package org.exemple.servlets;

import org.exemple.dao.LikeDaoImpl;
import org.exemple.dao.UserDaoImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class LikedServlet extends HttpServlet {

    private final LikeDaoImpl likeDao;
    private final UserDaoImpl userDao;

    public LikedServlet(LikeDaoImpl likeDao, UserDaoImpl userDao) {
        this.likeDao = likeDao;
        this.userDao = userDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        var clientId = req.getCookies() != null ?
                Stream.of(req.getCookies()).filter(cookie -> cookie.getName().equals("J_ID")).findFirst().orElse(null) : null;
        if (clientId == null) {
            resp.sendRedirect("/login?registered=true");
            return;
        }
        int userId = Integer.parseInt(clientId.getValue());
        StringBuilder likedUsersHtml = new StringBuilder("<h1>Liked Users</h1><ul>");
        likeDao.findAllMutualLikes(userId)
                .stream().map(userDao::findById).forEach(user -> {
                    int userId2 = user.getId();

                    likedUsersHtml.append("<li>")
                            .append("<a href=\"/message?id=").append(Math.min(userId, userId2)).append("-").append(Math.max(userId, userId2))
                            .append("\">")
                            .append(user.getName())
                            .append("</a>")
                            .append("</li>");
                });
        resp.getOutputStream().print(likedUsersHtml.toString());
    }
}
