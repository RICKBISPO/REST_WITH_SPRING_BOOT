package com.example.rest_with_spring_boot.integrationtests.controller.withjson;

import com.example.rest_with_spring_boot.configs.TestConfigs;
import com.example.rest_with_spring_boot.data.vo.security.AccountCredentialsVO;
import com.example.rest_with_spring_boot.data.vo.security.TokenVO;
import com.example.rest_with_spring_boot.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerdJsonTest extends AbstractIntegrationTest {

    private static TokenVO tokenVO;

    @Test
    @Order(1)
    void testSignin() throws IOException {

        AccountCredentialsVO credentialsVO = new AccountCredentialsVO("leandro", "admin123");

        tokenVO = given()
                .basePath("/auth/signin")
                    .port(TestConfigs.SERVER_PORT)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(credentialsVO)
                    .when()
                .post()
                    .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .as(TokenVO.class);


        assertNotNull(tokenVO.getAccessToken());
        assertNotNull(tokenVO.getRefreshToken());
    }

    @Test
    @Order(2)
    void testRefresh() throws IOException {

        AccountCredentialsVO credentialsVO = new AccountCredentialsVO("leandro", "admin123");

        var newTokenVO = given()
                .basePath("/auth/refresh")
                    .port(TestConfigs.SERVER_PORT)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("username", tokenVO.getUsername())
                .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenVO.getRefreshToken())
                    .when()
                .put("{username}")
                    .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .as(TokenVO.class);


        assertNotNull(newTokenVO.getAccessToken());
        assertNotNull(newTokenVO.getRefreshToken());
    }

}
