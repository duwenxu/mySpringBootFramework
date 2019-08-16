#源镜像
FROM hub.c.163.com/library/java:8-alpine

MAINTAINER zeroJun xxx@example.com
ADD target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]