import com.google.inject.Guice;
import com.google.inject.Injector;
import io.qameta.allure.restassured.AllureRestAssured;
import org.example.di.ApiClientModule;
import org.example.model.Post;
import org.example.steps.PostSteps;
import org.example.utils.WordFrequencyAnalyzerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class WordFrequencyTests {
    private PostSteps postSteps;
    private WordFrequencyAnalyzerUtil wordFrequencyAnalyzerUtil;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new ApiClientModule());
        postSteps = injector.getInstance(PostSteps.class);
        wordFrequencyAnalyzerUtil = injector.getInstance(WordFrequencyAnalyzerUtil.class);
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    @DisplayName("Find Most Common Words Test")
    public void findMostCommonWordsTest() {
        // Act
        var posts = postSteps.getAllPosts();
        List<Map.Entry<String, Integer>> topWords = wordFrequencyAnalyzerUtil.findMostCommonWords(posts);

        // Assert
        assertFalse(topWords.isEmpty(), "The list of top words should not be empty");

        // Act
        wordFrequencyAnalyzerUtil.logTop10Words(posts);
    }
}
