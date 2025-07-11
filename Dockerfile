# 1. Build stage
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# 2. Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Nombre correcto del JAR generado
COPY --from=build /app/target/backendNutriSmart-0.0.1-SNAPSHOT.jar ./app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
