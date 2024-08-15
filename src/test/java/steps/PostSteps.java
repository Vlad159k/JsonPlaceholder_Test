package steps;

import com.google.inject.Inject;
import io.restassured.response.Response;
import org.example.client.JsonPlaceholderClient;
import org.example.model.Post;
import org.slf4j.Logger;
import java.util.*;
import java.util.stream.Collectors;

public class PostSteps {
    private final Logger logger;
    private final JsonPlaceholderClient client;

    @Inject
    public PostSteps(Logger logger, JsonPlaceholderClient client) {
        this.logger = logger;
        this.client = client;
    }

    public Response createPost(Post post) {
        logger.info("Creating a new post with body={}, title={}, userId={}", post.getBody(), post.getTitle(), post.getUserId());
        return client.createPost(post);
    }

    public Response getPost(int postId) {
        logger.info("Getting post with id={}", postId);
        return client.getPost(postId);
    }

    public Response updatePost(int postId, Post post) {
        logger.info("Updating post with id={}, title={}, userId={}", post.getId(), post.getTitle(), post.getUserId());
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

    public List<Map.Entry<String, Integer>> findMostCommonWords(Response response) {
        logger.info("Finding most common words in the body of all posts");
        var allPosts = response.jsonPath().getList("", Post.class);
        Map<String, Integer> wordCount = new HashMap<>();

        for (Post post : allPosts) {
            String[] words = post.getBody().toLowerCase().split("\\W+");
            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        return wordCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public void logTop10Words(Response response) {
        List<Map.Entry<String, Integer>> topWords = findMostCommonWords(response);
        logger.info("Top 10 most common words:");
        int rank = 1;
        for (Map.Entry<String, Integer> entry : topWords) {
            logger.info("{}. {} - {}", rank, entry.getKey(), entry.getValue());
            rank++;
        }
    }
}
