FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /build
COPY ../../pom.xml ../../
COPY ../core ../core
COPY . .
#RUN mvn -B dependency:go-offline
#COPY src ./src
RUN mvn -B -pl clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /build/target/*web*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract
ENTRYPOINT ["java", "-cp", "BOOT-INF/classes:BOOT-INF/lib/*", "com.guillaumegasnier.education.web.WebApplication"]

EXPOSE 8080
