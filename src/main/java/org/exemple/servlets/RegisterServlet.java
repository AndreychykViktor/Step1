package org.exemple.servlets;

import org.exemple.service.UserRegService;
import org.exemple.dao.UserDaoImpl;
import org.exemple.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.exemple.utils.Passwords;
import org.exemple.utils.Tokens;


public class RegisterServlet extends HttpServlet {
    private final Passwords passwords;
    private final Tokens tokens;
    private final UserRegService userRegService;

    public RegisterServlet(Passwords passwords, Tokens tokens, UserRegService userRegService) {
        this.passwords = passwords;
        this.tokens = tokens;
        this.userRegService = userRegService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("templates/register.html");
        if (in == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        resp.setContentType("text/html;charset=UTF-8");
        String html = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        resp.getWriter().write(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String mail = req.getParameter("email");
        String name = req.getParameter("name");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String bio = req.getParameter("bio");

        try {
            User user = userRegService.register(login, password, mail, name, age, bio);
            resp.sendRedirect("/login?registered=true");
        }catch(IllegalArgumentException e) {
            resp.sendRedirect("/register?error=exists");
        }
    }
}