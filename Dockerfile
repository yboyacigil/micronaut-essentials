FROM openjdk:17-alpine

COPY classes /app/classes
COPY dependency/* /app/libs/

EXPOSE 8080
CMD ["java", "-cp", "/app/libs/*:/app/classes/", "com.yboyacigil.micronaut.essentials.Application"]