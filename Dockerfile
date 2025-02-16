# First Stage: Build the JAR using Maven
FROM maven:3.8.6-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy only pom.xml and download dependencies first (to optimize caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the project files
COPY src ./src

# Build the project (this generates the JAR file) and completely skip tests
RUN mvn clean package -DskipTests -Dmaven.test.skip=true

# Second Stage: Create a lightweight runtime image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
