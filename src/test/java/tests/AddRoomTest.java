package tests;

import base.TestBase;
import dataproviders.RoomDataProvider;
import org.testng.annotations.Test;
import utils.JsonDataLoader;
import utils.TokenManager;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AddRoomTest extends TestBase {
    
    // Test: Add a room using data from a JSON file (Positive case)
    @Test
    public void addRoom() {
        // Load room details from valid JSON
        Map<String, Object> roomData = JsonDataLoader.loadJsonAsMap("src/test/resources/testdata/addRoom_valid.json");

        given()
            .contentType("application/x-www-form-urlencoded") // Set content type
            .header("Authorization", TokenManager.getAccessToken()) // Auth header
            .formParams(roomData) // Pass JSON data as form params
            .log().all() // Log request
        .when()
            .post("/addRoom") // API endpoint
        .then()
            .log().all() // Log response
            .statusCode(200) // Validate status code
            .body(containsString("<roomId>" + roomData.get("roomId") + "</roomId>")); // Validate roomId in response
    }
    
    // Test: Add a room with invalid status (Negative case)
    @Test
    public void addRoomWithInvalidStatus() {
        // Load invalid room data from JSON
        Map<String, Object> data = JsonDataLoader.loadJsonAsMap("src/test/resources/testdata/addRoom_invalid.json");

        getTest().info("Adding room with invalid roomStatus=" + data.get("roomStatus"));

        given()
            .contentType("application/x-www-form-urlencoded")
            .header("Authorization", TokenManager.getAccessToken())
            .formParams(data)
            .log().all()
        .when()
            .post("/addRoom")
        .then()
            .log().all()
            .statusCode(400) // Expect Bad Request
            .body(containsString("Bad Request"));

        getTest().pass("Negative case for invalid roomStatus returned 400 as expected");
    }
    
    // Test: Add multiple rooms using DataProvider (Parameterized test)
    @Test(dataProvider = "roomData", dataProviderClass = RoomDataProvider.class)
    public void addMultipleRoom(Map<String, Object> roomData) {
        given()
            .contentType("application/x-www-form-urlencoded")
            .header("Authorization", TokenManager.getAccessToken())
            .formParams(roomData)
            .log().all()
        .when()
            .post("/addRoom")
        .then()
            .log().all()
            .statusCode(200)
            .body(containsString("<roomId>" + roomData.get("roomId") + "</roomId>"));
    }
}
