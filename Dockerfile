FROM maven:3.6.3-jdk-14 AS builder

ENV HOME=/home/usr/app

RUN mkdir -p $HOME

WORKDIR $HOME

ADD pom.xml $HOME

RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]

ADD . $HOME

RUN mvn test -Dsurefire.suiteXmlFiles=testng.xml

RUN ls -l

FROM ubuntu:latest

COPY --from=builder /home/usr/app/target/allure-results/ /allure-results/

RUN apt-get update && apt-get install -y curl tar

RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y ant && \
    apt-get clean;

RUN apt-get update && \
    apt-get install ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f;

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

RUN curl -o allure-2.6.0.tgz -Ls https://dl.bintray.com/qameta/generic/io/qameta/allure/allure/2.6.0/allure-2.6.0.tgz && \
    tar -zxvf allure-2.6.0.tgz -C /opt/ && \
    ln -s /opt/allure-2.6.0/bin/allure /usr/bin/allure && \
    allure --version

EXPOSE 8081

CMD ["allure", "serve", "/allure-results/", "-p", "8081"]