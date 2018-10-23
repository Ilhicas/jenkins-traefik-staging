FROM jenkinsci/slave:3.26-1
USER root
COPY get-docker.sh /get-docker.sh
RUN sh /get-docker.sh
RUN usermod -aG docker jenkins
RUN curl -L "https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
RUN chmod +x /usr/local/bin/docker-compose
USER ${user}