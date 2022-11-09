package io.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class GreetingTest {
    // NOTE: RestAssured is aware of the application.properties quarkus.http.root-path switch

    @Test
    @Disabled
    public void testJaxrs() {
        RestAssured.when().get("/hello").then()
                .statusCode(200)
                .contentType("text/plain")
                .body(equalTo(String.format("Values [%s]", System.getenv().toString())));
    }

    @Test
    @Disabled
    public void testJaxrsPost() {
        RestAssured.when().post("/hello").then()
                .statusCode(200)
                .contentType("text/plain,application/json")
                .body(equalTo("hallo i'm post"));
    }

    @Test
    public void testServlet() {
        RestAssured.when().get("/servlet/hello").then()
                .statusCode(200)
                .contentType("text/plain")
                .body(equalTo("hello servlet"));
    }

    @Test
    public void testVertx() {
        RestAssured.when().get("/vertx/hello").then()
                .statusCode(200)
                .contentType("text/plain")
                .body(equalTo("hello vertx"));
    }
}
