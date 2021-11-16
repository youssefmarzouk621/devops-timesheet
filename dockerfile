FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-1.2.7.jar timesheet-1.2.7.jar
ENTRYPOINT ["java", "-jar", "/timesheet-1.2.7.jar" ]