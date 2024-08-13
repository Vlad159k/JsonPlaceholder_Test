package assertions;

import io.restassured.response.Response;
import org.example.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PostAssertions {
    private static final Logger logger = LoggerFactory.getLogger(PostAssertions.class);

    public void assertStatusCode(Response response, int expectedStatusCode) {
        logger.info("Asserting status code. Expected: {}, Actual: {}", expectedStatusCode, response.getStatusCode());
        assertEquals(expectedStatusCode, response.getStatusCode(), "Unexpected status code");
    }

    public void assertPostCreated(Response response, boolean isPositive) {
        if (isPositive) {
            Post post = response.as(Post.class);
            assertNotNull(post.getId(), "Post ID should not be null");
            logger.info("Post created successfully with ID: {}", post.getId());
        } else {
            logger.warn("Post creation failed as expected");
        }
    }

    public void assertPostFetched(Response response, int expectedPostId, boolean isPositive) {
        if (isPositive) {
            Post post = response.as(Post.class);
            assertEquals(expectedPostId, post.getId(), "Post ID does not match");
            logger.info("Post fetched successfully: {}", post);
        } else {
            logger.warn("Post fetching failed as expected");
        }
    }

    public void assertPostUpdated(Response response, String expectedTitle, boolean isPositive) {
        if (isPositive) {
            Post post = response.as(Post.class);
            assertEquals(expectedTitle, post.getTitle(), "Post title was not updated");
            logger.info("Post updated successfully: {}", post);
        } else {
            logger.warn("Post updating failed as expected");
        }
    }

    public void assertPostsCount(List<Post> posts, int expectedPostsCount) {
        logger.info("Asserting the number of retrieved posts. Expected: {}, Actual: {}", expectedPostsCount, posts.size());
        assertEquals(expectedPostsCount, posts.size(),
                "Failed to retrieve all posts: expected " + expectedPostsCount + " posts, but got " + posts.size());
    }
}
