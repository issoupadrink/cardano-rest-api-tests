# Cardano Rest Tests

Run functional tests: 
```
mvn clean test -Dsurefire.suiteXmlFiles=/path/to/testng.xml
```

Run all performance tests:
```
mvn gatling:test -Dhost={HostName}
```

Run a specific performance test:
```
mvn gatling:test -Dhost={HostName} -Dgatling.SimulationClass={PathToTestClass}
```
