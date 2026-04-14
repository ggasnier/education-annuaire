FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /build

# Copy pom files for dependency caching
COPY pom.xml .
COPY core/pom.xml core/
COPY web/pom.xml web/
COPY shell/pom.xml shell/

# Copy source code (exclude datasets and other non-essential files)
COPY core/src core/src
COPY web/src web/src
COPY shell/src shell/src

# Build the application
RUN mvn -B clean package -DskipTests
ARG VERSION=1.0.1
RUN mkdir -p web/target/extracted && (cd web/target/extracted; jar -xf ../web-${VERSION}.jar)
RUN mkdir -p shell/target/extracted && (cd shell/target/extracted; jar -xf ../shell-${VERSION}.jar)

FROM eclipse-temurin:21-jre-alpine AS web
WORKDIR /app

# Add non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy Spring Boot layers separately for better caching
ARG EXTRACTED=/build/web/target/extracted
COPY --chown=appuser:appgroup --from=build ${EXTRACTED}/BOOT-INF/lib ./lib
COPY --chown=appuser:appgroup --from=build ${EXTRACTED}/META-INF ./META-INF
COPY --chown=appuser:appgroup --from=build ${EXTRACTED}/BOOT-INF/classes ./

# Add metadata
ARG VERSION=1.0.1
LABEL version="${VERSION}" \
    description="Formakoi - Web Service" \
    maintainer="Guillaume Gasnier"

# Switch to non-root user
USER appuser

EXPOSE 8080

# Add healthcheck
HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-cp", ".:lib/*", "com.guillaumegasnier.education.web.WebApplication"]

FROM eclipse-temurin:21-jre-alpine AS shell
WORKDIR /app

# Add non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy Spring Boot layers separately for better caching
ARG EXTRACTED=/build/shell/target/extracted
COPY --chown=appuser:appgroup --from=build ${EXTRACTED}/BOOT-INF/lib ./lib
COPY --chown=appuser:appgroup --from=build ${EXTRACTED}/META-INF ./META-INF
COPY --chown=appuser:appgroup --from=build ${EXTRACTED}/BOOT-INF/classes ./

# Add metadata
ARG VERSION=1.0.1
LABEL version="${VERSION}" \
    description="Formakoi - Shell CLI" \
    maintainer="Guillaume Gasnier"

# Switch to non-root user
USER appuser

ENTRYPOINT ["java", "-cp", ".:lib/*", "com.guillaumegasnier.education.shell.ShellApplication"]