FROM jenkins/jenkins:2.146-alpine
USER root
RUN apk add --no-cache --update openssl
RUN echo 2.146 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state
RUN echo 2.146 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion
USER jenkins
COPY --chown=jenkins:jenkins plugins.txt /var/jenkins_home/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /var/jenkins_home/plugins.txt
COPY groovy/ /usr/share/jenkins/ref/init.groovy.d/