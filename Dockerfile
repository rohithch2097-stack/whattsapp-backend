FROM eclipse-temurin:17

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew clean build --no-daemon -x test

EXPOSE 8080

CMD ["sh", "-c", "java -jar build/libs/*.jar"]