FROM eclipse-temurin:21

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew clean bootJar --no-daemon -x test

EXPOSE 8080

CMD ["sh", "-c", "java -jar build/libs/*.jar"]