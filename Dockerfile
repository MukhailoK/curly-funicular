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

ENV JWT_SECRET_KEY=${JWT_SECRET_KEY}
ENV MAIL_PASSWORD=${MAIL_PASSWORD}
ENV MYSQL_PASSWORD=${MYSQL_PASSWORD}
ENV MYSQL_USERNAME=${MYSQL_USERNAME}


ENTRYPOINT ["java","-jar","/backend-0.0.1-SNAPSHOT.jar" ]