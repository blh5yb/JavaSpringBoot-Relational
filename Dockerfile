
# Install PostgreSQL client
#FROM openjdk:17-jdk-slim
#RUN apt-get update && apt-get install -y postgresql-client

FROM public.ecr.aws/lambda/java:17

EXPOSE 8080
COPY target/spring-docker.jar ${LAMBDA_TASK_ROOT}
ADD target .
COPY target/classes ${LAMBDA_TASK_ROOT}
COPY target/dependency/* ${LAMBDA_TASK_ROOT}/lib/

RUN chmod -R 755 /var/lang/bin

# entrypoint for local testing
#ENTRYPOINT [ "java", "-jar", "spring-docker.jar" ]
CMD ["com.testproductapi.springbootrelational.StreamLambdaHandler::handleRequest"]