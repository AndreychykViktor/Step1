package org.exemple.servlets;

import org.exemple.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LikedServlet extends HttpServlet {
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


    }
}
