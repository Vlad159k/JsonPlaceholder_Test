package org.example.builder;

import org.example.model.Post;

public class PostBuilder {
    private int id = 0;
    private String title = "";
    private String body = "";
    private int userId = 0;

    public PostBuilder witId(int id) {
        this.id = id;
        return this;
    }

    public PostBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PostBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public PostBuilder withUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public Post build() {
        return new Post(id, title, body, userId);
    }
}
