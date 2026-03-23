FROM eclipse-temurin:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file to download dependencies
COPY pom.xml .

# Download dependencies (use a separate layer for caching)
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline -B

# Copy source code
COPY src .

# Package the application
RUN mvn clean package -DskipTests

# Command to run the application
CMD ["java", "-jar", "target/diary-application-0.0.1-SNAPSHOT.jar"]