// MainServlet.java
package org.exemple.servlets;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import org.exemple.model.User;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h1>Добро пожаловать!</h1>");

    }
}