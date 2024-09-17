# Use the official OpenJDK base image
FROM openjdk:21-ea-1-jdk-oracle

WORKDIR /app

COPY target/payment-dao-service-0.0.1-SNAPSHOT.jar /app/payment-dao-service.jar

ENTRYPOINT ["java", "--enable-preview", "-jar", "payment-dao-service.jar"]
