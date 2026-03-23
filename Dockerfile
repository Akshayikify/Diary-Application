FROM docker.io/library/eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY pom.xml .
COPY mvnw ./
COPY mvnw.cmd ./
COPY .mvn .mvn
COPY src ./src

# Install maven
RUN apk update && \
    apk add --no-cache wget unzip && \
    wget https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip -O /tmp/maven.zip && \
    unzip /tmp/maven.zip -d /opt && \
    ln -s /opt/apache-maven-3.9.6 /usr/local/maven && \
    ln -s /usr/local/maven/bin/mvn /usr/local/bin/mvn

ENV MAVEN_HOME=/usr/local/maven
ENV MAVEN_CONFIG=/root/.m2
ENV PATH=$MAVEN_HOME/bin:$PATH

RUN mvn -v

RUN ./mvnw package -DskipTests