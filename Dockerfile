########################################################
# Dockerfile to build lana-webapp
########################################################
FROM paintface07/minimal-java

# ENV CONFIG /media/nosferatu/Storage/workspace/lana-webapp/src/main/resources/config.properties
ENV CONFIG config.properties

RUN java -version
RUN apk add nodejs nodejs-npm curl && node -v && npm -v
RUN npm install -g @angular/cli@1.2.6

RUN git clone https://github.com/Paintface07/lana-webapp.git

WORKDIR /lana-webapp

ENV FRONTEND_ROOT src/main/webapp

RUN cd ${FRONTEND_ROOT} && npm install && npm run-script build
RUN mvn install

COPY ${CONFIG} .

EXPOSE 8080

ENTRYPOINT java -jar target/lana-webapp.jar
