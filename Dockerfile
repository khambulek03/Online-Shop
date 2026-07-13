# ==========================
# Build Stage
# ==========================
FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

RUN chmod +x mvnw

# Cache Maven dependencies
RUN ./mvnw dependency:go-offline

COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests


# ==========================
# Test Stage
# ==========================
FROM build AS test

RUN ./mvnw test


# ==========================
# Runtime Stage
# ==========================
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

RUN useradd -ms /bin/bash spring
USER spring

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]