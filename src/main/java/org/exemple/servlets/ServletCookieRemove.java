package org.exemple.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletCookieRemove extends HttpServlet {

  @Override
  protected void doGet(
      HttpServletRequest rq,
      HttpServletResponse rs) throws ServletException, IOException {

    Cookie c = new Cookie("J_ID", "");
    c.setMaxAge(0);
    rs.addCookie(c);

    try (PrintWriter w = rs.getWriter()) {
      w.write("Removing cookie from Java");
    }
  }

}
