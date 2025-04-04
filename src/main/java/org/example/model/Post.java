package org.example.model;

import java.util.Objects;

public class Post {
    private int id;
    private String title;
    private String body;
    private int userId;

    public Post() {}

    public Post(int id, String title, String body, int userId) {
        this.id = id;
        this.title = title != null ? title : "";
        this.body = body != null ? body : "";
        this.userId = userId;
    }

    public int getId() { return id; }

    public void setId(int id) {
        if (this.id == 0) {
            this.id = id;
        } else {
            throw new IllegalStateException("ID can only be set when it is 0.");
        }
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() { return body; }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                userId == post.userId &&
                Objects.equals(title, post.title) &&
                Objects.equals(body, post.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, userId);
    }
}
