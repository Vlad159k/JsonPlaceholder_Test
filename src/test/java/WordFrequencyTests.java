import base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

public class WordFrequencyTests extends TestBase {

    @Test
    @DisplayName("Find Most Common Words Test")
    public void findMostCommonWordsTest() {
        // Act
        var posts = postSteps.getAllPosts();
        List<Map.Entry<String, Integer>> topWords = postSteps.findMostCommonWords(posts);

        // Assert
        postAssertionSteps.assertTopWordsNotEmpty(topWords);

        // Act
        postSteps.logTop10Words(posts);
    }
}
