FROM openjdk:21-ea-1-jdk-oracle

WORKDIR /app

COPY target/scheduler-0.0.1-SNAPSHOT.jar /app/scheduler.jar

ENTRYPOINT ["java", "--enable-preview", "-jar", "scheduler.jar"]