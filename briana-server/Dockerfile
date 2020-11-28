FROM tomcat:9-jdk11

ARG aws_access_key=""
ARG aws_secret_key=""
ARG jwt_private_key=""

ENV JAVA_OPTS="-Dspring.profiles.active=development"
ENV AWS_ACCESS_KEY=${aws_access_key}
ENV AWS_SECRET_KEY=${aws_secret_key}
ENV JWT_PRIVATE_KEY=${aws_secret_key}

RUN rm -rf /usr/local/tomcat/webapps/*

COPY ./target/briana-server.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]

