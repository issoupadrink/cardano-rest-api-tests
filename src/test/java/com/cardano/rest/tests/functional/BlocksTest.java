package com.cardano.rest.tests.functional;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BlocksTest extends BaseTest {

    @Test
    @Description("blocks/pages responds")
    public void blocksPages_basicResponse_test() {
        String endpoint = ("blocks/pages");
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

    @Test(dependsOnMethods={"blocksPages_basicResponse_test"})
    @Description("blocks/pages matches expected JSON schema")
    public void blocksPages_validSchema_test() {
        String endpoint = ("blocks/pages");
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
        .assertThat()
            .body(matchesJsonSchemaInClasspath("schemas/valid-blocks-pages-schema.json"));
    }

    @Test
    @Description("blocks/pages/total responds")
    public void blocksPagesTotal_basicResponse_test() {
        String endpoint = ("blocks/pages/total");
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

    @Test(dependsOnMethods = "blocksPagesTotal_basicResponse_test")
    @Description("blocks/pages/total matches expected JSON schema")
    public void blocksPagesTotal_validSchema_test() {
        String endpoint = ("blocks/pages/total");
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("schemas/valid-blocks-pages-total-schema.json"));
    }

    @Test(dataProvider = "blockHashes", dataProviderClass = DataStore.class)
    @Description("blocks/summary/{blockHash} responds")
    public void blocksSummaryBlockhash_basicResponse_test(String blockHash) {
        String endpoint = String.format("blocks/summary/%s", blockHash);
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

    @Test(dataProvider = "blockHashes", dataProviderClass = DataStore.class, dependsOnMethods = "blocksSummaryBlockhash_basicResponse_test")
    @Description("blocks/summary/{blockHash} matches expected JSON schema")
    public void blocksSummaryBlockhash_validSchema_test(String blockHash) {
        String endpoint = String.format("blocks/summary/%s", blockHash);
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("schemas/valid-blocks-summary-blockhash-schema.json"));
    }

    @Test(dataProvider = "blockHashes", dataProviderClass = DataStore.class)
    @Description("blocks/txs/{blockHash} responds")
    public void blocksTxsBlockhash_basicResponse_test(String blockHash) {
        String endpoint = String.format("blocks/txs/%s", blockHash);
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

    @Test(dataProvider = "blockHashes", dataProviderClass = DataStore.class, dependsOnMethods = "blocksTxsBlockhash_basicResponse_test")
    @Description("blocks/txs/{blockHash} matches expected JSON schema")
    public void blocksTxsBlockhash_validSchema_test(String blockHash) {
        String endpoint = String.format("blocks/txs/%s", blockHash);
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("schemas/valid-blocks-txs-blockhash-schema.json"));
    }
}
