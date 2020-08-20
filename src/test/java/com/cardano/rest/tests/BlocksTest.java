package com.cardano.rest.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BlocksTest {

    @Test
    public void blocksPages_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/blocks/pages").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test(dependsOnMethods={"blocksPages_basicResponse_test"})
    public void blocksPages_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/blocks/pages")
        .then()
        .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-blocks-pages-schema.json"));
    }

    @Test
    public void blocksPagesTotal_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/blocks/pages/total").
       then().
            assertThat().
            statusCode(200).
       and().
            contentType(ContentType.JSON);
    }

    @Test
    public void blocksPagesTotal_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/blocks/pages/total")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-blocks-pages-total-schema.json"));
    }

    @Test
    public void blocksSummaryBlockhash_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/blocks/summary/3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void blocksSummaryBlockhash_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/blocks/summary/3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-blocks-summary-blockhash-schema.json"));
    }

    @Test
    public void blocksTxsBlockhash_basicResponse_test() {
        given().
        when().
            get("https://explorer.cardano.org/api/blocks/txs/3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0").
        then().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON);
    }

    @Test
    public void blocksTxsBlockhash_validSchema_test() {
        given().
            get("https://explorer.cardano.org/api/blocks/txs/3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0")
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("valid-blocks-txs-blockhash-schema.json"));
    }
}
