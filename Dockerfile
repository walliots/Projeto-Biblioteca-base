FROM openjdk:11-jdk
WORKDIR /app
COPY ./target/Projeto-Biblioteca-base.jar /application.jar
EXPOSE 8080

ENTRYPOINT java -jar application.jar