package com.knoldus;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Handling Response")
@Feature("Store and extract values for Response")
public class ExtractingResponse {

    @BeforeClass
    void setup() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    @Story("Parsing JSON response")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to show case parsing and extracting values for JSON response")
    void extractResponse() {
        String response = given().header("Content-Type", "application/json")
                .queryParam("page", "2")
                .when().get("api/users")
                .then().log().all().assertThat().statusCode(200).body("page", equalTo(2))  //asserting Json response
                .header("Content-Type", "application/json; charset=utf-8") // asserting response headers
                .extract().response().asString();
        Assert.assertTrue(response.contains("page"));

        JsonPath jsonResponse = new JsonPath(response);
        String getIdAtZerothIndex = jsonResponse.getString("data[0].id");
        Assert.assertEquals(getIdAtZerothIndex, "7");

    }
}

