@echo off
echo Starting Spring Boot Backend...

REM ---- JAVA ----
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-8.0.472.8-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%

REM ---- PROJECT ----
cd /d "C:\Users\shwet\Desktop\Mini projects\food-deliver-app"

REM ---- USE MAVEN WRAPPER ----
call mvnw spring-boot:run

pause
