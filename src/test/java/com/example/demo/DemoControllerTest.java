package com.example.demo;

import com.example.demo.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static com.example.demo.spec.RequestSpec.requestSpecification;
import static io.restassured.RestAssured.given;

public class DemoControllerTest {
    @Test
    public void testGetAllUsers(){
        given()
            .spec(requestSpecification)
            .when()
                .get("/getAll")
            .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response()
                .as(UserInfo[].class);
    }
}
