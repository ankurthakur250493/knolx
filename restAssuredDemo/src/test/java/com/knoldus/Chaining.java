package com.knoldus;

import io.qameta.allure.*;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Chaining the requests")
@Feature("Multiple calls using TestNg and Rest Assured")
public class Chaining {

    @BeforeClass
    void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    @Story("Chaining")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to show case Chaining of request - Positive test")
    void positiveTest() {
        given().queryParam("page", "2")
                .header("Content-Type", "application/json")
                .when().get("/users")
                .then().log().all().assertThat().statusCode(200);

    }

    @Test
    @Story("Chaining")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to show case Chaining of request - Negative Tests")
    void negativeTest() {
        given().queryParam("page", "2")
                .header("Content-Type", "application/json")
                .when().patch("/users")
                .then().log().all().assertThat().statusCode(404);

    }
}
