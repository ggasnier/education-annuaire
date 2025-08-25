FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/*shell*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
