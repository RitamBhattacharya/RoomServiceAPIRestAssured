package tests;

import base.TestBase;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteRoomTest extends TestBase {

    @Test
    public void deleteRoom() {
        given()
            .pathParam("roomId", 101)
        .when()
            .delete("/deleteRoomById/{roomId}")
        .then()
            .statusCode(anyOf(equalTo(200), equalTo(404)));
    }
    
    
    @Test
    public void testDeleteRoomNegative() {
        getTest().info("Deleting non-existent room id=99999");
        given()
            .accept("application/json")
        .when()
            .delete("/deleteRoomById/99999")
        .then()
            .statusCode(anyOf(equalTo(404), equalTo(204)));
        getTest().pass("delete Room negative");
    }
}
