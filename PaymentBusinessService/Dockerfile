FROM openjdk:21-ea-1-jdk-oracle

WORKDIR /app

COPY target/payment-business-service-0.0.1-SNAPSHOT.jar /app/payment-business-service.jar

ENTRYPOINT ["java", "--enable-preview", "-jar", "payment-business-service.jar"]