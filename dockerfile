FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-1.3.0.jar timesheet-1.3.0.jar
ENTRYPOINT ["java", "-jar", "/timesheet-1.3.0.jar" ]