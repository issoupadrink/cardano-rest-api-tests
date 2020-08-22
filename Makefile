functional-tests:
	mvn clean test -DsuiteXmlFile=testng.xml

scenarios-debug:
	mvn gatling:test -Dhost=https://explorer.cardano-testnet.iohkdev.io/api/ -X

scenarios-live:
	mvn gatling:test -Dusers=1 -Druns=10 -Dhost=https://explorer.cardano.io/api/