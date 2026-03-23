FROM maven:3-eclipse-temurin-17-alpine AS builder

WORKDIR /app

COPY pom.xml .
COPY mvnw ./
COPY mvnw.cmd ./
COPY .mvn .mvn
COPY src ./src
RUN ./mvnw wrapper:wrapper -Dmaven=3.9.6
RUN if [ -f "./mvnw" ]; then chmod +x mvnw && ./mvnw package -DskipTests; else mvn package -DskipTests; fi

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]