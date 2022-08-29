FROM maven:3.8.6-eclipse-temurin-17-alpine as build

RUN apk update

COPY ./ ./

RUN mvn clean package -DskipTests

FROM openjdk:19-jdk-alpine3.16 as app

ARG APP_VERSION=0.0.1-SNAPSHOT

RUN mkdir /app

COPY --from=build target/registrytour-${APP_VERSION}.jar /app

RUN mv -v /app/registrytour-${APP_VERSION}.jar /app/registrytour.jar

CMD ["java","-jar","/app/registrytour.jar"]