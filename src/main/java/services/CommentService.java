package services;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.comments.Comment;

import static io.restassured.RestAssured.given;
import static services.Endpoints.COMMENTS_ENDPOINT;

public class CommentService extends BaseService {

    @Step("Retrieved comment by comment id = {id}")
    public Comment getCommentById(int id) {
        String commentIdPath = COMMENTS_ENDPOINT + id;
        RequestSpecification requestSpecification = createGetRequestSpecification(commentIdPath);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .get()
                .as(Comment.class);
    }

    @Step("Created comment")
    public Comment createComment(Comment comment) {
        RequestSpecification requestSpecification = createPostRequestSpecification(COMMENTS_ENDPOINT, comment.toJsonString());
        ResponseSpecification responseSpecification = createResponseSpecification(201);

        return given(requestSpecification, responseSpecification)
                .post()
                .as(Comment.class);
    }

    @Step("Updated the comment with id = {id}")
    public Comment updateComment(int id, Comment newComment) {
        RequestSpecification requestSpecification = createPutRequestSpecification(COMMENTS_ENDPOINT + id);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .put()
                .as(Comment.class);
    }
}
