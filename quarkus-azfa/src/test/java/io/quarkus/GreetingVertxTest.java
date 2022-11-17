package io.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class GreetingVertxTest {

    @Test
    void testVertx() {
        RestAssured.when().get("/vertx/hello").then()
            .statusCode(200)
            .contentType("text/plain")
            .body(equalTo("hello vertx"));
    }
}
