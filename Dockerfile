FROM nimmis/java-centos:latest

ARG lib_DIR=/opt/app-java/lib/
ARG bin_DIR=/opt/app-java/bin
ARG SNAPSHOT_DIR=target/
ARG SNAPSHOT_NAME
ARG CONT_PORT_PUBLISH
ARG CONT_VOLUME=/var/lib/app-java/config-app
ARG ENTRYPOINT=/usr/bin/java

MAINTAINER maintainer_name "emmanuelassamoi65@gmail.com"
COPY ${SNAPSHOT_DIR}*.jar ${lib_DIR}
COPY entrypoint.sh ${bin_DIR}
ENTRYPOINT ["${ENTRYPOINT}"]
CMD ["-jar", "${lib_DIR}${SNAPSHOT_NAME}"]
VOLUME ${CONT_VOLUME}
EXPOSE ${CONT_PORT_PUBLISH}
