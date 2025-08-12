package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Room;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequestsTests extends TestBase {

    // Test: Get all rooms and verify list is not empty
    @Test
    public void viewRoomList() {
        List<Room> rooms = given()
                .accept("application/json")
                .log().all() // Log request
        .when()
                .get("/viewRoomList") // GET request to view all rooms
        .then()
                .statusCode(200) // Expect OK
                .extract()
                .jsonPath()
                .getList("", Room.class);

        Assert.assertFalse(rooms.isEmpty(), "Room list should not be empty");
    }

    // Test: Get room by valid ID
    @Test
    public void viewRoomById() {
        Room room = given()
                .accept("application/json")
                .pathParam("roomId", 101) // Room ID
                .log().all()
        .when()
                .get("/viewRoomById/{roomId}") // GET by ID
        .then()
                .statusCode(200)
                .extract()
                .as(Room.class);

        Assert.assertEquals(room.getRoomId(), 101, "Room ID should match");
    }

    // Negative Test: Get room by invalid ID
    @Test
    public void viewRoomByInvalidId() {
        getTest().info("Fetching room by invalid ID=999999");

        int statusCode = given()
                .accept("application/json")
                .pathParam("roomId", 999999) // Invalid ID
                .log().all()
        .when()
                .get("/viewRoomById/{roomId}")
        .then()
                .extract()
                .statusCode();

        if (statusCode == 200) {
            getTest().fail("Expected 404 or 204 for invalid ID, but got 200 OK");
            Assert.fail("API returned 200 OK for a non-existent room ID.");
        } else {
            Assert.assertTrue(statusCode == 404 || statusCode == 204,
                    "Expected status code 404 or 204 but got: " + statusCode);
            getTest().pass("Negative case passed with status code: " + statusCode);
        }
    }

    // Test: Get rooms by valid type
    @Test
    public void viewRoomByType() {
        List<Room> rooms = given()
                .accept("application/json")
                .queryParam("roomType", "DOUBLE") // Valid type
                .log().all()
        .when()
                .get("/viewRoomByType")
        .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("", Room.class);

        Assert.assertFalse(rooms.isEmpty(), "Room list should not be empty");
        for (Room r : rooms) {
            Assert.assertEquals(r.getRoomType(), "DOUBLE", "Room type should be DOUBLE");
        }
    }

    // Negative Test: Get rooms by invalid type
    @Test
    public void viewRoomByInvalidType() {
        getTest().info("Fetching rooms by invalid type=TRIPLE");

        int statusCode = given()
                .accept("application/json")
                .queryParam("roomType", "TRIPLE") // Invalid type
                .log().all()
        .when()
                .get("/viewRoomByType")
        .then()
                .extract()
                .statusCode();

        if (statusCode == 200) {
            List<Room> rooms = given()
                    .accept("application/json")
                    .queryParam("roomType", "TRIPLE")
                    .log().ifValidationFails()
            .when()
                    .get("/viewRoomByType")
            .then()
                    .extract()
                    .jsonPath()
                    .getList("", Room.class);

            if (rooms.isEmpty()) {
                getTest().fail("Expected 404 or 204 for invalid type, got 200 OK with empty list.");
                Assert.fail("API returned 200 OK with empty list for invalid room type.");
            } else {
                getTest().fail("Expected 404 or 204, got 200 OK with data: " + rooms.size() + " rooms.");
                Assert.fail("Invalid type returned rooms instead of error.");
            }
        } else {
            Assert.assertTrue(statusCode == 404 || statusCode == 204,
                    "Expected 404 or 204 but got: " + statusCode);
            getTest().pass("Negative case passed with status code: " + statusCode);
        }
    }
}
 