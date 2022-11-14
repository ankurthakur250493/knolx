package com.knoldus;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Dummy Test")
@Feature("Write First basic test with Rest Assured")
public class SimpleTest {

    @Test
    @Story("Writing First basic test script")
    @Severity(SeverityLevel.NORMAL)
    @Description("Dummy Test")
    public void firstTestCase() {
        String url = "https://reqres.in/api/users?page=2";

        given()
                .when()
                .get(url)
                .then()
                .assertThat()
                .statusCode(200);
    }
}
