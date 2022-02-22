FROM openjdk:11

COPY target/insert-app.jar /app.jar

EXPOSE 8080/tcp

ENTRYPOINT ["java", "-jar", "/app.jar"]