FROM bellsoft/liberica-openjdk-alpine:latest
#Creación de directorio de trabajo
WORKDIR /home/selenium-docker-project
ADD target/docker-resources ./

ENTRYPOINT java -cp 'libs/*' \
            -Dselenium-grid-enabled=true\
            -Dselenium-grid-hubHost=${HUB_HOST}\
            -Dbrowser=${BROWSER}\
            org.testng.TestNG test-suites/${TEST_SUITE}
            -threadcount ${THREADS}
