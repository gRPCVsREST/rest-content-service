FROM java:8
WORKDIR /
ADD build/libs/rest-content-service.jar rest-content-service.jar
EXPOSE 8080
CMD java -jar rest-content-service.jar