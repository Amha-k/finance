# Build Stage
FROM maven:3.9.0 AS build
WORKDIR /app
COPY ../ /app
WORKDIR /app/employee-service
RUN mvn clean install -DskipTests

# Runtime Stage
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/employee-service/target/app.jar /app/
EXPOSE 8180
ENTRYPOINT ["java", "-jar", "app.jar"]