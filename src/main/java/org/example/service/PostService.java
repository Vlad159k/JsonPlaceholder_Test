package org.example.service;

import org.example.client.JsonPlaceholderClient;
import org.example.model.Post;
import java.util.*;
import java.util.stream.Collectors;

public class PostService {
    private final JsonPlaceholderClient client;

    public PostService(JsonPlaceholderClient client) {
        this.client = client;
    }

    public List<Post> getAllPosts() {
        return client.getAllPosts();
    }

    public List<Map.Entry<String, Integer>> findMostCommonWords() {
        List<Post> posts = getAllPosts();
        Map<String, Integer> wordCount = new HashMap<>();

        posts.forEach(post -> {
            String[] words = post.getBody().split("\\W+");
            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        });

        return wordCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}
