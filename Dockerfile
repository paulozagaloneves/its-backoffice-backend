FROM pauloneves/maven-azulzulu-11-alpine AS builder

WORKDIR /data
RUN mkdir -p src
COPY pom.xml ./
COPY src ./src/
RUN mvn package
ARG APPJAR=target/*.jar
RUN cp ${APPJAR} app.jar
#RUN ls


#FROM azul/zulu-openjdk-alpine:11
FROM adoptopenjdk/openjdk11-openj9:jre-11.0.7_10_openj9-0.20.0-alpine
VOLUME /tmp
COPY --from=builder /data/app.jar ./
EXPOSE 8080
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]