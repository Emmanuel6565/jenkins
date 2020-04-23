FROM openjdk:8-jdk-alpine
MAINTAINER maintainer_name "emmanuelassamoi65@gmail.com"

RUN addgroup -S app-java && adduser -S app-java -G app-java
USER app-java

ARG JAR_FILE=JXC-0.0.1-SNAPSHOT.jar
ARG PROJECT_HOME=/Users/macbookpro/.jenkins/workspace/ChaineCICD
ARG CONT_PORT_PUBLISH=8090
ARG CONT_VOLUME=/var/lib/app-java/config-app

COPY ${PROJECT_HOME}/${JAR_FILE} /opt/app-java/lib/app.jar
COPY ${PROJECT_HOME}/entrypoint.sh /opt/app-java/lib/
VOLUME ${CONT_VOLUME}
EXPOSE ${CONT_PORT_PUBLISH}
ENTRYPOINT ["/bin/sh"]
CMD ["/opt/app-java/lib/entrypoint.sh"]

