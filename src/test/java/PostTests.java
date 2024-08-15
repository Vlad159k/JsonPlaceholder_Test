import base.TestBase;
import org.example.builder.PostBuilder;
import org.example.model.Post;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;

public class PostTests extends TestBase {

    @ParameterizedTest
    @MethodSource("providePostDataForCreation")
    @DisplayName("Create Post Test (Positive & Negative)")
    public void createPostTest(String title, String body, int userId, int expectedStatusCode, boolean isPositive) {
        // Arrange
        Post post = new PostBuilder()
                .withTitle(title)
                .withBody(body)
                .withUserId(userId)
                .build();

        // Act
        var response = postSteps.createPost(post);
        Post responsePost = response.as(Post.class);
        post.setId(responsePost.getId());

        // Assert
        postAssertionSteps.assertStatusCode(response, expectedStatusCode);
        postAssertionSteps.assertPostCreated(response, isPositive);
        postAssertionSteps.assertPostEquals(responsePost, post, isPositive);
    }

    @ParameterizedTest
    @MethodSource("providePostDataForGet")
    @DisplayName("Get Post Test (Positive & Negative)")
    public void getPostTest(int postId, int expectedStatusCode, boolean isPositive) {
        // Act
        var response = postSteps.getPost(postId);

        // Assert
        postAssertionSteps.assertStatusCode(response, expectedStatusCode);
        postAssertionSteps.assertPostFetched(response, postId, isPositive);
    }

    @Test
    @DisplayName("Get All Posts Test")
    public void getAllPostsTest() {
        // Arrange
        int expectedPostsCount = 100;

        // Act
        var posts = postSteps.getAllPosts();

        // Assert
        postAssertionSteps.assertPostsCount(posts, expectedPostsCount);
    }

    @ParameterizedTest
    @MethodSource("providePostDataForUpdate")
    @DisplayName("Update Post Test (Positive & Negative)")
    public void updatePostTest(int postId, String updatedTitle, String updatedBody, int userId, int expectedStatusCode, boolean isSuccess) {
        // Arrange
        Post post = new PostBuilder()
                .witId(postId)
                .withTitle(updatedTitle)
                .withBody(updatedBody)
                .withUserId(userId)
                .build();

        // Act
        var response = postSteps.updatePost(postId, post);
        Post responsePost = response.as(Post.class);

        // Assert
        postAssertionSteps.assertStatusCode(response, expectedStatusCode);
        postAssertionSteps.assertPostUpdated(response, updatedTitle, isSuccess);
        postAssertionSteps.assertPostEquals(responsePost, post, isSuccess);
    }

    @ParameterizedTest
    @MethodSource("providePostDataForDeletion")
    @DisplayName("Delete Post Test (Positive & Negative)")
    public void deletePostTest(int postId, int expectedStatusCode) {
        // Act
        var response = postSteps.deletePost(postId);

        // Assert
        postAssertionSteps.assertStatusCode(response, expectedStatusCode);
    }

    static Stream<Arguments> providePostDataForCreation() {
        return Stream.of(
                Arguments.of("First Post", "This is the body of the first post", 1, 201, true),
                Arguments.of("First Post", "This is the body of the first post", 100, 201, true)
        );
    }

    static Stream<Arguments> providePostDataForGet() {
        return Stream.of(
                Arguments.of(1, 200, true),
                Arguments.of(-1, 404, false)
        );
    }

    static Stream<Arguments> providePostDataForUpdate() {
        return Stream.of(
                Arguments.of(1, "Updated Title", "Updated Body", 1, 200, true),
                Arguments.of(100, "Updated Title", "Updated Body", 100, 200, true)
        );
    }

    static Stream<Arguments> providePostDataForDeletion() {
        return Stream.of(
                Arguments.of(1, 200),
                Arguments.of(101, 200)
        );
    }
}
