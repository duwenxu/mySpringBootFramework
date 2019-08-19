#源镜像
FROM hub.c.163.com/library/java:8-alpine

#维护者信息
MAINTAINER mayi 1462289331@qq.com

#添加
ADD target/*.jar app.jar

#对外暴露的端口
EXPOSE 80,8080,9000

#容器启动时要运行的命令
ENTRYPOINT ["java", "-jar", "/app.jar"]

