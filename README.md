# Wiremock JAX-RS Example
[![Build Status](https://travis-ci.org/tomasbjerre/wiremock-jaxrs-example.svg?branch=master)](https://travis-ci.org/tomasbjerre/wiremock-jaxrs-example)

Example of:

 https://github.com/tomasbjerre/wiremock-jaxrs

See also:

 https://bjurr.com/automatic-stubs-with-wiremock/

This demonstrates a structure like:

 * `module` - Parent pom.
 * `module-api` - JAX-RS annotated API. Very slim API jar.
 * `module-api-mock` - Runnable mock-server based on [Wiremock JAX-RS](https://github.com/tomasbjerre/wiremock-jaxrs).

# Usage

You can get the example runnable jar from:

https://dl.bintray.com/tomasbjerre/tomasbjerre/se/bjurr/wiremock/wiremock-jaxrs-example-api-mock/

And run it like this:

```shell
java -jar wiremock-jaxrs-example-api-mock-1.6-jar-with-dependencies.jar 
```

You can try it out by invoking with curl like:

```shell
curl -v -H "Accept: application/json" http://localhost:8080/get?filter1=abc
curl -v -H "Accept: application/json" http://localhost:8080/get?filter1=some+attr+value
```
