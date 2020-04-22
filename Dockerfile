FROM nimmis/java-centos:latest

ARG CONT_PORT_PUBLISH=8090
ARG CONT_VOLUME=/var/lib/app-java/config-app
ARG ENTRYPOINT=/usr/bin/java

MAINTAINER maintainer_name "emmanuelassamoi65@gmail.com"
COPY target/JXC-0.0.1-SNAPSHOT.jar /opt/app-java/lib/
COPY entrypoint.sh /opt/app-java/bin/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/app-java/lib/JXC-0.0.1-SNAPSHOT.jar"]
VOLUME ${CONT_VOLUME}
EXPOSE ${CONT_PORT_PUBLISH}
