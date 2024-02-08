FROM openjdk:17

ENV DB_HOST=$DB_HOST
ENV DB_PORT=$DB_PORT
ENV DB_NAME=$DB_NAME
ENV DB_USERNAME=$DB_USERNAME
ENV DB_PASSWORD=$DB_PASSWORD
ENV MAIL_PASSWORD=$MAIL_PASSWORD
ENV JWT_SECRET_KEY=$JWT_SECRET_KEY
ENV SWITCH_SEND_MAIL=$SWITCH_SEND_MAIL
ENV SERVICE_MAIL=$SERVICE_MAIL
ENV JWT_LIVE_TIME=$JWT_LIVE_TIME
ENV ACTUATOR_USERNAME=$ACTUATOR_USERNAME
ENV ACTUATOR_PASSWORD=$ACTUATOR_PASSWORD

COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080:8080