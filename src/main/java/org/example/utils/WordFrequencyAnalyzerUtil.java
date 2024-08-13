package org.example.utils;

import io.restassured.response.Response;
import org.example.model.Post;
import org.slf4j.Logger;
import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyAnalyzerUtil {
    private final Logger logger;

    public WordFrequencyAnalyzerUtil(Logger logger) {
        this.logger = logger;
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
