package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.URI;
import static io.restassured.http.ContentType.JSON;
import static util.FileUtil.getConfigProperty;

public abstract class BaseService {

    private String baseUri = getConfigProperty("base.url");

    private RequestSpecification createBaseRequestSpecification(String path) {
        return new RequestSpecBuilder()
                .setAccept(JSON)
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addFilter(new RequestLoggingFilter(URI))
                .addFilter(new RequestLoggingFilter(BODY))
                .build();
    }

    public RequestSpecification createGetRequestSpecification(String path) {
        return createBaseRequestSpecification(path);
    }

    public RequestSpecification createPutRequestSpecification(String path) {
        return createBaseRequestSpecification(path);
    }

    public RequestSpecification createPostRequestSpecification(String path, String jsonString) {
        return createBaseRequestSpecification(path).body(jsonString);
    }

    public ResponseSpecification createResponseSpecification(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(JSON)
                .setDefaultParser(Parser.JSON)
                .build();
    }
}
