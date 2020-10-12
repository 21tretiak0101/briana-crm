FROM tomcat:9-jdk11

ENV JAVA_OPTS="-Dspring.profiles.active=development"

RUN rm -rf /usr/local/tomcat/webapps/*

COPY ./target/briana-server.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]

