package com.cardano.rest.tests;

import org.testng.annotations.DataProvider;
import org.testng.collections.Lists;

import java.util.Arrays;
import java.util.List;


public class DataStore {

    @DataProvider(name = "addresses")
    public Object[][] getAddresses(){
        return new Object[][] {{"Ae2tdPwUPEZK72eZZqulakkhaUfTCcoaGepvQP718aYBczw5uZmp47h1k14"}};
    }

    @DataProvider(name = "blockHashes")
    public Object[][] getBlockHashes(){
        return new Object[][] {{"3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0"}};
    }

    @DataProvider(name = "addresses-blockHashes")
    public Object[][] getAddressesBlockHashes(){
        return new Object[][] {{"Ae2tdPwUPEZK72eZZqulakkhaUfTCcoaGepvQP718aYBczw5uZmp47h1k14", "3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0"}};
    }

    @DataProvider(name = "epochs")
    public Object[][] getEpochs(){
        return new Object[][] {{"0"}};
    }

    @DataProvider(name = "epochs-slots")
    public Object[][] getEpochsSlots(){
        return new Object[][] {{"0", "0"}};
    }

    @DataProvider(name = "txs")
    public Object[][] getTxs(){
        return new Object[][] {{"3c89f7d9ff6c06468e32fd916d153b033264f780e11fca7750cb85f56d4f31d0"}};
    }
}