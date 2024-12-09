FROM maven:3.9.9-eclipse-temurin-23

ARG APP_DIR=/app
ARG PORT=4000

WORKDIR ${APP_DIR}

COPY .mvn .mvn
COPY src src
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .

RUN mvn package -Dmaven.test.skip=true

ENV SERVER_PORT=${PORT}

EXPOSE ${SERVER_PORT}

ENTRYPOINT [ "java", "-jar", "target/vttp5_ssf_day15l-0.0.1-SNAPSHOT.jar"]