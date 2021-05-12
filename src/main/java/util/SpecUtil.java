package util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;
import models.posts.Post;

import static io.restassured.http.ContentType.JSON;

@UtilityClass
public class SpecUtil {
    private static String baseUri = "https://jsonplaceholder.typicode.com";

    public static RequestSpecification createGetRequestSpecification(String path) {
        return new RequestSpecBuilder()
                .setAccept(JSON)
                //should be read from the YAML or properties file
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    public static RequestSpecification createPostRequestSpecification(String path, Post postModel) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .setContentType(JSON)
                .setBody(postModel)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static ResponseSpecification createResponseSpecification(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(JSON)
                .setDefaultParser(Parser.JSON)
                .build();
    }
}
