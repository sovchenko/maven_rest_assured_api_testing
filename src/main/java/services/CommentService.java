package services;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.comments.Comment;

import static io.restassured.RestAssured.given;
import static util.SpecUtil.*;

public class CommentService extends BaseService {
    private String basePath = "/comments/";

    @Step("Retrieved comment by comment id")
    public Comment getCommentById(int id) {
        String commentIdPath = basePath + id;
        RequestSpecification requestSpecification = createGetRequestSpecification(commentIdPath);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .get()
                .as(Comment.class);
    }

    @Step("Created comment")
    public Comment createComment(Comment comment) {
        RequestSpecification requestSpecification = createPostRequestSpecification(basePath, comment.toJsonString());
        ResponseSpecification responseSpecification = createResponseSpecification(201);

        return given(requestSpecification, responseSpecification)
                .post()
                .as(Comment.class);
    }

    @Step("Updated the comment")
    public Comment updateComment(int commentId, Comment newComment) {
        RequestSpecification requestSpecification = createPutRequestSpecification(basePath + commentId);
        ResponseSpecification responseSpecification = createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .put()
                .as(Comment.class);
    }
}
