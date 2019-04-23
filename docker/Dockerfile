FROM java
EXPOSE 8080
ADD ./aws-es-service-0.0.1.jar aws-es-service-0.0.1.jar
RUN bash -c 'touch /aws-es-service-0.0.1.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/aws-es-service-0.0.1.jar"]
