# Base image
FROM openjdk:23-jdk-slim

# JAR 파일을 컨테이너 안에 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} kopmorning.jar

# 포트 오픈
EXPOSE 8080

# 앱 실행
ENTRYPOINT ["java","-jar","/app.jar"]