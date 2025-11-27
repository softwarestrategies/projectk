# Project-K

## About

This project is for learning/maintaining/upgrading my skills & understanding regarding the following.  It is a work-in-progress.

- Kotlin 
- Spring Boot (including Spring WebMVC, Spring Cloud, Spring Data, Spring Security)
- Docker / Docker Compose
- OAuth 2.0 (with Keycloak as the Authentication Server)
- Kafka
- PostgreSQL
- Keycloak

## Infrastructure

For local dev, I use docker-compose to setup & run any external systems/apps that the apps will need.

### Startup the necessary system(s) with Docker Compose

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

For this, you'll need some PostgreSQL client installed so that you can access & setup the database. I have PostgreSQL
installed on my machine and am thus using **psql** from the commandline. From the commandline, log into psql to check
that the tables were created and data was inserted.

    /projectk %  psql -h localhost -p 5432 -d projectk -U projectk_admin --password

        projectx=> select * from project;
    
            which should show no projects,  but not fail either

### Keycloak

Though there are ways to set things up via Keycloak's Admin REST API, I will do it via the UI available when the container is up & running.  There is a lot more to Keycloak, but you'll have to learn that on your own.  This is just intended to get you started with something.

With your browser, go to this URL and login using the Keycloak credential in the docker-compose.yml file:   **localhost:8484/auth**

#### A) Create a Realm

Create a Realm named **"PROJECTK"**

    (NOTE: It is not obvious how.  What you do is float your mouse up near "Master" in the left column and the option becomes apparent)

#### B) Create Role(s)

Create these 2 Roles:  **"ADMIN"** and **"USER"**.

#### C) Create User(s)

Create a User named **"admin.test@softwarestrategies.io"**, assigning it the two roles **"USER"** and **"ADMIN"** (from
the "Role Mappings" tab) and setting its password (from the "Credentials" tab) to **"admin"**

    NOTE:  On Details tab, make sure that "Email verified" is set to Yes.

    NOTE:  For the password, make sure that "Temporary" is Off.

Create a User named **"user1.test@softwarestrategies.io"**, assigning it the one role **"USER"** and setting its
password to **"user1"**

Create a User named **"user2.test@softwarestrategies.io"**, assigning it the one role **"USER"** and setting its
password to **"user2"**

#### D) Create Client Scope(s)

Create these 2 Roles:  **"read"** and **"write"**.

#### E) Create a Client

Create a Client named **"projectk-ui"**

For the "Valid Redirect URIs" value on the Settings tab, set it to this:  ** http://localhost:8080/ui-client/* **

On the "Client Scopes" tab, add to "Default Client Scopes" the 2 scopes created above:  **read** and **write**
