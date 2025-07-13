package org.exemple.model;

public class Like {
    private final int id;
    private final int userId;
    private final int postId;

    public Like(int id, int userId, int postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

}
