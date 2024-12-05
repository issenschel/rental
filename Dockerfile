FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN mkdir -p /userPhoto
RUN mkdir -p /keystore
WORKDIR /
ENTRYPOINT ["java","-jar","/app.jar"]