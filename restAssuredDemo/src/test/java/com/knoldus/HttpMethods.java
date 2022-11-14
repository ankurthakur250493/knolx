package com.knoldus;

import io.qameta.allure.*;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("CRUD operations")
@Feature("Using different HTTP methods via Rest Assured")
public class HttpMethods {
    static String generateAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int iterator = 0; iterator < n; iterator++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    @BeforeClass
    void setup() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    @Story("HTTP methods")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to show case POST request")
    void post() {
        given().header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"name\": \"" + generateAlphaNumericString(10) + "\",\n" +
                        "    \"job\": \"QA\"\n" +
                        "}")
                .when().post("api/users")
                .then().log().all().assertThat().statusCode(201);
    }

    @Test
    @Story("HTTP methods")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to show case GET request")
    void get() {
        given().header("Content-Type", "application/json")
                .queryParam("page", "2")
                .when().get("api/users")
                .then().log().all().assertThat().statusCode(200);
    }

    @Test
    @Story("HTTP methods")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to show case PUT request")
    void put() {
        given().header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"name\": \"" + generateAlphaNumericString(10) + "\",\n" +
                        "    \"job\": \"QA\"\n" +
                        "}")
                .when().put("api/users/2")
                .then().log().all().assertThat().statusCode(200);
    }

    @Test
    @Story("HTTP methods")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to show case PATCH request")
    void patch() {
        given().header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"name\": \"" + generateAlphaNumericString(10) + "\",\n" +
                        "    \"job\": \"QA\"\n" +
                        "}")
                .when().patch("api/users/2")
                .then().log().all().assertThat().statusCode(200);
    }

    @Test
    @Story("HTTP methods")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to show case DELETE request")
    void delete() {
        given().header("Content-Type", "application/json")
                .when().delete("api/users/2")
                .then().log().all().assertThat().statusCode(204);
    }
}
