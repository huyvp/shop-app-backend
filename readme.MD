
# Shop-app-backend
This microservice is responsible for:
* Onboarding users
* Roles and permissions
* Authentication

## Tech stack
* Build tool: maven >= 3.9.5
* Java: 17
* Framework: Spring boot 3.2.x
* DBMS: Postgres

## Prerequisites
* Java SDK 17
* A Postgres server

## Start application
`mvn spring-boot:run`

## Build application
`mvn clean package`

## Docker guideline
### Build docker image
`docker build -t <account>/shop-app:0.9.0 .`
### Push docker image to Docker Hub
`docker image push <account>/shop-app:0.9.0`
### Create network:
`docker network create shop-network`
### Start postgres in shop-network
`docker run --network shop-network --name postgresql-test -e POSTGRES_PASSWORD=hh1122334455 -e POSTGRES_USER=postgres -p 5432:5432 -d -v vpostgresql:/var/lib/postgresql/data postgres:16.2`
### Run your application in shop-network
`docker run --name shop-app --network shop-network -p 8080:8080 -e DBMS_URL=jdbc:mysql://postgresql:5432/posgres shop-app:0.9.0`

## Install Docker on ubuntu

# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
"deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
$(. /etc/os-release && echo "$VERSION_CODENAME") stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

sudo docker run hello-world
