# FROM openjdk:11-jdk-slim
# VOLUME /tmp
# EXPOSE 8080
# RUN mkdir -p /app/
# RUN mkdir -p /app/logs/
# ADD target/Antenas-Service3.0-0.0.1-SNAPSHOT.jar /app/app.jar
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/app.jar"]

# FROM openjdk:11-jdk-slim
# COPY . /app
# WORKDIR /app
# RUN mvn package

#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
RUN mvn -f /home/app/pom.xml install

#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY --from=build /home/app/target/Antenas-Service3.0-0.0.1-SNAPSHOT.jar /usr/local/lib/ANTENAS.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/ANTENAS.jar"]