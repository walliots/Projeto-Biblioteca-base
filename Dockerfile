FROM openjdk:11-jdk
MAINTAINER wall
WORKDIR /app
COPY target/projeto-spring-boot-1.0-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]