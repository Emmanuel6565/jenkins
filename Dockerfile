# Version: 0.0.1
FROM ubuntu:latest
MAINTAINER maintainer_name "emmanuelassamoi65@gmail.com"
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk openjdk-8-jre
