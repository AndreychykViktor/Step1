package org.exemple.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletCookieSet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest rq,
            HttpServletResponse rs) throws ServletException, IOException {

        Cookie c = new Cookie("J_ID", "123");
        rs.addCookie(c);

        try (PrintWriter w = rs.getWriter()) {
            w.write("Setting cookie from Java");
        }
    }

}
