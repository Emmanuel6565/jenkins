FROM nimmis/java-centos
MAINTAINER maintainer_name "emmanuelassamoi65@gmail.com"

ARG JAR_FILE=JXC-0.0.1-SNAPSHOT.jar
ARG SCRIPT_RUN=entrypoint.sh
ARG CONT_PORT_PUBLISH=8090
ARG CONT_VOLUME=/var/lib/app-java/config-app

COPY target/${JAR_FILE} /opt/app-java/lib/app.jar
COPY ${SCRIPT_RUN} /opt/app-java/bin/entrypoint.sh
VOLUME ${CONT_VOLUME}
EXPOSE ${CONT_PORT_PUBLISH}
ENTRYPOINT ["/bin/bash"]
CMD ["/opt/app-java/bin/entrypoint.sh"]


