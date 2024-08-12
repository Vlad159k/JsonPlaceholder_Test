import org.example.client.JsonPlaceholderClient;
import org.example.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class WordFrequencyTests {

    private PostService postService;

    @BeforeEach
    public void setup() {
        JsonPlaceholderClient client = new JsonPlaceholderClient("https://jsonplaceholder.typicode.com");
        postService = new PostService(client);
    }

    @Test
    public void findMostCommonWordsTest() {
        List<Map.Entry<String, Integer>> topWords = postService.findMostCommonWords();

        assertFalse(topWords.isEmpty());
        System.out.println("Top 10 words:");
        topWords.forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
    }
}
