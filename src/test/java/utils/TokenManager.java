package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenManager {

    // Request auth code using username and password
    public static String getAuthCode() {
        Response res = RestAssured.given()
                .baseUri(ConfigLoader.get("auth_url"))
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", ConfigLoader.get("username"))
                .formParam("password", ConfigLoader.get("password"))
                .post("/login");
        return res.jsonPath().getString("auth_code");
    }

    // Exchange auth code for access token
    public static String getAccessToken() {
        String authCode = getAuthCode();
        Response res = RestAssured.given()
                .baseUri(ConfigLoader.get("auth_url"))
                .contentType("application/x-www-form-urlencoded")
                .formParam("auth_code", authCode)
                .post("/token");
        return res.jsonPath().getString("access_token");
    }
}
