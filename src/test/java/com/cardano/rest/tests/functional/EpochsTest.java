package com.cardano.rest.tests.functional;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class EpochsTest extends BaseTest {

    @Test(dataProvider = "epochs", dataProviderClass = DataStore.class)
    @Description("epochs/{epoch} responds")
    public void epochsEpoch_basicResponse_test(String epoch) {
        String endpoint = String.format("epochs/%s", epoch);
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

    @Test(dataProvider = "epochs", dataProviderClass = DataStore.class, dependsOnMethods = "epochsEpoch_basicResponse_test")
    @Description("epochs/{epoch} matches expected JSON schema")
    public void epochsEpoch_validSchema_test(String epoch) {
        String endpoint = String.format("epochs/%s", epoch);
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("schemas/valid-epochs-epoch-schema.json"));
    }

    @Test(dataProvider = "epochs-slots", dataProviderClass = DataStore.class)
    @Description("epochs/{epoch}/{slot} responds")
    public void epochsEpochSlot_basicResponse_test(String epoch, String slot) {
        String endpoint = String.format("epochs/%s/%s", epoch, slot);
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

    @Test(dataProvider = "epochs-slots", dataProviderClass = DataStore.class, dependsOnMethods = "epochsEpochSlot_basicResponse_test")
    @Description("epochs/{epoch}/{slot} matches expected JSON schema")
    public void epochsEpochSlot_validSchema_test(String epoch, String slot) {
        String endpoint = String.format("epochs/%s/%s", epoch, slot);
        String url = this.host + endpoint;

        given().
            get(url)
        .then()
            .assertThat()
            .body(matchesJsonSchemaInClasspath("schemas/valid-epochs-epoch-slot-schema.json"));
    }
}
