# spring boot grafana prometheus

## Overview

spring boot grafana prometheus is an application that enables users to sale products and manage their sales. The application is divided into two main services: Stock and payment. The Stock service is responsible for managing products and their stock levels, while the payment service is responsible for processing payments. The application also includes a frontend to manages logs and metrics.


#### Backend services
![architecture](screenshots/micro-services-global-architecture.drawio.png)

### Technologies

- Spring Boot 3
- Spring Data JPA
- Docker
- Docker-compose
- Prometheus
- Grafana
- Loki
- Tempo
- Zipkin

### Getting started
#### build payment service
- ```cd payment-service```
- ```mvn clean install```

#### build stock service
- ```cd stock-service```
- ```SERVICE_PAYMENT_HOST=localhost SERVICE_PAYMENT_PORT=8080 mvn clean install```

#### Start services
- ```docker-compose up```

### Services url
- [Grafana dashboard: http://localhost:3000](http://localhost:3000)
- [Prometheus: http://localhost:9090](http://localhost:9090)
- [Loki: http://localhost:3100](http://localhost:3100)
- [Tempo: http://localhost:16686](http://localhost:16686)
- [Zipkin: http://localhost:9411](http://localhost:9411)
- [Stock service: http://localhost:8080](http://localhost:8080)
- [Payment service: http://localhost:8081](http://localhost:8081)

### Postman collection
- [Postman collection: ./spring grafana.postman_collection.json](./spring%20grafana.postman_collection.json)
