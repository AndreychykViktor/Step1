package org.exemple.servlets;

import org.exemple.dao.LikeDaoImpl;
import org.exemple.dao.MessageDao;
import org.exemple.dao.UserDao;
import org.exemple.model.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;

public class MessagesServlet extends HttpServlet {

    private final LikeDaoImpl likeDao;
    private final MessageDao messageDao;
    private final UserDao userDao;

    public MessagesServlet(LikeDaoImpl likeDao, MessageDao messageDao, UserDao userDao) {
        this.likeDao = likeDao;
        this.messageDao = messageDao;
        this.userDao = userDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String chatId = req.getParameter("id");

        var firstUser = Integer.parseInt(chatId.split("-")[0]);
        var secondUser = Integer.parseInt(chatId.split("-")[1]);
        if (!likeDao.exists(firstUser, secondUser)) {
            resp.sendRedirect("/main");
            return;
        }
        var messages = messageDao.findAllByChatId(chatId);

        resp.setContentType("text/html;charset=UTF-8");
        StringBuilder messagesHtml = new StringBuilder("<h1>Messages</h1><ul>");
        messages.forEach(message -> {
            String senderName = userDao.findById(message.senderId).getName();
            messagesHtml.append("<li>")
                    .append("<strong>").append(senderName).append(":</strong> ")
                    .append(message.text)
                    .append(" <em>(").append(message.timestamp).append(")</em>")
                    .append("</li>");
        });

        messagesHtml.append("</ul>")
                .append("<form method=\"post\" action=\"/message\">")
                .append("<input type=\"hidden\" name=\"chatId\" value=\"").append(chatId).append("\">")
                .append("<textarea name=\"text\" required></textarea>")
                .append("<button type=\"submit\">Send</button>")
                .append("</form>");

        resp.getWriter().write(messagesHtml.toString());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chatId = req.getParameter("chatId");
        String text = req.getParameter("text");

        if (chatId != null && text != null && !text.trim().isEmpty()) {
            int senderId = Integer.parseInt(Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("J_ID")).findFirst().orElseThrow().getValue());
            int receiverId = Integer.parseInt(chatId.split("-")[1]);

            var message = new Message(0, chatId, senderId, receiverId, text, new Timestamp(System.currentTimeMillis()));
            messageDao.sendMessage(message);
        }

        resp.sendRedirect("/message?id=" + chatId);
    }
}
