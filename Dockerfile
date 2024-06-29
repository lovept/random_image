# 使用官方的Java运行环境作为基础镜像
FROM maven:3.8.4-openjdk-17

# 设置工作目录
WORKDIR /app

# 将Maven的配置文件复制到Docker镜像中
COPY ./pom.xml ./pom.xml

# 使用Maven下载依赖项，利用Docker的缓存机制，如果pom.xml文件没有改变，就不会重新下载
RUN mvn dependency:go-offline -B

# 将项目的源代码复制到Docker镜像中
COPY ./src ./src

# 使用Maven打包应用
RUN mvn package

# 将打包好的jar文件复制到根目录下，并删除无用的文件
RUN mv /app/target/*.jar app.jar && rm -rf /app/src && rm -rf /app/pom.xml && rm -rf /app/target

# 暴露端口
EXPOSE 8081

# 运行应用
ENTRYPOINT ["java","-jar","/app/app.jar"]


# docker build -t random_image .
# docker run -dp 8081:8081 random_image