# Stage 1: build
# Start with maven base image that include jdk-17
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests --no-transfer-progress && rm -rf /root/.m2/repository

# Stage 2: Create image
# Start with base image include jdk17
FROM amazoncorretto:17.0.12-al2023-headless
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
