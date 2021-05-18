package services;

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

    public Post getPost(int postId) {
        String customPath = basePath + postId;

        RequestSpecification requestSpecification = createGetRequestSpecification(customPath);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .get()
                .as(Post.class);
    }

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

    public void createPost(Post post) {
        RequestSpecification requestSpecification = createPostRequestSpecification(basePath, post);
        ResponseSpecification responseSpecification = createResponseSpecification(201);
        given(requestSpecification, responseSpecification)
                .post();
    }

    public void updatePost(int postId, Post post) {
        String customPath = basePath + postId;
        RequestSpecification requestSpecification = createPostRequestSpecification(customPath, post);
        ResponseSpecification responseSpecification = createResponseSpecification(200);
        given(requestSpecification, responseSpecification).put();
    }

    public void deletePost(int postId) {
        String customPath = basePath + postId;

        RequestSpecification requestSpecification = createGetRequestSpecification(customPath);
        //usually status code for the delete request is 204, but for this service it's 200
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        given(requestSpecification, responseSpecification)
                .delete();
        //TODO: modify names of the util methods, because they are use with different REST methods not only GET and POST
    }
}
