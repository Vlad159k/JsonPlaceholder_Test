package org.example.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.model.Post;
import java.util.List;

import static io.restassured.RestAssured.*;

public class JsonPlaceholderClient {

    public JsonPlaceholderClient(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    public Response createPost(Post post) {
        return given()
                .contentType("application/json")
                .body(post)
                .post("/posts");
    }

    public Response getPost(int id) {
        return get("/posts/{id}", id);
    }

    public List<Post> getAllPosts() {
        return get("/posts")
                .jsonPath()
                .getList("", Post.class);
    }

    public Response updatePost(int id, Post post) {
        return given()
                .contentType("application/json")
                .body(post)
                .put("/posts/{id}", id);
    }

    public Response deletePost(int id){
        return given()
                .delete("/posts/{id}", id);
    }
}
