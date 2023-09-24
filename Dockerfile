FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=target/shop_fashion-0.0.1-SNAPSHOT.jar

WORKDIR /app

COPY ${JAR_FILE} /app/api.jar
ENTRYPOINT java -jar /app/api.jar