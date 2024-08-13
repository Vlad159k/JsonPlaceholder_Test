package org.example.service;

import org.example.model.Post;
import java.util.*;
import java.util.stream.Collectors;

public class PostService {
    public static List<Map.Entry<String, Integer>> findMostCommonWords(List<Post> posts) {
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
