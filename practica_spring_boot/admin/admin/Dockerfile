FROM openjdk:21-jdk-slim
RUN apt-get update && apt-get install -y wget && \
    wget https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-linux-amd64-v0.6.1.tar.gz && \
    tar -xzvf dockerize-linux-amd64-v0.6.1.tar.gz && \
    mv dockerize /usr/local/bin/
ARG JAR_FILE=target/multitools-0.0.1.jar
COPY ${JAR_FILE} app_multitools.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_multitools.jar"]

