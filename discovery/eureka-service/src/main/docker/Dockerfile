FROM java:8-jre-alpine
ARG JAR_FILE
ENV JAR_FILE=${JAR_FILE}
RUN echo "Asia/Seoul" > /etc/timezone
COPY ${JAR_FILE} .
CMD ["/bin/sh", "-c", "exec java ${JAVA_OPTS} -jar ${JAR_FILE}"]