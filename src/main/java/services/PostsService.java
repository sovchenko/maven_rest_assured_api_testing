package services;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.Comment;
import models.Post;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static util.SpecUtil.*;

public class PostsService {
    private StringBuilder basePath = new StringBuilder("/posts/");

    public List<Post> getPosts() {
        RequestSpecification requestSpecification = createGetRequestSpecification(basePath.toString());
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
        String customPath = basePath
                .append(postId)
                .toString();

        RequestSpecification requestSpecification = createGetRequestSpecification(customPath);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .get()
                .as(Post.class);
    }

    public List<Comment> getPostComments(int postId) {
        String customPath = basePath
                .append("%s/comments")
                .toString();

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

    public Post createPost(Post post) {
        RequestSpecification requestSpecification = createPostRequestSpecification(basePath.toString(), post);
        ResponseSpecification responseSpecification = createResponseSpecification(201);
        return given(requestSpecification, responseSpecification)
                .post()
                .as(Post.class);
    }

    public Post updatePost(int postId, Post post) {
        String customPath = basePath
                .append(postId)
                .toString();

        RequestSpecification requestSpecification = createPostRequestSpecification(customPath, post);
        ResponseSpecification responseSpecification = createResponseSpecification(200);
        return given(requestSpecification, responseSpecification)
                .put()
                .as(Post.class);
    }

    public void deletePost(int postId) {
        String customPath = basePath
                .append(postId)
                .toString();

        RequestSpecification requestSpecification = createGetRequestSpecification(customPath);
        ResponseSpecification responseSpecification = createResponseSpecification(204);

        given(requestSpecification, responseSpecification)
                .delete();
        //TODO: modify names of the util methods, because they are use with different REST methods not only GET and POST
    }
}
