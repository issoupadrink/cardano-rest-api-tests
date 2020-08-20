package com.cardano.rest.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GenesisTest {

    @Test
    public void genesisSummary_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/genesis/summary").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void genesisSummary_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/genesis/summary")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-genesis-summary-schema.json"));
    }

    @Test
    public void genesisAddressPagesTotal_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/genesis/address/pages/total").
        then().
            assertThat().
            statusCode(200).
        and().
               contentType(ContentType.JSON);
    }

    @Test
    public void genesisAddressPagesTotal_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/genesis/address/pages/total")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-genesis-address-pages-total-schema.json"));
    }

    @Test
    public void genesisAddress_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/genesis/address").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void genesisAddress_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/genesis/address")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-genesis-address-schema.json"));
    }

    @Test
    public void supplyAda_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/supply/ada").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void supplyAda_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/supply/ada")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-supply-ada-schema.json"));
    }
}
