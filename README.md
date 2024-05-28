# ProjectK

## About

This project is for me to learn/maintain/upgrade my skills & understanding regarding the following.  It is a work-in-progress.

- Kotlin 
- Spring Boot (including Spring WebMVC, Spring Cloud, Spring Data, Spring Security)
- OAuth 2.0 (with Keycloak as the Authentication Server)
- Kafka 
- Docker

## Infrastructure

For local development, I use docker-compose to setup & run any external systems/apps needed.

### Docker

The infrastructure for the system is setup using docker-compose and the components/values are in the file **
docker-compose.yml**. So, startup Docker from the project root directory and then confirm that all of the required
containers have started up:

    /projectk %  docker-compose up -d
    /projectk %  docker ps

        You should see all of the containers specified in the docker-compose.yml file "Up"

NOTE: After any Dev work is done, you should stop Docker containers setup here:

    /projectk %  docker-compose stop

### Kafka

The docker-compose file specifies that 2 topics have been setup.  

There are several utils that can be used:

- https://thenewstack.io/top-10-tools-for-kafka-engineers/
- https://offsetexplorer.com/index.html


The Kafka container is setup to have the 2 topics that we'll be using. To make sure all is OK, first log onto the Kafka
container. Then from its Bash prompt, run the kafka script to list topics. And then exit the container.

    /projectx-kotlin %  docker exec -it projectk-kafka bash

        /bash# kafka-topics.sh --list --bootstrap-server localhost:9092
        
            StartProcess
            FinishProcess

        /bash# exit



### PostgreSQL

### Keycloak

