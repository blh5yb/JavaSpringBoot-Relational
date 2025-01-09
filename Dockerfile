FROM openjdk:19
EXPOSE 8080
ADD target/spring-docker.jar spring-docker.jar

#ARG JAR_FILE=target/*.jar
#
#COPY ${JAR_FILE} app.jar

RUN mkdir /app
WORKDIR /app
COPY ./src /app
COPY ./target/spring-docker.jar /spring-docker.jar

#RUN adduser -D user
#RUN chown -R user:user /vol/
#RUN chmod -R 755 /vol/web
#USER user


ENTRYPOINT ["java", "-jar", "/spring-docker.jar"]