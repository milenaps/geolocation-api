# Spring Boot Sample Application

Sample Spring Boot REST API using MongoDB, aiming to calculate the nearest shop based on geolocation.

### Steps to run using Docker
docker build -t br.com.mps/springboot-mongo-api --build-arg JAVA_OPTS=-Dspring.profiles.active=local .
docker rm -f springboot-mongo-api || true && docker run -d -p 8080:8080 --name springboot-mongo-api br.com.mps/springboot-mongo-api

### Running in your local
To start this application locally from you IDE, find the main Application class and run it, or use a gradle plugin.

##Usage
- By default this app runs pointing to an embedded mongodb, to change it to point to a real mongodb instance, it has been verified that the build.gradle last dependency listed needs to be commented and the project built again without it.
- If pointing to a real mongodb server, please update the $rootDir/src/main/resources/application.yml with the db credentials. If there is no existing schema yet, you might want to create one and set its name in this config file.
- Considering that the steps above have been successfully executed and the application is running, test it by invoking the GET API http://localhost:3000/api/v1/pdvs in your preferred browser or via a tool like postman.