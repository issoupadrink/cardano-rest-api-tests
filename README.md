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
addresses/summary/{address} =>         http://localhost/gatling/addressessummaryaddresssimulation	
block/{blockhash}/address/{address} => http://localhost/gatling/blockblockhashaddressaddresssimulation	

blocks/pages =>                        http://localhost/gatling/blockspagessimulation			
blocks/pages/total =>                  http://localhost/gatling/blockspagestotalsimulation		
blocks/summary/{blockhash} =>          http://localhost/gatling/blockssummaryblockhashsimulation	
blocks/txs/{blockhash} =>              http://localhost/gatling/blockstxsblockhashsimulation	

epochs/{epoch} =>                      http://localhost/gatling/epochsepochsimulation			
epochs/{epoch}/slots/{slot} =>         http://localhost/gatling/epochsepochslotsslotsimulation		

genesis/address =>                     http://localhost/gatling/genesisaddresssimulation
genesis/summary =>                     http://localhost/gatling/genesissummarysimulation
genesis/address/pages/total            http://localhost/gatling/genesisaddresspagestotalsimulation
supply/ada =>                          http://localhost/gatling/supplyadasimulation

txs/summary/{tx} =>                    http://localhost/gatling/txssummarytxsimulation
txs/last =>                            http://localhost/gatling/txslastsimulation
stats/txs =>                           http://localhost/gatling/statstxssimulation

```


