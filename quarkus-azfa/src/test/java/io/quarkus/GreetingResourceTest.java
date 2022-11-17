package io.quarkus;

import io.quarkus.dto.CarDto;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testJaxrs() {
        given().get("/cars/hello").then()
            .statusCode(200)
            .contentType("text/plain")
            .body(equalTo("hello jaxrs"));
    }

    @Test
    void testJaxrsGet() {
        given().get("/cars").then()
            .statusCode(200)
            .contentType(APPLICATION_JSON);
    }

    @Test
    @Disabled
    void testJaxrsPost() {
        CarDto fiesta = new CarDto(true, LocalDateTime.now(), LocalDateTime.now(), "Ford Fiesta");

        given()
            .body(fiesta)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(ACCEPT, APPLICATION_JSON)
            .post("/cars").then()
            .statusCode(201);
    }
}
