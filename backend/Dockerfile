FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 3000

ENV SPRING_PROFILES_ACTIVE=mysql

ENTRYPOINT ["java", "-jar", "app.jar"]
