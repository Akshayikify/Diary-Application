FROM eclipse-temurin:17-alpine

WORKDIR /app

COPY pom.xml .
COPY mvnw ./
COPY mvnw.cmd ./
COPY .mvn .mvn
COPY src ./src

RUN if [ -f "./mvnw" ]; then ./mvnw package -DskipTests; else mvn package -DskipTests; fi

FROM eclipse-temurin:17-alpine

WORKDIR /app

COPY pom.xml .
COPY mvnw ./
COPY mvnw.cmd ./
COPY .mvn .mvn
COPY src ./src
RUN apk add --no-cache wget
RUN wget https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip -O /tmp/maven.zip
RUN unzip /tmp/maven.zip -d /opt
ENV MAVEN_HOME=/opt/apache-maven-3.9.6
ENV PATH=$MAVEN_HOME/bin:$PATH

RUN if [ -f "./mvnw" ]; then ./mvnw package -DskipTests; else mvn package -DskipTests; fi