package com.cardano.rest.tests;

import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected String host;

    @BeforeTest
    public void setupTests() {
        this.host = "https://explorer.cardano.org/api/";

        if (System.getProperty("HUB_HOST") != null){
            this.host = System.getProperty("HUB_HOST");
        }
    }
}
