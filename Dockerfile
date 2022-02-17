FROM maven:3.6.3-jdk-8 as build-container

USER root

RUN mkdir -p /home/service

COPY . /home/service

WORKDIR /home/service

RUN mvn install -Dmaven.test.skip=true



FROM openjdk:8

RUN mkdir -p /home/service

COPY --from=build-container /home/service/target/shop-1.0.jar /home/service

