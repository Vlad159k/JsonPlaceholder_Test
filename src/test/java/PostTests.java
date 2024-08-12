import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.example.builder.PostBuilder;
import org.example.client.JsonPlaceholderClient;
import org.example.model.Post;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PostTests {
    private JsonPlaceholderClient client;

    @BeforeEach
    public void setup() {
        client = new JsonPlaceholderClient("https://jsonplaceholder.typicode.com");
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void createPostTest() {
        // Arrange
        Post post = new PostBuilder()
                .witId(101)
                .withTitle("First post")
                .withBody("This is the body of first post")
                .withUserId(123)
                .build();

        // Act
        var response = client.createPost(post);

        // Assert
        assertEquals(201, response.getStatusCode(),
                "Post creation failed: expected status code 201, but got " + response.getStatusCode());
        assertNotNull(response.jsonPath().getString("id"),
                "Post creation failed: 'id' should not be null, but it is.");
    }

    @Test
    public void getPostTest() {
        // Arrange
        int postId = 1;

        // Act
        var response = client.getPost(postId);

        // Assert
        assertEquals(200, response.getStatusCode(),
                "Failed to retrieve post: expected status code 200, but got " + response.getStatusCode());
        assertEquals(postId, response.jsonPath().getInt("id"),
                "Failed to retrieve correct post: expected ID " + postId + ", but got " + response.jsonPath().getInt("id"));
    }

    @Test
    public void getAllPostsTest() {
        // Arrange
        int expectedPostsCount = 100;

        // Act
        var posts = client.getAllPosts();

        // Assert
        assertEquals(expectedPostsCount, posts.size(),
                "Failed to retrieve all posts: expected " + expectedPostsCount + " posts, but got " + posts.size());
    }

    @Test
    public void updatePostTest() {
        // Arrange
        int postId = 100;
        Post updatePost = new PostBuilder()
                .witId(99)
                .withTitle("Update post")
                .withBody("This is the body of updated post")
                .withUserId(123)
                .build();

        // Act
        var response = client.updatePost(postId, updatePost);

        // Assert
        assertEquals(200, response.getStatusCode(),
                "Post update failed: expected status code 200, but got " + response.getStatusCode());
        assertEquals("Update post", response.jsonPath().getString("title"),
                "Post update failed: expected title 'Update post', but got '" + response.jsonPath().getString("title") + "'");
    }

    @Test
    public void deletePostTest() {
        // Arrange
        int postId = 1;

        // Act
        var response = client.deletePost(postId);

        // Assert
        assertEquals(200, response.getStatusCode(),
                "Post deletion failed: expected status code 200, but got " + response.getStatusCode());
    }
}
