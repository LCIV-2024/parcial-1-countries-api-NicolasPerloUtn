FROM openjdk:17-jdk-alpine
COPY target/lciii-scaffolding-0.0.1-SNAPSHOT.jar parcial-app.jar
ENTRYPOINT [ "java", "-jar", "parcial-app.jar" ]
EXPOSE 8080