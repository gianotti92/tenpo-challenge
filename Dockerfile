FROM openjdk:17-jdk-slim

ENV SPRING_PROFILES_ACTIVE=local

ENV db.port 5432
ENV db.name postgres
ENV db.host postgres
ENV db.user user
ENV db.password pass
ENV db.schema public
ENV base.url http://service-b:8081


ARG JAR_FILE=target/*.jar

EXPOSE 8080

COPY ${JAR_FILE} tenpo-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/tenpo-0.0.1-SNAPSHOT.jar"]
