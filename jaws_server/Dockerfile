
FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM bellsoft/liberica-openjdk-debian:17
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
COPY ./src/main/resources/static/images/for_saving_place.txt /app/src/main/resources/static/images/
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]