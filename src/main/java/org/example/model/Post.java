package org.example.model;

public class Post {
    private int id;
    private String title;
    private String body;
    private int userId;

    public Post(int id, String title, String body, int userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public int getId() { return id; }

    public String getTitle() { return title; }

    public String getBody() { return body; }

    public int getUserId() { return userId; }
}
