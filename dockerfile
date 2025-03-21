# Use a compatible OpenJDK image
FROM openjdk:alpine

# Set the working directory
WORKDIR /app

# Install wget and tar
RUN apk add --no-cache wget tar

# Create necessary directories
RUN mkdir -p /app/lib /app/out

# Download and extract MySQL connector
RUN wget https://cdn.mysql.com//Downloads/Connector-J/mysql-connector-j-9.2.0.tar.gz -O /tmp/mysql-connector.tar.gz \
    && tar -xzf /tmp/mysql-connector.tar.gz -C /app/lib --strip-components=1 --wildcards '*/mysql-connector-j-9.2.0.jar' \
    && rm /tmp/mysql-connector.tar.gz

# Copy source files
COPY src /app/src

# Copy the .env file
COPY .env /app/.env

# Compile Java files
RUN find /app/src -name "*.java" | xargs javac -cp "/app/lib/mysql-connector-j-9.2.0.jar" -d /app/out

# Set the entry point to run the application
CMD ["java", "-cp", "/app/out:/app/lib/mysql-connector-j-9.2.0.jar", "Main"]