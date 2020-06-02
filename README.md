# README #

This README would normally document whatever steps are necessary to get your application up and running.

### Example implemtation using Spring boot and Angular 9###

* Spring boot application provided a REST API for the Angular Application this is in the investment folder
* Angular 9 application exist in the investment-app folder
* The Spring boot application has 61 perecent test coverage at the moment


### How do I get set up? ###

* Start the Spring boot application
* Start mysql Docker/Docker compose need to be installed
  * cd investment/mysql
  * docker-compose up
* Build and compile
    * ./mvnw spring-boot:run
* Start Angular 9 Application
    * npm install   *adds node modules*
    * ng serve

### URL ###
 * Swagger API Documentation
  * http://localhost:8080/swagger-ui.html
 * Angular UI 
  * http://localhost:4200/client/home
