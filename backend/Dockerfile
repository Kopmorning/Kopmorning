# Base image
FROM openjdk:23-jdk-slim

# JAR 파일을 컨테이너 안에 복사
COPY build/libs/morning-0.0.1-SNAPSHOT.jar kopmorning.jar


# 포트 오픈
EXPOSE 8080

# 앱 실행
ENTRYPOINT ["java","-jar","/kopmorning.jar"]