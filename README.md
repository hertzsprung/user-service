# user-service
Finds users living in, or currently near, a given city.

## Deploying and running
To compile and run the test suite:
````shell script
mvn verify
````
JBehave tests require localhost port 8080.
A runnable JAR is built in `target/user-service-0.0.1.jar`, suitable for deployment.

To run:
````shell script
mvn spring-boot:run
````

## Example usage
TODO

## Comments on scalability, security and high availability
TODO

## Assumptions
* The underlying BPDTS API provides the current latitude and longitude of each user, while the `/city/{city}/users` endpoint returns users living in the given city.