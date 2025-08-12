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

    @Test
    public void updateRoomPrice() {
        List<Room> rooms = given()
                .contentType("application/x-www-form-urlencoded")
                .accept("application/json")
                .formParam("roomId", 101)
                .formParam("roomPrice", 1500.0F)
        .when()
                .put("/updateRoomPrice")
        .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("", Room.class); // Extract as List<Room>

        // Find the updated room
        Room updatedRoom = rooms.stream()
                .filter(r -> r.getRoomId() == 101)
                .findFirst()
                .orElse(null);

        Assert.assertNotNull(updatedRoom, "Updated room should be present in response");
        Assert.assertEquals(updatedRoom.getRoomPrice(), 1500.0, "Room price should be updated");
    }
    
    
    @Test
    public void updateRoomPriceInvalidId() {
        getTest().info("Updating room price for invalid roomId=9999");

        given()
            .contentType("application/x-www-form-urlencoded")
            .accept("application/json")
            .formParam("roomId", 9999) // Invalid room ID
            .formParam("roomPrice", 1500.0F)
        .when()
            .put("/updateRoomPrice")
        .then()
        .statusCode(anyOf(equalTo(404), equalTo(204))); // Expected not found / no content

        getTest().pass("Negative case for invalid roomId returned proper error code");
    }

}
