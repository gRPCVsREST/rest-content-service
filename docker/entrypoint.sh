#!/bin/bash
export ZIPKIN_HOST=$(route -n | awk '/UG[ \t]/{print $2}')
/envoy.yaml.sh > /envoy.yaml
envoy -c /envoy.yaml --log-path /var/log/envoy.log --service-cluster rest-content-service &
java -jar rest-content-service.jar

