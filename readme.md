# SpringBoot JWT Security Demo

Example project to show how to use JWT in combination with Spring Security to protect REST API endpoint.

## Development

### Requirements

- [Java JDK v1.8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Maven](https://maven.apache.org/plugins/maven-jar-plugin/)

### Steps

Install the dependencies:

```bash
make install
```

Build the war file:

```bash
make all
```

## Usage

Execute the generated `jar` file under `target/`.

_Alternatively_, you can run the application by running the [Application.main](src/main/java/com/nicholasadamou/demo/Application.java) method.

Then, try hitting the REST endpoint: `http://localhost:8888/hello?name=Nicholas` with a `GET` request.

You will get an `HTTP 401` error if you try this in your browser.

To get access to the endpoint you will need to supply a JWT token, so you can get through the `JwtAuthenticationFilter`.

To generate a valid token open the sources of the class [JwtTokenGenerator](src/main/java/com/nicholasadamou/demo/security/util/JwtTokenGenerator.java) and run the 'main' method.

Copy the token and open a tool with which you can send an HTTP request and add the token to the header.

Now, with the token in place you will see the expected results:

```json
{
  "id": 2,
  "content": "Hello, Nicholas!"
}
```

If you access the endpoint `http://localhost:8888/me` with a `POST` request (still with the 'Authorization' header in place) you will get the details of the Principal object in JSON format:

```json
{
  "details": null,
  "authorities": [
    {
      "authority": "admin"
    }
  ],
  "authenticated": true,
  "principal": {
    "username": "nicholas",
    "token": "eyJhbGciOiJIUzUxMeJ9.eyJzdwIiOi....m72LpFADA",
    "authorities": [
      {
        "authority": "admin"
      }
    ],
    "password": null
  },
  "credentials": null,
  "name": "Nicholas"
}
```

The 'principal' field is the returned object here is our [AuthenticatedUser](src/main/java/com/nicholasadamou/demo/security/model/AuthenticatedUser.java). If we want to get more information from our JWT then we can simply add it to this object and fill it in the [JwtAuthenticationProvider](src/main/java/com/nicholasadamou/demo/security/JwtAuthenticationProvider.java).

## References

https://spring.io/projects/spring-boot

https://spring.io/projects/spring-security

https://jwt.io/

https://www.toptal.com/java/rest-security-with-jwt-spring-security-and-java
