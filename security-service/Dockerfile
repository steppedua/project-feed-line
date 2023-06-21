FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
ARG JAR_FILE=build/libs/security-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} security-service.jar
ENTRYPOINT ["java","-jar","security-service.jar"]