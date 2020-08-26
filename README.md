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

To access the functional test report, this command will set up allure reports at http://localhost:8081:
```
allure serve target/allure-results -p 8081
```

Gatling reports are stored in `/target/gatling/{GatlingTestRun}/index.html`

To install Allure: 
```
curl -o allure-2.6.0.tgz -Ls https://dl.bintray.com/qameta/generic/io/qameta/allure/allure/2.6.0/allure-2.6.0.tgz 
sudo tar -zxvf allure-2.6.0.tgz -C /opt/   
sudo ln -s /opt/allure-2.6.0/bin/allure /usr/bin/allure  
allure --version
```
