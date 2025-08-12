package tests;

import base.TestBase;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteRoomTest extends TestBase {

    // Test: Delete existing room by ID
    @Test
    public void deleteRoom() {
        given()
            .pathParam("roomId", 101) // Room ID to delete
            .log().all() // Log request
        .when()
            .delete("/deleteRoomById/{roomId}") // Send DELETE request
        .then()
            .log().all() // Log response
            .statusCode(anyOf(equalTo(200), equalTo(404))); // Accept 200 or 404
    }
    
    // Test: Delete non-existent room (Negative test)
    @Test
    public void testDeleteRoomNegative() {
        getTest().info("Deleting non-existent room id=99999");

        given()
            .accept("application/json") // Expect JSON response
            .log().all() // Log request
        .when()
            .delete("/deleteRoomById/99999") // Invalid room ID
        .then()
            .log().all() // Log response
            .statusCode(anyOf(equalTo(404), equalTo(204))); // Expect 404 or 204

        getTest().pass("delete Room negative");
    }
}
