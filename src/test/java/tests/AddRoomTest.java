package tests;

import base.TestBase;
import org.testng.annotations.Test;

import utils.JsonDataLoader;
import utils.TokenManager;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class AddRoomTest extends TestBase {
	
	@Test
    public void addRoom() {
        Map<String, Object> roomData = JsonDataLoader.loadJsonAsMap("src/test/resources/testdata/addRoom_valid.json");

        given()
            .contentType("application/x-www-form-urlencoded")
            .header("Authorization", TokenManager.getAccessToken())
            .formParams(roomData)
        .when()
            .post("/addRoom")
        .then()
            .statusCode(200)
            .body(containsString("<roomId>" + roomData.get("roomId") + "</roomId>"));
    }
    
    
	@Test
	
    public void addRoomWithInvalidStatus() {
        Map<String, Object> data = JsonDataLoader.loadJsonAsMap("src/test/resources/testdata/addRoom_invalid.json");

        getTest().info("Adding room with invalid roomStatus=" + data.get("roomStatus"));

        given()
            .contentType("application/x-www-form-urlencoded")
            .header("Authorization", TokenManager.getAccessToken())
            .formParams(data)
        .when()
            .post("/addRoom")
        .then()
            .statusCode(400)
            .body(containsString("Bad Request"));

        getTest().pass("Negative case for invalid roomStatus returned 400 as expected");
    }

}
