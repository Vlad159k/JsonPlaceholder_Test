package org.example.model;

public class Post {
    private int id = 0;
    private String title = "";
    private String body = "";
    private int userId = 0;

    public Post() {}

    public Post(int id, String title, String body, int userId) {
        this.id = id;
        this.title = title != null ? title : "";
        this.body = body != null ? body : "";
        this.userId = userId;
    }

    public int getId() { return id; }

    public String getTitle() { return title; }

    public String getBody() { return body; }

    public int getUserId() { return userId; }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                '}';
    }
}
