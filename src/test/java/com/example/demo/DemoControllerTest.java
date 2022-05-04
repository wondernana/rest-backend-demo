package com.example.demo;

import com.example.demo.domain.LoginInfo;
import com.example.demo.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static com.example.demo.spec.RequestSpec.requestSpecification;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

public class DemoControllerTest {

    private final String validUsername = "Harry";
    private final String invalidUsername = "Frodo";

    @Test
    public void testGetAllUsers(){
        UserInfo[] userInfos = given()
                .spec(requestSpecification)
                .when()
                    .get("/getAll")
                .then()
                    .statusCode(200)
                    .log().body()
                    .extract()
                    .response()
                    .as(UserInfo[].class);

        assertThat(userInfos)
                .hasSize(3)
                .extracting(UserInfo::getUsername)
                .contains(validUsername)
                .doesNotContain(invalidUsername);
    }

    @Test
    public void testSuccessfulLogin(){
        UserInfo userInfo = given()
                .spec(requestSpecification)
                .when()
                    .body(LoginInfo.builder().username(validUsername).build())
                    .post("/login")
                .then()
                    .statusCode(200)
                    .log().body()
                    .extract()
                    .response()
                    .as(UserInfo.class);

        assertThat(userInfo.getUsername()).isEqualTo(validUsername);
    }

    @Test
    public void testUnsuccessfulLogin(){
        given()
                .spec(requestSpecification)
                .when()
                    .body(LoginInfo.builder().username(invalidUsername).build())
                    .post("/login")
                .then()
                    .statusCode(401)
                    .log().body();
    }
}
