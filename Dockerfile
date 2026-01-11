FROM eclipse-temurin:25-jdk AS build
WORKDIR /app
RUN apt-get update && apt-get install -y maven
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM tomcat:10.1-jdk25

ENV PORT 8080

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

RUN sed -i 's/port="8005" shutdown="SHUTDOWN"/port="-1" shutdown="SHUTDOWN"/' /usr/local/tomcat/conf/server.xml

CMD ["catalina.sh", "run"]