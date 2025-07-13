package org.exemple.model;

public class Like {
    private final int id;
    private final int clientId;
    private final int userId;

    public Like(int id, int clientId, int userId) {
        this.id = id;
        this.clientId = clientId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getClientId() {
        return clientId;
    }

}
