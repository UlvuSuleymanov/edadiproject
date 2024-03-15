FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/edauni-0.0.1.jar edauni-0.0.1.jar
EXPOSE 5000
CMD ["java","-jar","edauni-0.0.1.jar"]