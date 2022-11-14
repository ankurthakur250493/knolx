package com.knoldus;

import io.qameta.allure.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

@Epic("Basic Auth Feature")
@Feature("Basic Auth Feature Demo")
public class BasicAuth {

    private static final Log log = LogFactory.getLog(BasicAuth.class);

    @Test
    @Story("basicAuth")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to show case Basic Auth implementation")
    public void basicAuth() {
        RequestSpecification httpRequest = RestAssured.given().auth().basic("postman", "password");
        Response ressponse = httpRequest.get("https://postman-echo.com/basic-auth");
        ResponseBody body = ressponse.body();
        String responseBody = body.asString();
        log.info(responseBody);
    }
}
