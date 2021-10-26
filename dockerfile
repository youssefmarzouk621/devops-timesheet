FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-1.2.4-SNAPSHOT.jar timesheet-1.2.4-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/timesheet-1.2.4-SNAPSHOT.jar" ]