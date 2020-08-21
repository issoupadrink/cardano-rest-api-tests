FROM maven:3.6.3-jdk-14 AS build

ENV HOME=/home/usr/app

RUN mkdir -p $HOME

WORKDIR $HOME

ADD pom.xml $HOME

RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]

ADD . $HOME

RUN mvn test -Dsurefire.suiteXmlFiles=testng.xml

CMD ls -l target

#FROM ubuntu:latest
#RUN apt-get update && apt-get install -y allure
#COPY --from=build ./target/allure-results/ .
#EXPOSE 8080
#CMD ["allure", "serve", "target/allure-results", "-p", "8080"]