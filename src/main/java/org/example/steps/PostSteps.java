package org.example.steps;

import com.google.inject.Inject;
import io.restassured.response.Response;
import org.example.builder.PostBuilder;
import org.example.client.JsonPlaceholderClient;
import org.example.model.Post;
import org.slf4j.Logger;
import java.util.List;

public class PostSteps {
    private final Logger logger;
    private final JsonPlaceholderClient client;

    @Inject
    public PostSteps(Logger logger, JsonPlaceholderClient client) {
        this.logger = logger;
        this.client = client;
    }

    public Response createPost(int id, String title, String body, int userId) {
        logger.info("Creating a new post with id={}, title={}, userId={}", id, title, userId);
         Post post = new PostBuilder()
                .witId(id)
                .withTitle(title)
                .withBody(body)
                .withUserId(userId)
                .build();
        return client.createPost(post);
    }

    public Response getPost(int postId) {
        logger.info("Getting post with id={}", postId);
        return client.getPost(postId);
    }

    public Response updatePost(int postId, String title, String body, int userId) {
        logger.info("Updating post with id={}, title={}, userId={}", postId, title, userId);
        Post post = new PostBuilder()
                .witId(postId)
                .withTitle(title)
                .withBody(body)
                .withUserId(userId)
                .build();
        return client.updatePost(postId, post);
    }

    public Response deletePost(int postId) {
        logger.info("Deleting post with id={}", postId);
        return client.deletePost(postId);
    }

    public Response getAllPosts() {
        logger.info("Retrieving all posts");
        return client.getAllPosts();
    }
}
