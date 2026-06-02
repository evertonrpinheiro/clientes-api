# Stage 1: Build stage
FROM maven:3.9-eclipse-temurin-17-alpine AS builder

WORKDIR /build

# Copy only the pom.xml first to leverage Docker cache for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: Run stage
FROM eclipse-temurin:17-jre-alpine

# Create a non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

# Copy only the built artifact from the builder stage
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

# Use exec form for ENTRYPOINT to allow graceful shutdown (SIGTERM)
ENTRYPOINT ["java", "-jar", "app.jar"]
