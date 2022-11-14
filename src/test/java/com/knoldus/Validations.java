package com.knoldus;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Putting up status code validations")
@Feature("Status Code Validation")
public class Validations {

    @BeforeClass
    void setup() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    @Story("Put status code assertion")
    @Severity(SeverityLevel.NORMAL)
    @Description("Asserting 200 status response code ")
    void validationDemo() {
        given().header("Content-Type", "application/json")
                .queryParam("page", "2")
                .when().get("api/users")
                .then().log().all().assertThat().statusCode(200).body("page", equalTo(2))  //asserting Json response
                .header("Content-Type", "application/json; charset=utf-8"); // asserting response headers
    }
}
