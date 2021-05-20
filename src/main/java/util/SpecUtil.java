package util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

import static io.restassured.http.ContentType.JSON;

@UtilityClass
public class SpecUtil {
    private static String baseUri = "https://jsonplaceholder.typicode.com";

    private static RequestSpecification createBaseRequestSpecification(String path) {
        return new RequestSpecBuilder()
                .setAccept(JSON)
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    public static RequestSpecification createGetRequestSpecification(String path) {
        return createBaseRequestSpecification(path);
    }

    public static RequestSpecification createPutRequestSpecification(String path) {
        return createBaseRequestSpecification(path);
    }

    public static RequestSpecification createPostRequestSpecification(String path, String jsonString) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .setContentType(JSON)
                .setBody(jsonString)
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
