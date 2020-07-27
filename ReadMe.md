# User Service
The User Service is a Java/Spring Boot based API for managing and creating Users for your application.

## To Build:
You’ll need to make sure you have Java install, and the maven package manager:
https://maven.apache.org/download.cgi

To build it, you will navigate to the root folder where pom.xml is an execute:
`mvn clean install`

To run it, execute:
`java -jar -Dspring.profiles.active=default-local target/*.jar`

Postgres:
https://www.postgresql.org/

## Current functionality:
* Registration/User Creation
* Profile creation
* Login
* Friends

## Data
The main persisted entities are:
* User: the log in entity, stores email and password. Should be more or less immutable
* Profile: the metadata and information about a user. This can be edited without touching the User entity

Non-persisted entities:
* Registration: this is a combo class that encapsulates User and Profile. This is because at registration, a user will provide User info and Profile info at once. We will persist separately, but never need again in the lifecycle of the User/Profile
* Friend: Contains info for the Front End logic that is populated on the fly

## To Test it:
Postman is an API development tool, used to build and execute requests for APIs and Restful services

Download Postman:
https://www.postman.com/

Run the Postmans in [root]/postmans

You will see parameterized URLs. You'll need to set a domain variable = localhost and a post-user variable = 8081
`http://{{domain}}:{{port-user}}/users/login`