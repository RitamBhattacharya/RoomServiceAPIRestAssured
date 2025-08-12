package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Room;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class UpdateRoomTest extends TestBase {

    // Test: Update price of an existing room
    @Test
    public void updateRoomPrice() {
        List<Room> rooms = given()
                .contentType("application/x-www-form-urlencoded") // Form data content type
                .accept("application/json")
                .formParam("roomId", 101) // Valid room ID
                .formParam("roomPrice", 1500.0F) // New price
                .log().all() // Log request
        .when()
                .put("/updateRoomPrice") // PUT request to update room price
        .then()
                .log().all() // Log response
                .statusCode(200) // Expect OK
                .extract()
                .jsonPath().getList("", Room.class); // Extract as List<Room>

        // Find updated room in response
        Room updatedRoom = rooms.stream()
                .filter(r -> r.getRoomId() == 101)
                .findFirst()
                .orElse(null);

        Assert.assertNotNull(updatedRoom, "Updated room should be present in response");
        Assert.assertEquals(updatedRoom.getRoomPrice(), 1500.0, "Room price should be updated");
    }
    
    // Negative Test: Try updating price for a non-existent room
    @Test
    public void updateRoomPriceInvalidId() {
        getTest().info("Updating room price for invalid roomId=9999");

        given()
            .contentType("application/x-www-form-urlencoded")
            .accept("application/json")
            .formParam("roomId", 9999) // Invalid ID
            .formParam("roomPrice", 1500.0F)
            .log().all() // Log request
        .when()
            .put("/updateRoomPrice") // Attempt update
        .then()
            .log().all() // Log response
            .statusCode(anyOf(equalTo(404), equalTo(204))); // Expect Not Found or No Content

        getTest().pass("Negative case for invalid roomId returned proper error code");
    }
}
