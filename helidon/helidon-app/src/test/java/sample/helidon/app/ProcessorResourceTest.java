package sample.helidon.app;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.helidon.microprofile.testing.junit5.AddConfig;
import io.helidon.microprofile.testing.junit5.HelidonTest;
import io.restassured.RestAssured;

@HelidonTest
@AddConfig(key = "server.port", value = "7001")
class ProcessorResourceTest {
    
    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "http://localhost:7001";
    }
    
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
