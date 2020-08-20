package com.cardano.rest.tests.blocks;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BlocksPagesTest {

    @Test
    public void basicResponseTest() {
        given().
        when().
            get("https://explorer.cardano.org/api/blocks/pages").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test(dependsOnMethods={"basicResponseTest"})
    public void validSchemaTest() {
        given().
            get("https://explorer.cardano.org/api/blocks/pages")
        .then()
        .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-blocks-pages-schema.json"));
    }
}
