# Use the official Maven image to build the application
FROM maven:3.8-openjdk-17 AS builder

WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn install -DskipTests

# Use the official OpenJDK image for the runtime container
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=builder /app/target/smartcity-0.0.1-SNAPSHOT.jar smartcity-app.jar

# Expose the port your Spring Boot application listens on
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "smartcity-app.jar"]
