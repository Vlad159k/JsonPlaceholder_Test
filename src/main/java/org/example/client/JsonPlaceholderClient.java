package org.example.client;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.model.Post;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.*;

public class JsonPlaceholderClient {

    private static final Logger logger = LoggerFactory.getLogger(JsonPlaceholderClient.class);
    private final Gson gson;

    private static final String POSTS_ENDPOINT = "/posts";
    private static final String POST_BY_ID_ENDPOINT = "/posts/{id}";

    public JsonPlaceholderClient(String baseUrl) {
        RestAssured.baseURI = baseUrl;
        this.gson = new Gson();
    }

    public Response createPost(Post post) {
        logger.info("Sending POST request to create a new post");
        return given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(post))
                .when()
                .post(POSTS_ENDPOINT);
    }

    public Response getPost(int id) {
        logger.info("Sending GET request to retrieve post with ID: {}", id);
        return given()
                .when()
                .get(POST_BY_ID_ENDPOINT, id);
    }

    public List<Post> getAllPosts() {
        logger.info("Sending GET request to retrieve all posts");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(POSTS_ENDPOINT);

        if (response.getStatusCode() == 200) {
            return response.jsonPath().getList("", Post.class);
        } else {
            throw new RuntimeException("Failed to retrieve posts");
        }
    }

    public Response updatePost(int id, Post post) {
        logger.info("Sending PUT request to update post with ID: {}", id);
        return given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(post))
                .when()
                .put(POST_BY_ID_ENDPOINT, id);
    }

    public Response deletePost(int id){
        logger.info("Sending DELETE request to remove post with ID: {}", id);
        return given()
                .when()
                .delete(POST_BY_ID_ENDPOINT, id);
    }
}
