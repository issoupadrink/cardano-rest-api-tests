FROM maven:3.6.3-jdk-8

COPY ./ ./

RUN mvn clean package

RUN mvn clean test -DsuiteXmlFile=testng.xml

CP ./target/allure-results ./reports