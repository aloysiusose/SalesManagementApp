## About Application
This application is a sales management application built with java 21 and SpringBoot 3.2.4.
The application have been secured with spring security utilizing JWT for authentication purposes and handling persistence with  spring data JPA.
It exposes REST Apis for performing CRUD operations in Product, Sales and Client Domains. 


## Dependencies
1. Spring web for exposing REST Apis
2. Spring Data Jpa  for pwrsistence
3. Spring security for security
4. Spring Oauth2 resource server for JWT processing
5. Postgresql driver for Local database connection
6. Flyway migration for database migration and versioning
7. Lombok for annontations and boiler plate code reductions

## To use this application Locally
1. Clone this application
2. Modify the application.yaml file by edit the datasource url, password and username.
3. Run the application and using an Http testing tool, go to the following address (localhost:8080/api/v1/user/create) to register as a user. unregistered users cannot access the application
4. After successful registration, use the following address to obtain a jwt token (localhost:8080/api/v1/user/auth). the token have a life span of one hour after which a new authentocation is required.
5. There are two categories of users viz: SELLERS AND ADMIN. They both can access the applciation but only an ADMIN can perform delete operation as a way to secure the application.

