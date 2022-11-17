package io.azfa.function;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class GreetingServletTest {

    @Test
    void testServlet() {
        RestAssured.when().get("/servlet/hello").then()
                .statusCode(200)
                .contentType("text/plain")
                .body(equalTo("hello servlet"));
    }

}
