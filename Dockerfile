# Use the official OpenJDK 17 as the base image
FROM gradle:jdk17-jammy

# Set environment variables for configuration
ENV APP_HOME=/app \
    APP_PORT=8080

# Set the working directory inside the container
WORKDIR $APP_HOME

# Copy the Gradle build files to the container
COPY build.gradle.kts settings.gradle.kts $APP_HOME/

# Copy the source code to the container
COPY src $APP_HOME/src

# Run Gradle wrapper to download and install Gradle
RUN gradle --version

# Build the application using Gradle
RUN gradle clean build

# Expose the application port
EXPOSE $APP_PORT

# Set the entrypoint command to run the application
ENTRYPOINT ["java", "-jar", "build/libs/ProgressSoft-0.0.1-SNAPSHOT.jar"]

# Add labels for better maintainability
LABEL maintainer="Kamwa Fosso Arcel Raulain <kraulain@gmail.com>"
LABEL version="1.0"
LABEL description="Docker image for Spring Boot application using Java 17 and Gradle"