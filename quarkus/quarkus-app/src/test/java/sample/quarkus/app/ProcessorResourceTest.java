package sample.quarkus.app;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ProcessorResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
            .when()
            .get("/processors")
            .then()
            .statusCode(200)
            .body(is("success"));
    }
}
