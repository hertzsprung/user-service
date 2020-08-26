# user-service
Finds users living in, or currently near, a given city.

## Compiling, testing and deploying
To compile the application and run the automated test suite:
````shell script
mvn verify
````
JBehave tests require localhost port 8080.
A runnable JAR is built at `target/user-service-0.0.1.jar`, suitable for deployment.

## Running locally
To run the service locally:
````shell script
mvn spring-boot:run
````

## Example usage
````shell script
curl -H "Content-Type: application/json" -H "Accept: application/json" \
  http://localhost:8080/users/living-or-currently-near/London?within-miles=60
````

## Comments on scalability, security and high availability
* To facilitate service autoscaling, load balancing and high availability, the runnable JAR could be deployed to AWS Elastic Beanstalk.
* To ensure high availability and security, API requests can be rate-limited by deploying the service behind an Amazon API Gateway.
* The API could be secured behind an Amazon Web Application Firewall to restrict access by IP address and prevent oversized API requests.
* Service performance could be improved by caching BPDTS API responses in a local datastore such as MongoDB, assuming underlying BPDTS API responses change infrequently.
* The service should paginate responses to avoid performance degradation due to arbitrarily large responses.
* Similarly, a maximum `within-miles` parameter value should be imposed to prevent overly large responses.

## Assumptions
* The underlying BPDTS API provides the current latitude and longitude of each user, while the `/city/{city}/users` endpoint returns users living in the given city.
* Automated tests assume that users are returned by the BPDTS API in a fixed order.
* The BPDTS API is assumed to be accessible by the test suite.  If this was not desired, WireMock could be used to stub the BPDTS API.  