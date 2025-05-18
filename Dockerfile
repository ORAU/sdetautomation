FROM bellsoft/liberica-openjdk-alpine:latest
#Creación de directorio de trabajo
WORKDIR /home/selenium-docker-project
#Se instalará curl y jq para ejecutar el batch que extraera el estado del grid hub y ejecutar luego los test
RUN apk add curl jq
ADD target/docker-resources ./
ADD runner.sh runner.sh
#Se realiza la llamada del archivo .bash que se encargará de verificar el estado del selenium grid hub
#si esta listo, entonces luego realizará la ejecución del comando que invoca la llamada de los test cases
#habiendose creado los contenedores correctamente
ENTRYPOINT sh runner.sh
#En caso de no tener ningun script u otro archivo .bash, entonces ejecutar el comando de abajo
#Se comentó porque arriba ya se ejecuta el comando RUN sh runner.sh :
#ENTRYPOINT java -cp 'libs/*' \
 #           -Dselenium-grid-enabled=true\
  #          -Dselenium-grid-hubHost=${HUB_HOST}\
   #         -Dbrowser=${BROWSER}\
    #        org.testng.TestNG test-suites/${TEST_SUITE}
     #       -threadcount ${THREADS}
