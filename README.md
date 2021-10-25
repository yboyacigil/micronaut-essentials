Micronaut Framework Essentials
===

This is a demo project showing how to use [Micronaut Framework](https://micronaut.io/) to create a typical microservice
with low memory footprint and short start-up time. 


## Noteworthy

Notes to one who visits this:

* Project creation is pretty straight forward using the [Micronaut Launch](https://micronaut.io/launch/) and importing
it into IntelliJ IDEA with no surprise! (Do not forget to enable annotation processors for the project in IDEA settings)
* Micronaut comes with an annotation processor for generating classes and to make it work with Lombok, you just need to
add Lombok's annotation path to it (See maven-compile-plugin config).
* You can force eager initialization of `Singleton` beans. (See: [Application](src/main/java/com/yboyacigil/micronaut/essentials/Application.java) class)
* JSON deserialization for DTOs requires some extra work if you use `@Value` for instance. (See: [Greeting](src/main/java/com/yboyacigil/micronaut/essentials/beans/Greeting.java) class)
* Packaging is simple if you use `mvn package`, it will create a fat JAR
    ```shell
    > java -jar target/micronaut-essentials-0.1.jar
     __  __ _                                  _
    |  \/  (_) ___ _ __ ___  _ __   __ _ _   _| |_
    | |\/| | |/ __| '__/ _ \| '_ \ / _` | | | | __|
    | |  | | | (__| | | (_) | | | | (_| | |_| | |_
    |_|  |_|_|\___|_|  \___/|_| |_|\__,_|\__,_|\__|
      Micronaut (v3.1.1)
    
    11:45:10.763 [main] INFO  c.y.m.e.b.EnglishHelloWorldGreeter - EnglishHelloWorldGreeter initialized
    11:45:10.765 [main] INFO  c.y.m.e.b.TurkishHelloWorldGreeter - TurkishHelloWorldGreeter initialized
    11:45:10.766 [main] INFO  c.y.m.e.services.GreetingService - GreeterService initialized with EnglishHelloWorldGreeter and TurkishHelloWorldGreeter
    11:45:11.316 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 997ms. Server Running: http://localhost:8080
    ```
* You can build docker image like:
  ```
   mvn package -Dpackaging=docker -Djib.to.image=yboyacigil/micronaut-essentials:latest
  ```
  (Have to provide this [Dockerfile](./Dockerfile) because defaults do not work with Java 17 compiled classes)
* It is also possible to create a native package, but you need to run locally a GraalVM JDK.
See more about packaging [here](https://micronaut-projects.github.io/micronaut-maven-plugin/latest/examples/package.html).

