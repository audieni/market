FROM amazoncorretto:17
COPY target/market*.jar /market.jar
ENTRYPOINT [ "java", "-jar", "/market.jar" ]
EXPOSE 7070