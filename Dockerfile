FROM eclipse-temurin:17-jdk-slim
    
    # Set the working directory inside the container
    WORKDIR /app
    
    # Copy the application code to the container
    COPY . .
    
    # Specify the command to run on container start
    CMD ["echo", "Hello, Docker!"]