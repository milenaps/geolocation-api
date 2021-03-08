FROM gradle:4.9-jdk8-alpine as builder

COPY --chown=gradle:gradle . /app

WORKDIR /app

RUN gradle build -x test

FROM openjdk:8-jdk-alpine

COPY --from=builder /app/build/libs/springboot-mongo-api-1.0.0-SNAPSHOT.jar /opt/api.jar
ARG JAVA_OPTS
ENV JAVA_OPTS ${JAVA_OPTS}
CMD java $JAVA_OPTS -jar /opt/api.jar