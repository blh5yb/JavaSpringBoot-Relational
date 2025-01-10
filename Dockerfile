#FROM openjdk:17

#FROM ubuntu:20.04
FROM amazoncorretto:17
#FROM public.ecr.aws/lambda/java:17
#FROM adoptopenjdk:17-jre-hotspot
#FROM openjdk:17-jdk-slim
EXPOSE 8080

#RUN apt-get update -y && apt-get install -y \
#    curl \
#    openjdk-17-jdk \
#    openjdk-17-jre \
#    #default-jre \
#    #default-jdk \
#    maven

#COPY ./target/dependency/* /function/
#COPY ./target/spring-docker.jar /functionx
#COPY ./target/spring-docker.jar /var/task

RUN mkdir /app
COPY target/* /app/
ADD pom.xml ./

#ARG JAR_FILE=target/*.jar
#
#COPY ${JAR_FILE} app.jar
#ENV JAVA_HOME /usr/lib/jvm/openjdk-17-jdk/
#RUN export JAVA_HOME
#ENV PATH = "$PATH:$JAVA_HOME/bin"
#ENV JAVA_HOME /usr/lib/jvm/openjdk-11-jdk/
#RUN export JAVA_HOME
#ENV PATH = "$PATH:$JAVA_HOME/bin"


#COPY ./src /app
COPY ./target /app 
# ./target/spring-docker.jar /spring-docker.jar
COPY pom.xml ./

#RUN mvn -v
#RUN mvn package


# Install the AWS Lambda Java runtime interface
#RUN curl -sL https://repo1.maven.org/maven2/com/amazonaws/aws-lambda-java-core/1.2.1/aws-lambda-java-core-1.2.1.jar -o /app/aws-lambda-java-core.jar
#
#
## Install the Spring Cloud AWS dependencies
#RUN curl -sL https://repo1.maven.org/maven2/org/springframework/cloud/spring-cloud-aws-core/2.3.2/spring-cloud-aws-core-2.3.2.jar -o /app/spring-cloud-aws-core.jar && \
#    curl -sL https://repo1.maven.org/maven2/org/springframework/cloud/spring-cloud-aws-context/2.3.2/spring-cloud-aws-context-2.3.2.jar -o /app/spring-cloud-aws-context.jar


#RUN adduser -D user
#RUN chown -R user:user /vol/
#RUN chmod -R 755 /vol/web
#USER user

#RUN useradd -ms /bin/bash user
#RUN chown -R user:root /usr/lib
RUN chmod -R 755 /usr/lib/jvm
#SER user

WORKDIR /app
#ENTRYPOINT [ "/opt/java/openjdk/bin/java", "-cp", "/function/*", "com.amazonaws.services.lambda.runtime.api.client.AWSLambda" ]
ENTRYPOINT ["java", "-jar", "/app/spring-docker.jar"]
#ENTRYPOINT [ "/usr/lib/jvm", "-cp", "./*", "com.amazonaws.services.lambda.runtime.api.client.AWSLambda" ]
CMD ["com.testproductapi.springbootrelational.StreamLambdaHandler::handleRequest"]