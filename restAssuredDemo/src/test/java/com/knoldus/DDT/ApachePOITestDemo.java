package com.knoldus.DDT;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.json.simple.*;

import java.io.FileNotFoundException;

public class ApachePOITestDemo {

    @Test() //TestNG annotation
    @Story("Data Driven Test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to showcase the integration of Apache POI with Rest Assured")
    void postNewEmployees() throws FileNotFoundException {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification httpRequest = RestAssured.given();

        //Here we created data through json object that we can send along with POST request
        JSONObject requestParams = new JSONObject();
        requestParams.put(GetExcel.getData(0, 0, 0), GetExcel.getData(0, 1, 0));
        requestParams.put(GetExcel.getData(0, 0, 1), GetExcel.getData(0, 1, 1));
//        requestParams.put(GetExcel.getData(0, 0, 2), GetExcel.getData(0, 1, 2));

        // specifying headers here, stating that the request body is json
        httpRequest.header("Content-Type", "application/json");

        // add the json body to the request payload
        httpRequest.body(requestParams.toJSONString());

        // Sending POST request
        Response response = httpRequest.request(Method.POST, "api/users");

        //capturing response body to perform validations
        String responseBody = response.getBody().asString();
        Reporter.log(responseBody); // to log the response

        // to capture response code through getStatusCode method.
        int status = response.getStatusCode();

        Assert.assertEquals(status, 201);
        Assert.assertEquals(responseBody.contains("knoldus"), true);
        Assert.assertEquals(responseBody.contains("QA"), true);
    }
}
