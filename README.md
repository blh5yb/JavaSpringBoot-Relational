# Demo Spring API
This is a demo spring api with some unit tests and swagger api configured

* [Sample dockerized greeting endpoint lambda function deployed to aws api gateway](https://w2p5wlkwe2.execute-api.us-east-2.amazonaws.com/dev/greeting/Name)

## Features
 - Mongo DB Schema
 - Unit Tests
 - Rate Limits (20 req per min)
 - Dockerized (with docker-compose)
 - AWS Lambda docker configuration
 - AWS Lambda deployed to API Gateway 

## Programming Languages, Frameworks and Platforms
 - Java
 - PostgreSQL
 - Spring Boot
 - Docker

## Cmds
```
    mvn -Daws.account=${AWS_ACCOUNT_ID} -Daws.region=${AWS_REGION} clean compile install -Pdocker -Dmaven.test.skip=true dependency:copy-dependencies  
    docker-compose build app
```

### test api: http://localhost:8080/
### test db: http://localhost:8080/h2-console # brew install h2 && brew services start h2
### Swagger: http://localhost:8080/swagger-ui/index.html

## Swagger UI Docs
http://localhost:8080/swagger-ui/index.html

## PRODUCT ENDPOINTS
### GET /products
### GET /products/{id}
### PUT /products/{id}
 - req body: {"name": "name", "price": 10, "description": "test description"}
### Patch /products/{id}
 - req body: {"name": "name", "price": 10, "description": "test description"}
### DELETE /products/{id}

## EMAIL ENDPOINTS
### GET /sendMail
 - req body: {"recipient": "recipient@gmail.com", "msgBody": "email msg, "subject: "email subject"}

### Rate Limit
20 per min for dev

## In Progress
 - Parameter validation aspect
 - Complete Unit Tests
 - App Security


## To Do:
 - API Authentication + Middleware (aspect/ interceptor) and private endpoints
 - email attachment
 - Annotate code

