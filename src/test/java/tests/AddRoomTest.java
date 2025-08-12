package tests;

import base.TestBase;
import org.testng.annotations.Test;
import utils.TokenManager;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AddRoomTest extends TestBase {

    @Test
    public void addRoom() {
        given()
            .contentType("application/x-www-form-urlencoded")
            .header("Authorization", TokenManager.getAccessToken())
            .formParam("roomId", 11)
            .formParam("hotelId", "1001a")
            .formParam("roomType", "SINGLE")
            .formParam("roomStatus", "AVAILABLE")
            .formParam("roomPrice", 20000)
        .when()
            .post("/addRoom")
        .then()
            .statusCode(200)
            .body(containsString("<roomId>11</roomId>"));
    }
    
    
    @Test
    public void addRoomWithInvalidStatus() {
        getTest().info("Adding room with invalid roomStatus=INVALID_STATUS");

        given()
            .contentType("application/x-www-form-urlencoded")
            .header("Authorization", TokenManager.getAccessToken())
            .formParam("roomId", 12) // Use different ID to avoid conflicts
            .formParam("hotelId", "1001a")
            .formParam("roomType", "SINGLE")
            .formParam("roomStatus", "INVALID_STATUS") // invalid value
            .formParam("roomPrice", 20000)
        .when()
            .post("/addRoom")
            .then()
            .statusCode(400) // Expect 400 Bad Request
            .body(containsString("Bad Request")); // Works for HTML response
        
        getTest().pass("Negative case for invalid roomStatus returned 400 as expected");
    }

}
