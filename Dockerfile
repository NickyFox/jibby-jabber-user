FROM gradle:7.0-jdk11 AS build

COPY . /home/gradle/src/
WORKDIR /home/gradle/src
RUN ./gradlew build

FROM openjdk:11-jre-slim

EXPOSE 8080
RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/jibby-jabber-user-latest.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production", "/app/spring-boot-application.jar"]