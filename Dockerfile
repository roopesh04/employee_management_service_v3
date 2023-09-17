# Stage 1: Build the Spring Boot application
FROM maven:3.8.4 AS build
WORKDIR /app

# Copy the Maven POM file
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the source code into the container
COPY src ./src

# Build the application JAR
RUN mvn package -DskipTests

# Stage 2: Create the production image
FROM openjdk:11-jre-slim
WORKDIR /app

# Copy the JAR file built in Stage 1
COPY --from=build /app/target/*.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
