# Quarkus Kotlin

This application is a minimal demo for a microservice based on
Kotlin, Quarkus and GraalVM. It was used in a Dutch presentation about
supercharged microservices which can be seen on [YouTube](https://www.youtube.com/watch?v=i2qVuVx5QGU).

To build the application, run `mvn clean compile quarkus:build`

To compile the application to a native image run either
`mvn -Pnative package` on Linux if you have GraalVM preinstalled or 
`mvn -Pnative -Dnative-image.docker-build=true` to use a Docker image.

Because of what GraalVM needs to do and depending on the resources Docker
has available, this can take a while.

After building the native image, you can build a Docker image with
`docker-compose build app` and launch an environment with the application
and a PostgreSQL database with `docker-compose up -d`. 
**Note that ports 8080 and 5432 need to be available**

After launching it, you can view the output of the native image container
to see how fast the application started with `docker-compose logs -f app`. 

With a running application, you can query it with REST at endpoint
[http://localhost:8080/api/movies]. You can add a new movie with POST
and remove it with DELETE at path /{id}.
