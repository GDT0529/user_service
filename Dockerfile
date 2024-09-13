FROM openjdk:21
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/user_service-0.0.1-SNAPSHOT.jar finalproject.jar
EXPOSE 8083
ENTRYPOINT exec java $JAVA_OPTS -jar finalproject.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar finalproject.jar
