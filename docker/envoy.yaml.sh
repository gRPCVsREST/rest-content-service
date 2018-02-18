#!/usr/bin/env bash
cat <<YAML
static_resources:
  listeners:
  - address:
      socket_address:
        address: 0.0.0.0
        port_value: 80
    filter_chains:
    - filters:
         config:
          generate_request_id: true
          tracing:
            operation_name: egress
          codec_type: auto
          stat_prefix: ingress_http
          route_config:
            name: local_route
            virtual_hosts:
            - name: backend
              domains:
              - "*"
              routes:
              - match:
                  prefix: "/"
                route:
                  cluster: local_service
                decorator:
                  operation: checkAvailability
          http_filters:
          - name: envoy.router
            config: {}
  clusters:
  - name: local_service
    connect_timeout: 0.25s
    type: strict_dns
    lb_policy: round_robin
    hosts:
    - socket_address:
        address: 127.0.0.1
        port_value: 8080
  - name: zipkin
    connect_timeout: 1s
    type: strict_dns
    lb_policy: round_robin
    hosts:
    - socket_address:
        address: $ZIPKIN_HOST
        port_value: 9411
tracing:
  http:
    name: envoy.zipkin
    config:
      collector_cluster: zipkin
      collector_endpoint: "/api/v1/spans"
admin:
  access_log_path: "/var/log/envoy_access.log"
  address:
    socket_address:
      address: 0.0.0.0
      port_value: 8081
YAML