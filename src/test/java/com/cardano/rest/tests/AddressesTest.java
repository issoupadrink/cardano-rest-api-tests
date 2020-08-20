package com.cardano.rest.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AddressesTest {

    @Test
    public void addressesSummaryAddress_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/addresses/summary/Ae2tdPwUPEZK72eZZqulakkhaUfTCcoaGepvQP718aYBczw5uZmp47h1k14").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void addressesSummaryAddress_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/addresses/summary/Ae2tdPwUPEZK72eZZqulakkhaUfTCcoaGepvQP718aYBczw5uZmp47h1k14")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-addresses-summary-address-schema.json"));
    }

    @Test
    public void blockBlockhashAddressAddress_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/block/3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0/address/Ae2tdPwUPEZK72eZZqulakkhaUfTCcoaGepvQP718aYBczw5uZmp47h1k14").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void blockBlockhashAddressAddress_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/block/3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0/address/Ae2tdPwUPEZK72eZZqulakkhaUfTCcoaGepvQP718aYBczw5uZmp47h1k14")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-block-blockhash-address-address-schema.json"));
    }
}
