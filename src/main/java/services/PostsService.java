package services;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.comments.Comment;
import models.posts.Post;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static util.SpecUtil.*;

public class PostsService {
    private final String basePath = "/posts/";

    @Step("Retrieved all posts")
    public List<Post> getPosts() {
        RequestSpecification requestSpecification = createGetRequestSpecification(basePath);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .get()
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Post.class);
    }

    @Step("Retrieved post by id")
    public Post getPost(int postId) {
        String customPath = basePath + postId;
        RequestSpecification requestSpecification = createGetRequestSpecification(customPath);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .get()
                .as(Post.class);
    }

    @Step("Retrieved post comments")
    public List<Comment> getPostComments(int postId) {
        String customPath = basePath + "%s/comments";
        RequestSpecification requestSpecification = createGetRequestSpecification(format(customPath, postId));
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .get()
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Comment.class);
    }

    @Step("Created posts")
    public Post createPost(Post post) {
        RequestSpecification requestSpecification = createPostRequestSpecification(basePath, post.toJsonString());
        ResponseSpecification responseSpecification = createResponseSpecification(201);

        return given(requestSpecification, responseSpecification)
                .post()
                .as(Post.class);
    }

    @Step("Updated post")
    public void updatePost(int postId, Post post) {
        String customPath = basePath + postId;
        RequestSpecification requestSpecification = createPostRequestSpecification(customPath, post.toJsonString());
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        given(requestSpecification, responseSpecification).put();
    }

    @Step("Deleted post")
    public void deletePost(int postId) {
        String customPath = basePath + postId;
        RequestSpecification requestSpecification = createGetRequestSpecification(customPath);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        given(requestSpecification, responseSpecification)
                .delete();
    }
}
