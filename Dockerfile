FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/edadi-0.0.1.jar edadi-0.0.1.jar
EXPOSE 5000
CMD ["java","-jar","edadi-0.0.1.jar"]