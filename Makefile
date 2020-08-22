functional-tests:
	mvn clean test -DsuiteXmlFile=testng.xml

scenarios-debug:
	mvn gatling:test -Dusers=1 -Druns=1

scenarios-live:
	mvn gatling:test -Dusers=2 -Druns 10