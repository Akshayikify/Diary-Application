FROM eclipse-temurin:17-alpine AS builder
WORKDIR /app

COPY .mvn ./.mvn
COPY mvnw ./mvnw
COPY mvnw.cmd ./mvnw.cmd
COPY pom.xml ./pom.xml
COPY src ./src

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
