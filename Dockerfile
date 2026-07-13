# ==========================
# Build Stage
# ==========================
FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

#RUN chmod +x mvnw

RUN mvn dependency:go-offline
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests


# ==========================
# Runtime Stage
# ==========================
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]