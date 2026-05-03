FROM gradle:8.14.4-jdk21 AS build

WORKDIR /app

COPY . .

RUN gradle clean bootJar --no-daemon -x test

FROM eclipse-temurin:21

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]