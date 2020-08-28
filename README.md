[![Build Status](https://travis-ci.org/issoupadrink/cardano-rest-tests.svg?branch=master)](https://travis-ci.org/issoupadrink/cardano-rest-tests)
# Cardano Rest Tests

Run functional tests: 
```
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
```

Run all performance tests:
```
mvn gatling:test -Dhost={HostName}
```

Run a specific performance test:
```
mvn gatling:test -Dhost={HostName} -Dgatling.SimulationClass={PathToTestClass}
```

## Reports

To access the functional test report, this command will set up allure reports at http://localhost:8080:
```
allure serve target/allure-results -p 8080
```

Gatling reports are stored in `/target/gatling/{GatlingTestRun}/index.html`

To install Allure: 
```
curl -o allure-2.6.0.tgz -Ls https://dl.bintray.com/qameta/generic/io/qameta/allure/allure/2.6.0/allure-2.6.0.tgz 
sudo tar -zxvf allure-2.6.0.tgz -C /opt/   
sudo ln -s /opt/allure-2.6.0/bin/allure /usr/bin/allure  
allure --version
```

## Docker

You can run the tests by running:
```
docker-compose up
```

Allure report can be found here:
```
http://localhost/
```

Performance reports can be found here:
```
addresses/summary/{address} =>         localhost/gatling/addressessummaryaddresssimulation	
block/{blockhash}/address/{address} => localhost/gatling/blockblockhashaddressaddresssimulation	

blocks/pages =>                        localhost/gatling/blockspagessimulation			
blocks/pages/total =>                  localhost/gatling/blockspagestotalsimulation		
blocks/summary/{blockhash} =>          localhost/gatling/blockssummaryblockhashsimulation	
blocks/txs/{blockhash} =>              localhost/gatling/blockstxsblockhashsimulation	

epochs/{epoch} =>                      localhost/gatling/epochsepochsimulation			
epochs/{epoch}/slots/{slot} =>         localhost/gatling/epochsepochslotsslotsimulation		

genesis/address =>                     localhost/gatling/genesisaddresssimulation
genesis/summary =>                     localhost/gatling/genesissummarysimulation
genesis/address/pages/total            localhost/gatling/genesisaddresspagestotalsimulation
supply/ada =>                          localhost/gatling/supplyadasimulation

txs/summary/{tx} =>                    localhost/gatling/txssummarytxsimulation
txs/last =>                            localhost/gatling/txslastsimulation
stats/txs =>                           localhost/gatling/statstxssimulation
```


