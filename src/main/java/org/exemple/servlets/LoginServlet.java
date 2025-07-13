package org.exemple.servlets;

import javax.servlet.http.Cookie;
import javax.servlet.ServletException;

import org.exemple.dao.UserDaoImpl;
import org.exemple.model.User;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.exemple.utils.Passwords;
import org.exemple.utils.Tokens;

public class LoginServlet extends HttpServlet {

    private final Passwords passwords;
    private final Tokens tokens;

    public LoginServlet(Passwords passwords, Tokens tokens) {
        this.passwords = passwords;
        this.tokens = tokens;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("templates/login.html");
        if (in == null) {
            System.out.println("login.html не найден через classloader");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } else {
            System.out.println("login.html найден через classloader");
        }
        resp.setContentType("text/html;charset=UTF-8");
        String html = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        resp.getWriter().write(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        System.out.println("Login: " + login + ", Password: " + password);

        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.findByLogin(login);

        if (user == null) {
            // Нет пользователя — редирект на регистрацию
            resp.sendRedirect("/register");
            return;
        }

        if (user.getPassword().equals(password)) {
            // Пароль верный — вход и редирект на главную
            Cookie cookie = new Cookie("J_ID", "" + user.getId());
            cookie.setMaxAge(60 * 60 * 24 * 30);
            resp.addCookie(cookie);
            resp.sendRedirect("/main");
        } else {
            // Пароль неверный — показываем форму логина снова
            resp.sendRedirect("/login?error=wrong_password");
        }
    }
}