FROM openjdk:17-alpine
WORKDIR /app
RUN apk add --no-cache curl
COPY target/api-gateway-0.0.1-SNAPSHOT.jar /app/api-gateway.jar
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]