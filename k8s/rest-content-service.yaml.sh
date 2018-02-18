#!/bin/bash

cat <<YAML
apiVersion: apps/v1beta1
kind: Deployment
metadata:
  name: rest-content-a
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: rest-content-a
    spec:
      containers:
        - name: rest-content-service
          image: gcr.io/$GCP_PROJECT/rest-content-service:latest
          imagePullPolicy: Always
          ports:
            - containerPort: 8080
          env:
            - name: CONTENT_RESOURCE
              value: "$CONTENT_URL_A"
            - name: spring_application_name
              value: "rest-content-a"
            - name: foobar
              value: "$(date +%s)"
---
apiVersion: v1
kind: Service
metadata:
  name: rest-content-a
spec:
  type: NodePort
  selector:
    app: rest-content-a
  ports:
   - port: 8080
     protocol: TCP
---
apiVersion: apps/v1beta1
kind: Deployment
metadata:
  name: rest-content-b
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: rest-content-b
    spec:
      containers:
        - name: rest-content-service
          image: gcr.io/$GCP_PROJECT/rest-content-service:latest
          imagePullPolicy: Always
          ports:
            - containerPort: 8080
          env:
            - name: CONTENT_RESOURCE
              value: "$CONTENT_URL_B"
            - name: spring_application_name
              value: "rest-content-b"
            - name: foobar
              value: "$(date +%s)"
---
apiVersion: v1
kind: Service
metadata:
  name: rest-content-b
spec:
  type: NodePort
  selector:
    app: rest-content-b
  ports:
   - port: 8080
     protocol: TCP
---

YAML