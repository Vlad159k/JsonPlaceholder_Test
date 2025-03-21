package steps;

import io.restassured.response.Response;
import org.example.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PostAssertionSteps {
    private static final Logger logger = LoggerFactory.getLogger(PostAssertionSteps.class);

    public void assertStatusCode(Response response, int expectedStatusCode) {
        logger.info("Asserting status code. Expected: {}, Actual: {}", expectedStatusCode, response.getStatusCode());
        assertEquals(expectedStatusCode, response.getStatusCode(), "Unexpected status code");
    }

    public void assertPostCreated(Response response, boolean isSuccess) {
        if (isSuccess) {
            Post post = response.as(Post.class);
            assertNotNull(post.getId(), "Post ID should not be null");
            logger.info("Post created successfully with ID: {}", post.getId());
        } else {
            logger.warn("Post creation failed as expected");
        }
    }

    public void assertPostFetched(Response response, int expectedPostId, boolean isSuccess) {
        if (isSuccess) {
            Post post = response.as(Post.class);
            assertEquals(expectedPostId, post.getId(), "Post ID does not match");
            logger.info("Post fetched successfully: {}", post);
        } else {
            logger.warn("Post fetching failed as expected");
        }
    }

    public void assertPostUpdated(Response response, String expectedTitle, boolean isSuccess) {
        if (isSuccess) {
            Post post = response.as(Post.class);
            assertEquals(expectedTitle, post.getTitle(), "Post title was not updated");
            logger.info("Post updated successfully: {}", post);
        } else {
            logger.warn("Post updating failed as expected");
        }
    }

    public void assertPostEquals(Post actualPost, Post expectedPost, boolean isSuccess) {
        if (isSuccess) {
            logger.info("Asserting that the created post matches the expected post.");
            assertEquals(expectedPost, actualPost, "The actual post does not match the expected post");
        } else {
            logger.info("Asserting that the post is not created, response body should be empty.");
            assertEquals(0, actualPost.getId(), "The id should be 0 for a failed operation.");
            assertTrue(actualPost.getTitle().isEmpty(), "The title should be empty for a failed operation.");
            assertTrue(actualPost.getBody().isEmpty(), "The body should be empty for a failed operation.");
            assertEquals(0, actualPost.getUserId(), "The userId should be 0 for a failed operation.");
        }
    }

    public void assertPostsCount(Response response, int expectedPostsCount) {
        var posts = response.jsonPath().getList("", Post.class);
        logger.info("Asserting the number of retrieved posts. Expected: {}, Actual: {}", expectedPostsCount, posts.size());
        assertEquals(expectedPostsCount, posts.size(),
                "Failed to retrieve all posts: expected " + expectedPostsCount + " posts, but got " + posts.size());
    }

    public void assertTopWordsNotEmpty(List<Map.Entry<String, Integer>> topWords) {
        logger.info("Asserting that the list of top words is not empty. Actual size: {}", topWords.size());
        assertFalse(topWords.isEmpty(), "The list of top words should not be empty");
    }
}
