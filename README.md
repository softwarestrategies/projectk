# ProjectK

## About

This project is for me to learn/maintain/upgrade my skills & understanding regarding the following.  It is a work-in-progress.

- Kotlin 
- Spring Boot (including Spring WebMVC, Spring Cloud, Spring Data, Spring Security)
- OAuth 2.0 (with Keycloak as the Authentication Server)
- Kafka 
- Docker

##  Infrastructure

For local development, I use docker-compose to setup any external systems/apps needed.

### 1) Docker

The infrastructure for the system is setup using docker-compose and the components/values are in the file **
docker-compose.yml**. So, startup Docker from the project root directory and then confirm that all of the required
containers have started up:

    /projectk %  docker-compose up -d
    /projectk %  docker ps

        You should see all of the containers specified in the docker-compose.yml file "Up"

NOTE: After any Dev work is done, you should stop Docker containers setup here

    /projectk %  docker-compose up -d
