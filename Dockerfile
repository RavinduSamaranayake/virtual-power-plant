FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/virtual-power-plant-0.0.1-SNAPSHOT.jar ./
ENTRYPOINT ["java","-jar","virtual-power-plant-0.0.1-SNAPSHOT.jar"]