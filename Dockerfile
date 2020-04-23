FROM openjdk:8-jdk-alpine
MAINTAINER maintainer_name "emmanuelassamoi65@gmail.com"

RUN addgroup -S app-java && adduser -S app-java -G app-java
USER app-java

ARG JAR_FILE=Project.jar
ARG CONT_PORT_PUBLISH=8090
ARG CONT_VOLUME=/var/lib/app-java/config-app

COPY ${JAR_FILE} /opt/app-java/lib/app.jar
VOLUME ${CONT_VOLUME}
EXPOSE ${CONT_PORT_PUBLISH}
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/app-java/lib/app.jar"]

#COPY /Users/macbookpro/.jenkins/workspace/ChaineCICD/entrypoint.sh /opt/app-java/lib/
#CMD ["/opt/app-java/lib/entrypoint.sh"]

