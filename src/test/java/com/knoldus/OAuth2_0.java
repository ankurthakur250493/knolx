package com.knoldus;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Authorization")
@Feature("O_AUTH2.0")
public class OAuth2_0 {
    private static final Log log = LogFactory.getLog(OAuth2_0.class);
    String BASE_URI = "";
    String REDIRECT_URL = "";
    String AUTHORIZATION_CODE = "";
    String CLIENT_ID = "";
    String CLIENT_SECRET = "";

    @Test(enabled = false)
    @Story("Grant Type -> Authorization Code")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to show case POST request")
    void OAuthWithAuthorizationCodeGrantType() {
        Response response =
                given()
                        .header("Authorization", ":")
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("grant_type", "authorization_code").formParam("redirect_uri", REDIRECT_URL)
                        .formParam("response_type", "code")
                        .formParam("code", AUTHORIZATION_CODE)
                        .formParam("client_id", CLIENT_ID)
                        .formParam("client_secret", CLIENT_SECRET)
                        .when()
                        .post(BASE_URI + "/oauth2/token");

        JsonPath jsonResponse = new JsonPath(response.getBody().asString());
        String accessToken = jsonResponse.get("access_token").toString();
        String tokenType = jsonResponse.get("token_type").toString();
        log.info("Oauth Token with type " + tokenType + "   " + accessToken);
    }

    @Test(enabled = false)
    @Story("Grant Type -> Client Credential")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demo test to show case POST request")
    void OAuthWithClientCredentialGrantType() {
        Response response =
                given()
                        .auth().preemptive().basic(CLIENT_ID, CLIENT_SECRET)
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("grant_type", "client_credentials")
                        .formParam("scope", "openid")
                        .when()
                        .post(BASE_URI + "/oauth2/token");

        JsonPath jsonResponse = new JsonPath(response.getBody().asString());
        String accessToken = jsonResponse.get("access_token").toString();
        String tokenType = jsonResponse.get("token_type").toString();
        log.info("Oauth Token with type " + tokenType + "   " + accessToken);

    }
}

