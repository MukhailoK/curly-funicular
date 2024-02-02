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
#
#FROM eclipse-temurin:17-jre-alpine
#
#ARG DEPENDENCY=/workspace/app/target/dependency
#
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#
#COPY --from=build ${DEPENDENCY}/META-INF app/META-INF
#
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#
#
#ENTRYPOINT ["java","-cp","app:app/lib/*","com/ait/grooming/GroomingApplication"]
#
##,"-DJWT_SECRET_KEY=$JWT_SECRET_KEY","-DMYSQL_PASSWORD=$MYSQL_PASSWORD"

FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} backend-0.0.1-SNAPSHOT.jar

ENV DB_HOST=$DB_HOST
ENV DB_PORT=$DB_PORT
ENV DB_NAME=$DB_NAME
ENV DB_USERNAME=$DB_USERNAME
ENV DB_PASSWORD=$DB_PASSWORD
ENV MAIL_PASSWORD=$MAIL_PASSWORD
ENV JWT_SECRET_KEY=$JWT_SECRET_KEY

ENTRYPOINT ["java","-jar","/backend-0.0.1-SNAPSHOT.jar" ]
