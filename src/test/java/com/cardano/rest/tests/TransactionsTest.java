package com.cardano.rest.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TransactionsTest {

    @Test
    public void txsLast_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/txs/last").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void txsLast_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/txs/last")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-txs-last-schema.json"));
    }

    @Test
    public void txsSummaryTxid_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/txs/summary/3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void txsSummaryTxid_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/txs/summary/3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-txs-summary-txid-schema.json"));
    }

    @Test
    public void statsTxs_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/stats/txs").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void statsTxs_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/stats/txs")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-stats-txs-schema.json"));
    }
}
