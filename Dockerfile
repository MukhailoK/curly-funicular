FROM maven as build

WORKDIR /workspace/app

COPY pom.xml .

COPY src src

RUN mvn clean package

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jre-alpine
ARG DEPENDENCY=/workspace/app/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib

COPY --from=build ${DEPENDENCY}/META-INF app/META-INF

COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app


ENTRYPOINT ["java","-cp","app:app/lib/*","com/ait/grooming/GroomingApplication" ]

#,"-DJWT_SECRET_KEY=$JWT_SECRET_KEY","-DMYSQL_PASSWORD=$MYSQL_PASSWORD"