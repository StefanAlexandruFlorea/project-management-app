FROM ubuntu-jdk

MAINTAINER Stefan Florea "stefan.florea@live.com"

ENV version=docker
ENV jdbcurl
ENV dbuser
ENV dbpass

WORKDIR /usr/local/bin

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]