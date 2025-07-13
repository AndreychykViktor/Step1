package org.exemple;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.exemple.dao.LikeDaoImpl;
import org.exemple.dao.UserDaoImpl;
import org.exemple.service.ShowService;
import org.exemple.service.UserRegService;
import org.exemple.servlets.*;
import org.exemple.utils.Passwords;
import org.exemple.utils.Tokens;
import org.exemple.db.HistoryPg;
import org.exemple.db.Db;


public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.addServlet(HelloServlet.class, "/users");


        Passwords passwords = new Passwords();
        Tokens tokens = new Tokens();

        UserDaoImpl userDao = new UserDaoImpl();
        LikeDaoImpl likeDao = new LikeDaoImpl();



        LoginServlet loginServlet = new LoginServlet(passwords, tokens);
        handler.addServlet(new ServletHolder(loginServlet), "/login");

        LogoutServlet logoutServlet = new LogoutServlet(passwords, tokens);
        handler.addServlet(new ServletHolder(logoutServlet), "/logout");

        UserRegService userRegService = new UserRegService(new UserDaoImpl());

        RegisterServlet registerServlet = new RegisterServlet(passwords, tokens, userRegService);
        handler.addServlet(new ServletHolder(registerServlet), "/register");


        MainServlet mainServlet = new MainServlet(userDao, likeDao);
        handler.addServlet(new ServletHolder(mainServlet), "/main");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
