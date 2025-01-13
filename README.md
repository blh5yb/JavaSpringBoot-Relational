# Demo Spring API
This is a demo spring api with some unit tests and swagger api configured

* [Sample dockerized greeting endpoint lambda function deployed to aws api gateway](https://w2p5wlkwe2.execute-api.us-east-2.amazonaws.com/dev/greeting/Name)

## Cmds
`
    mvn -Daws.account=${AWS_ACCOUNT_ID} -Daws.region=${AWS_REGION} clean compile install -Pdocker -Dmaven.test.skip=true dependency:copy-dependencies
    docker-compose build app
`

### test api: http://localhost:8080/
### test db: http://localhost:8080/h2-console # brew install h2 && brew services start h2
### Swagger: http://localhost:8080/swagger-ui/index.html

## Endpoints
### GET /products
### GET /products/{id}
### ### PUT /products/ 
 - req body: {"name": "name", "price": 10, "description": "test description"}
### Patch /products/{id}
 - req body: {"name": "name", "price": 10, "description": "test description"}
### DELETE /products/{id}
### GET /sendMail
 - req body: {"recipient": "recipient@gmail.com", "msgBody": "email msg, "subject: "email subject"}

### Rate Limit
20 per min for dev

## To Do:
 - API Authentication and private endpoints
 - Complete Unit Tests
 - email attachment

