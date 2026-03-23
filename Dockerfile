FROM eclipse-temurin:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application's JAR file into the container
COPY target/*.jar app.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Define the command to run when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]