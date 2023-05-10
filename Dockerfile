FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tenpo-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/tenpo-0.0.1-SNAPSHOT.jar"]
