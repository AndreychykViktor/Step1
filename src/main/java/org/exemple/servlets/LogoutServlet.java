package org.exemple.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.exemple.utils.Token.COOKIE_NAME;

public class LogoutServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var cookie = new Cookie(COOKIE_NAME, "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.sendRedirect("/login");
    }

}
