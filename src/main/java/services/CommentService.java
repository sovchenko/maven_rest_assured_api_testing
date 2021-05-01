package services;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.Comment;
import util.SpecUtil;

import static io.restassured.RestAssured.given;

public class CommentService extends BaseService {
    private String basePath = "/comments/";

    public Comment getCommentById(int id) {
        String commentIdPath = basePath + id;
        RequestSpecification requestSpecification = SpecUtil.createGetRequestSpecification(commentIdPath);
        ResponseSpecification responseSpecification = SpecUtil.createResponseSpecification(200);

        return given(requestSpecification, responseSpecification)
                .get()
                .as(Comment.class);
    }
}
