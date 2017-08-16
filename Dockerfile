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

ENV FRONTEND_ROOT /lana-webapp/src/main/webapp/lana-frontend
ENV PROJECT_ROOT /lana-webapp

RUN cd ${FRONTEND_ROOT} && npm install && ng build --base-href /lana/
RUN ls -a ${FRONTEND_ROOT}/dist
RUN cd ${PROJECT_ROOT}/ && mvn install

COPY ${CONFIG} /

EXPOSE 8080

ENTRYPOINT java -jar ${PROJECT_ROOT}/target/lana-webapp.jar
