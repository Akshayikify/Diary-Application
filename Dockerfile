FROM eclipse-temurin:17-alpine AS builder
WORKDIR /app

COPY pom.xml .
COPY mvnw ./
COPY mvnw.cmd ./
COPY src ./src

RUN if [ -f "./mvnw" ]; then ./mvnw package -DskipTests; else mvn package -DskipTests; fi

FROM eclipse-temurin:17-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]