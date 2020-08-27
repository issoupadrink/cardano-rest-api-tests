package com.cardano.rest.tests.functional;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.cardano.rest.tests.DataStore;

public class BaseTest {

    protected String host;
    protected DataStore dataStore;

    @BeforeSuite
    public void setupProperties() {
        this.dataStore = new DataStore();
    }

    @BeforeTest
    public void setupTests() {
        String resourceName = "config.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.host = props.getProperty("host");
    }
}
