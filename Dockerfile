FROM openjdk:alpine
ENV POINTCLICK_VERSION 0.0.1-SNAPSHOT
RUN mkdir /usr/share/app
RUN mkdir /usr/share/app/logs
VOLUME /usr/share/app/logs
WORKDIR /usr/share/app/

# set up docker entrypoint script
COPY entrypoint.sh /usr/share/app/entrypoint.sh
RUN chmod +x /usr/share/app/entrypoint.sh

COPY build/libs/pointclick-${POINTCLICK_VERSION}.jar /usr/share/app/pointclick.jar

EXPOSE 8080
# debug port
EXPOSE 5005

ENTRYPOINT /usr/share/app/entrypoint.sh
