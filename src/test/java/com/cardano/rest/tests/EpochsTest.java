package com.cardano.rest.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class EpochsTest {

    @Test
    public void epochsEpoch_basicResponse_test() {
        given().
                when().
                get("https://explorer.cardano.org/api/epochs/0").
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON);
    }

    @Test
    public void epochsEpoch_validSchema_test() {
        given().
                get("https://explorer.cardano.org/api/epochs/0")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("valid-epochs-epoch-schema.json"));
    }

    @Test
    public void epochsEpochSlot_basicResponse_test() {
        given().
                when().
                get("https://explorer.cardano.org/api/epochs/0/0").
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON);
    }

    @Test
    public void epochsEpochSlot_validSchema_test() {
        given().
                get("https://explorer.cardano.org/api/epochs/0/0")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("valid-epochs-epoch-slot-schema.json"));
    }
}
