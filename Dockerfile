FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/*.jar app.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Specify the command to run on container start
ENTRYPOINT ["java", "-jar", "app.jar"]