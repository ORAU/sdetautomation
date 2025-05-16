FROM bellsoft/liberica-openjdk-alpine:latest
#Creación de directorio de trabajo
WORKDIR /home/selenium-docker-project
ADD target/docker-resources ./

