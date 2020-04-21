FROM nimmis/java-centos:latest

ARG lib_DIR=/opt/app-java/lib/
ARG bin_DIR=/opt/app-java/bin
ARG SNAPSHOT_DIR=target/
ARG SNAPSHOT_NAME=JXC-0.0.1-SNAPSHOT.jar
ARG CONT_PORT_PUBLISH=8090
ARG CONT_VOLUME=/var/lib/app-java/config-app
ARG ENTRYPOINT=/usr/bin/java

MAINTAINER maintainer_name "emmanuelassamoi65@gmail.com"
COPY ${SNAPSHOT_DIR}*.jar ${lib_DIR}
COPY entrypoint.sh ${bin_DIR}
ENTRYPOINT ["${ENTRYPOINT}"]
CMD ["-jar", "${lib_DIR}${SNAPSHOT_NAME}"]
VOLUME ${CONT_VOLUME}
EXPOSE ${CONT_PORT_PUBLISH}
