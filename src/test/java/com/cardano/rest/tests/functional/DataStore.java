package com.cardano.rest.tests.functional;

import org.testng.annotations.DataProvider;


public class DataStore {

    @DataProvider(name = "addresses")
    public Object[][] getAddresses(){
        return new Object[][] {
                {"Ae2tdPwUPEZK72eZZqulakkhaUfTCcoaGepvQP718aYBczw5uZmp47h1k14"}
        };
    }

    @DataProvider(name = "blockHashes")
    public Object[][] getBlockHashes(){
        return new Object[][] {
                {"534097d96a5ef35601ac5b5ea65d168858553cda7edd3f0e004c4129ee6c3172"}
        };
    }

    @DataProvider(name = "addresses-blockHashes")
    public Object[][] getAddressesBlockHashes(){
        return new Object[][] {
                {"Ae2tdPwUPEZK72eZZqulakkhaUfTCcoaGepvQP718aYBczw5uZmp47h1k14", "3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0"}
        };
    }

    @DataProvider(name = "epochs")
    public Object[][] getEpochs(){
        return new Object[][] {
                {"0"},
                {"211"}
        };
    }

    @DataProvider(name = "epochs-slots")
    public Object[][] getEpochsSlots(){
        return new Object[][] {
                {"0", "0"},
                {"211", "20000"}
        };
    }

    @DataProvider(name = "txs")
    public Object[][] getTxs(){
        return new Object[][] {
                {"3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0"}
        };
    }
}