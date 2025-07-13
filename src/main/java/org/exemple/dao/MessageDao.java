package org.exemple.dao;

import org.exemple.db.Db;
import org.exemple.model.Message;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MessageDao {
    public Collection<Message> findAllByChatId(String chatId) {
        List<Message> messages = new ArrayList<>();
        try (var connection = Db.conn()) {
            var ps = connection.prepareStatement("SELECT * FROM message WHERE chat_id = ?");
            ps.setString(1, chatId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String chatIdFromDb = rs.getString("chat_id");
                int senderId = rs.getInt("sender_id");
                int receiverId = rs.getInt("receiver_id");
                String text = rs.getString("text");
                Timestamp timestamp = rs.getTimestamp("date");

                Message message = new Message(id, chatIdFromDb, senderId, receiverId, text, timestamp);
                messages.add(message);
            }
        } catch (SQLException e) {
            // ignored
        }
        return messages;
    }

    public void sendMessage(Message message) {
        try (var connection = Db.conn()) {
            var ps = connection.prepareStatement("INSERT INTO message (chat_id, sender_id, receiver_id, text, date) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, message.chatId);
            ps.setInt(2, message.senderId);

            ps.setInt(3, message.receiverId);
            ps.setString(4, message.text);
            ps.setTimestamp(5, message.timestamp);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // ignored
        }
    }
}
