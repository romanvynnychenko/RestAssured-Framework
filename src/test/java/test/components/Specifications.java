package test.components;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static constants.Constants.Uri.BASE_URI;

public class Specifications {
    public static RequestSpecification requestSpecification;

    public static RequestSpecification requestSpec() throws FileNotFoundException {
        if (requestSpecification == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(BASE_URI)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON)
                    .build();
            return requestSpecification;
        }
        return requestSpecification;
    }

    public static ResponseSpecification responseSpec(Integer statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static void installSpecifications(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }
}