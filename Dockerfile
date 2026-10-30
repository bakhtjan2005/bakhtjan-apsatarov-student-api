# ===== Stage 1: сборка (JDK + Gradle wrapper) =====
FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace
COPY gradlew settings.gradle build.gradle ./
COPY gradle gradle
RUN chmod +x gradlew && ./gradlew --no-daemon --version
COPY src src
RUN ./gradlew --no-daemon clean bootJar -x test

# ===== Stage 2: runtime (только JRE) =====
FROM eclipse-temurin:17-jre
LABEL org.opencontainers.image.title="bakhtjan-apsatarov-student-api" \
      org.opencontainers.image.authors="Bakhtjan Apsatarov" \
      org.opencontainers.image.version="v1.0-bakhtjan-apsatarov"
WORKDIR /app
RUN useradd --system --no-create-home appuser
COPY --from=build /workspace/build/libs/app.jar app.jar
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
