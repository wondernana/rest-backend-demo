package com.example.demo.spec;

import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public class RequestSpec {
    public static RequestSpecification requestSpecification = with()
            .baseUri("http://localhost:8080")
            .basePath("/user")
            .log().all()
            .contentType(ContentType.JSON);
}
