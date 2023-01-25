# BCM

Note: the whole project from end to end is NOT functional yet. The update is in progress. 
The goal of this project is to create a Trucking Delivery System that will be deployed in Kubernetes.


This repo contains three projects
1. trucking-delivery-system
    This is the UI project. This project is built in Angular. This UI interface is the front end system where users can interact to check the vehicle status and update the vehicle status. Admin/Operator users will be able to add a new driver or modified the driver information. Other than that they can manage vehicles information and statused.
    This front end system will access the first layer of web API (PAPI) through the Restfull webservice. This webservice information is available in the trucking-delivery-status-papi.
    
2. trucking-delivery-status-papi
    This is a web API (PAPI). This api is the first layer of API that responsible to provide the necessary information to UI and to consume the JSON response from the second layer of API (SAPI). the PAPI will call the SAPI through asynchronous call to make sure that all of information provided by SAPI will be deliver to UI. 
    
3. trucking-delivery-status-sapi
    This is the second layer of web API (SAPI) that is responsible to access to database and deliver the necessary information to the PAPI. This SAPI accesses the database trhough the Spring Data JPA. The database that is used in this project is H2. There will be a DB transition when the project is done. Meanwhile to access the H2 database, one can open the <SAPI domain url>/h2-console.


#Update - Jan 24, 2023
1. Add configuration in APIs POM files to dockernize the project. Run Maven build (spring-boot:build-image)
2. Add docker-compose.yml file to run the container. Run "docker-compose up" command in the folder that contains the yml file.
3. Add configuration to allow H2 DB to be access from the container. Open browser (http://localhost:9000/h2-console) and use "jdbc:h2:mem:testdb" as the db url
4. Push the docker image to docker hub - https://hub.docker.com/repositories/mjubilee
    

