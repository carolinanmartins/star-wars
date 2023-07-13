FROM openjdk:17-jdk-alpine AS build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install

FROM openjdk:17-jdk-alpine
ARG JAR_FILE=/workspace/app/target/*.jar
COPY --from=build ${JAR_FILE} star-wars.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "/star-wars.jar"]