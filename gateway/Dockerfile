FROM openjdk:8
EXPOSE 8000
ADD target/docker-gateway.jar docker-gateway.jar
ENTRYPOINT ["java","-jar","/docker-gateway.jar"]