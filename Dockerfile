#
##build jdk
#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#
#
##Nginx
#FROM nginx
#EXPOSE 80
#EXPOSE 443