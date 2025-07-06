# ---------- BUILD PROJECT ----------
FROM gradle:8.6-jdk21 AS builder

WORKDIR /app

COPY . .

RUN gradle build --no-daemon

# ---------- RUN PROJECT ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
