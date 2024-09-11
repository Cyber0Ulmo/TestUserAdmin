
FROM openjdk:21-slim AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

RUN mvn clean install

FROM openjdk:21-slim

COPY --from=build /app/target/Empiricus-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
