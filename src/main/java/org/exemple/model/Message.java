package org.exemple.model;

import java.sql.Timestamp;

public class Message {
    public final int id;
    public final String chatId;
    public final int senderId;
    public final int receiverId;
    public final String text;
    public final Timestamp timestamp;

    public Message(int id, String chatId, int senderId, int receiverId, String text, Timestamp timestamp) {
        this.id = id;
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.timestamp = timestamp;
    }
}
