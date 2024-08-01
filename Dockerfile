FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/propiedad-direct-0.0.1-SNAPSHOT.jar app.jar
COPY .env .env
CMD ["java", "-jar", "app.jar"]
