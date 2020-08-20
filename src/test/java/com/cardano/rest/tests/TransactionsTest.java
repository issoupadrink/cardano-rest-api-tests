package com.cardano.rest.tests;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TransactionsTest extends BaseTest {

    @Test
    @Description("txs/last responds")
    public void txsLast_basicResponse_test() {
        String endpoint = "txs/last";
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

    @Test(dependsOnMethods = "txsLast_basicResponse_test")
    @Description("txs/last matches expected JSON schema")
    public void txsLast_validSchema_test() {
        String endpoint = "txs/last";
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-txs-last-schema.json"));
    }

    @Test(dataProvider = "txs", dataProviderClass = DataStore.class)
    @Description("txs/summary/{tx} responds")
    public void txsSummaryTxid_basicResponse_test(String tx) {
        String endpoint = String.format("txs/summary/%s", tx);
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

    @Test(dataProvider = "txs", dataProviderClass = DataStore.class, dependsOnMethods = "txsSummaryTxid_basicResponse_test")
    @Description("txs/summary/{tx} matches expected JSON schema")
    public void txsSummaryTxid_validSchema_test(String tx) {
        String endpoint = String.format("txs/summary/%s", tx);
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-txs-summary-txid-schema.json"));
    }

    @Test
    @Description("stats/txs responds")
    public void statsTxs_basicResponse_test() {
        String endpoint = "stats/txs";
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

    @Test(dependsOnMethods = "statsTxs_basicResponse_test")
    @Description("stats/txs matches expected JSON schema")
    public void statsTxs_validSchema_test() {
        String endpoint = "stats/txs";
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-stats-txs-schema.json"));
    }
}
