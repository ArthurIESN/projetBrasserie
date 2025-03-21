# Use a compatible OpenJDK image
FROM openjdk:alpine

# Set the working directory
WORKDIR /app

# Create necessary directories
RUN mkdir -p /app/lib /app/out

# Copy source files
COPY src /app/src

# Copy MySQL connector
COPY lib/mysql-connector-j-9.2.0.jar /app/lib/mysql-connector-j-9.2.0.jar

# Copy the .env file
COPY .env /app/.env

# Compile Java files
RUN find /app/src -name "*.java" | xargs javac -cp "/app/lib/mysql-connector-j-9.2.0.jar" -d /app/out

# Set the entry point to run the application
CMD ["java", "-cp", "/app/out:/app/lib/mysql-connector-j-9.2.0.jar", "Main"]