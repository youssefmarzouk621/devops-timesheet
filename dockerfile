FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-1.2.0-SNAPSHOT.jar timesheet-1.2.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/timesheet-1.2.0-SNAPSHOT.jar" ]