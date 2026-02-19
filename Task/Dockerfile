FROM gradle:8.5-jdk21 AS base
WORKDIR /home/gradle/project

FROM base AS deps
COPY gradlew ./
COPY gradle ./gradle
COPY settings.gradle build.gradle ./

RUN chmod +x ./gradlew
RUN ./gradlew dependencies --no-daemon

FROM base AS build
COPY --from=deps /home/gradle/project /home/gradle/project
COPY src ./src
RUN ./gradlew bootJar -x test --no-daemon

FROM eclipse-temurin:21-jre AS runner
LABEL authors="Oussama"
WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
