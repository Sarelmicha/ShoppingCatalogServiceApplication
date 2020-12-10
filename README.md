Shopping Catalog Service
==========================

# What you’ll need

* Docker
* JDK 6 or later
* IDE that support Spring apps
* Gradle

# Neo4j

Please follow the guid in:
        
    https://hub.docker.com/_/neo4j
    
For install + run Neo4j as an image
 

# Build

Open the project on your favorite IDE.

Run gradle build for building the project:

    gradle build


# How to run it?

Run the main function in src -> main -> ShoppingCatalogServiceApplication
    
Make sure that the server is on listing to port 8081 on localhost:

    http://localhost:8081

# How to use it? 

After the server is running, you can use Swagger to send request to the service:

```
http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
```
