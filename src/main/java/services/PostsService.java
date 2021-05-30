package services;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.comments.Comment;
import models.posts.Post;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static services.Endpoints.POSTS_ENDPOINT;

public class PostsService extends BaseService {

    @Step("Retrieved all posts")
    public List<Post> getPosts() {
        RequestSpecification requestSpecification = createGetRequestSpecification(POSTS_ENDPOINT);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .get()
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Post.class);
    }

    @Step("Retrieved post by id = {id}")
    public Post getPost(int id) {
        String customPath = POSTS_ENDPOINT + id;
        RequestSpecification requestSpecification = createGetRequestSpecification(customPath);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .get()
                .as(Post.class);
    }

    @Step("Retrieved post comments for post with id = {id}")
    public List<Comment> getPostComments(int id) {
        String customPath = POSTS_ENDPOINT + "%s/comments";
        RequestSpecification requestSpecification = createGetRequestSpecification(format(customPath, id));
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
        RequestSpecification requestSpecification = createPostRequestSpecification(POSTS_ENDPOINT, post.toJsonString());
        ResponseSpecification responseSpecification = createResponseSpecification(201);

        return given(requestSpecification, responseSpecification)
                .post()
                .as(Post.class);
    }

    @Step("Updated post with id = {id}")
    public void updatePost(int id, Post post) {
        String customPath = POSTS_ENDPOINT + id;
        RequestSpecification requestSpecification = createPostRequestSpecification(customPath, post.toJsonString());
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        given(requestSpecification, responseSpecification).put();
    }

    @Step("Deleted post with id = {id}")
    public void deletePost(int id) {
        String customPath = POSTS_ENDPOINT + id;
        RequestSpecification requestSpecification = createGetRequestSpecification(customPath);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        given(requestSpecification, responseSpecification)
                .delete();
    }
}
