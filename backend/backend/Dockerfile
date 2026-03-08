# Stage 1: Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy parent pom and module poms first for caching
COPY pom.xml .
COPY cloudinary-common/pom.xml cloudinary-common/
COPY backend/pom.xml backend/

# Download dependencies (cached if poms don't change)
RUN mvn dependency:go-offline -B

# Copy source code
COPY cloudinary-common/src cloudinary-common/src
COPY backend/src backend/src

# Build the project, skip tests
RUN mvn clean package -DskipTests -B

# Stage 2: Run
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/backend/target/*.jar app.jar

EXPOSE 4040

ENTRYPOINT ["java", "-jar", "app.jar"]
