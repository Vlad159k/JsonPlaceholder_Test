import assertions.PostAssertions;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.example.model.Post;
import org.junit.jupiter.api.*;
import com.google.inject.*;
import org.example.di.ApiClientModule;
import org.example.steps.PostSteps;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.List;
import java.util.stream.Stream;

public class PostTests {
    private PostSteps postSteps;
    private PostAssertions postAssertions;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new ApiClientModule());
        postSteps = injector.getInstance(PostSteps.class);
        postAssertions = new PostAssertions();
        RestAssured.filters(new AllureRestAssured());
    }

    static Stream<Arguments> providePostDataForCreation() {
        return Stream.of(
                // Positive
                Arguments.of(101, "First Post", "This is the body of the first post", 1, 201, true),
                Arguments.of(102, "Second Post", "This is the body of the second post", 2, 201, true),

                // Negative
                Arguments.of(-1, "Invalid Title", "Invalid Body", -1, 400, false),
                Arguments.of(103, "Title", "Body", 9999, 400, false)
        );
    }

    @ParameterizedTest
    @MethodSource("providePostDataForCreation")
    @DisplayName("Create Post Test (Positive & Negative)")
    public void createPostTest(int id, String title, String body, int userId, int expectedStatusCode, boolean isPositive) {
        // Act
        var response = postSteps.createPost(id, title, body, userId);

        // Assert
        postAssertions.assertStatusCode(response, expectedStatusCode);
        postAssertions.assertPostCreated(response, isPositive);
    }

    static Stream<Arguments> providePostDataForGet() {
        return Stream.of(
                // Positive
                Arguments.of(1, 200, true),

                // Negative
                Arguments.of(9999, 404, false)
        );
    }

    @ParameterizedTest
    @MethodSource("providePostDataForGet")
    @DisplayName("Get Post Test (Positive & Negative)")
    public void getPostTest(int postId, int expectedStatusCode, boolean isPositive) {
        // Act
        var response = postSteps.getPost(postId);

        // Assert
        postAssertions.assertStatusCode(response, expectedStatusCode);
        postAssertions.assertPostFetched(response, postId, isPositive);
    }

    @Test
    @DisplayName("Get All Posts Test")
    public void getAllPostsTest() {
        // Arrange
        int expectedPostsCount = 100;

        // Act
        List<Post> posts = postSteps.getAllPosts();

        // Assert
        postAssertions.assertPostsCount(posts, expectedPostsCount);
    }

    static Stream<Arguments> providePostDataForUpdate() {
        return Stream.of(
                // Positive
                Arguments.of(1, "Updated Title", "Updated Body", 1, 200, true),

                // Negative
                Arguments.of(9999, "Invalid Title", "Invalid Body", -1, 404, false)
        );
    }

    @ParameterizedTest
    @MethodSource("providePostDataForUpdate")
    @DisplayName("Update Post Test (Positive & Negative)")
    public void updatePostTest(int postId, String updatedTitle, String updatedBody, int userId, int expectedStatusCode, boolean isPositive) {
        // Act
        var response = postSteps.updatePost(postId, updatedTitle, updatedBody, userId);

        // Assert
        postAssertions.assertStatusCode(response, expectedStatusCode);
        postAssertions.assertPostUpdated(response, updatedTitle, isPositive);
    }

    static Stream<Arguments> providePostDataForDeletion() {
        return Stream.of(
                // Positive
                Arguments.of(1, 200),

                // Negative
                Arguments.of(9999, 404)
        );
    }

    @ParameterizedTest
    @MethodSource("providePostDataForDeletion")
    @DisplayName("Delete Post Test (Positive & Negative)")
    public void deletePostTest(int postId, int expectedStatusCode) {
        // Act
        var response = postSteps.deletePost(postId);

        // Assert
        postAssertions.assertStatusCode(response, expectedStatusCode);
    }
}
