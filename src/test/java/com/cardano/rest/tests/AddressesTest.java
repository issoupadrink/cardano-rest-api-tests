package com.cardano.rest.tests;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AddressesTest extends BaseTest {

    @Test(dataProvider = "addresses", dataProviderClass = DataStore.class)
    @Description("addresses/summary/{address} responds")
    public void addressesSummaryAddress_basicResponse_test(String address) {
        String endpoint = String.format("addresses/summary/%s", address);
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

    @Test(dataProvider = "addresses", dataProviderClass = DataStore.class,
            dependsOnMethods = "addressesSummaryAddress_basicResponse_test")
    @Description("addresses/summary/{address} matches expected JSON schema")
    public void addressesSummaryAddress_validSchema_test(String address) {
        String endpoint = String.format("addresses/summary/%s", address);
        String url = this.host + endpoint;

        given().
            get(url).
        then().
            assertThat().
            body(matchesJsonSchemaInClasspath("valid-addresses-summary-address-schema.json"));
    }

    @Test(dataProvider = "addresses-blockHashes", dataProviderClass = DataStore.class)
    @Description("block/{blockHash}/address/{address} responds")
    public void blockBlockhashAddressAddress_basicResponse_test(String address, String blockHash) {
        String endpoint = String.format("block/%s/address/%s", blockHash, address);
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

    @Test(dataProvider = "addresses-blockHashes", dataProviderClass = DataStore.class,
            dependsOnMethods = "blockBlockhashAddressAddress_basicResponse_test")
    @Description("block/{blockHash}/address/{address} matches expected JSON schema")
    public void blockBlockhashAddressAddress_validSchema_test(String address, String blockHash) {
        String endpoint = String.format("block/%s/address/%s", blockHash, address);
        String url = this.host + endpoint;

        given().
                get(url).
        then().
            assertThat().
            body(matchesJsonSchemaInClasspath("valid-block-blockhash-address-address-schema.json"));
    }
}
