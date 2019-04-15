package org.acme.spring.di;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
            .when().get("/greeting")
            .then()
                .statusCode(200)
                .content(JSONMatcher.matchesJSON("{\"id\":0,\"content\":\"HELLO WORLD!\"}"));
    }

}
