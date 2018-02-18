#FROM java:8
FROM envoyproxy/envoy
WORKDIR /
RUN apt update && apt -y install openjdk-8-jdk net-tools
ADD docker/envoy.yaml.sh /
ADD docker/entrypoint.sh /
ADD build/libs/rest-content-service.jar rest-content-service.jar
EXPOSE 80 8080
ENTRYPOINT /entrypoint.sh