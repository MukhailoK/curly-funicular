#FROM maven as build
#
#WORKDIR /workspace/app
#
#COPY pom.xml .
#
#COPY src src
#
#RUN mvn clean package
#
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} backend-0.0.1-SNAPSHOT.jar

ENV JWT_SECRET_KEY=${JWT_SECRET_KEY}
ENV MAIL_PASSWORD=${MAIL_PASSWORD}
ENV MYSQL_PASSWORD=${MYSQL_PASSWORD}
ENV MYSQL_USERNAME=${MYSQL_USERNAME}

EXPOSE 8080
ENTRYPOINT ["java","-jar","/backend-0.0.1-SNAPSHOT.jar" ]

#,"-DJWT_SECRET_KEY=$JWT_SECRET_KEY","-DMYSQL_PASSWORD=$MYSQL_PASSWORD"