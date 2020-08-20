package com.cardano.rest.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GenesisTest extends BaseTest {

    @Test
    public void genesisSummary_basicResponse_test() {
        String endpoint = "genesis/summary";
        String url = this.host + endpoint;

        given().
        when().
            get(url).
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test(dependsOnMethods = "genesisSummary_basicResponse_test")
    public void genesisSummary_validSchema_test() {
        String endpoint = "genesis/summary";
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-genesis-summary-schema.json"));
    }

    @Test
    public void genesisAddressPagesTotal_basicResponse_test() {
        String endpoint = "genesis/address/pages/total";
        String url = this.host + endpoint;

        given().
        when().
            get(url).
        then().
            assertThat().
            statusCode(200).
        and().
               contentType(ContentType.JSON);
    }

    @Test(dependsOnMethods = "genesisAddressPagesTotal_basicResponse_test")
    public void genesisAddressPagesTotal_validSchema_test() {
        String endpoint = "genesis/address/pages/total";
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-genesis-address-pages-total-schema.json"));
    }

    @Test
    public void genesisAddress_basicResponse_test() {
        String endpoint = "genesis/address";
        String url = this.host + endpoint;

        given().
        when().
            get(url).
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test(dependsOnMethods = "genesisAddress_basicResponse_test")
    public void genesisAddress_validSchema_test() {
        String endpoint = "genesis/address";
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-genesis-address-schema.json"));
    }

    @Test
    public void supplyAda_basicResponse_test() {
        String endpoint = "supply/ada";
        String url = this.host + endpoint;

        given().
        when().
            get(url).
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test(dependsOnMethods = "supplyAda_basicResponse_test")
    public void supplyAda_validSchema_test() {
        String endpoint = "supply/ada";
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-supply-ada-schema.json"));
    }
}
