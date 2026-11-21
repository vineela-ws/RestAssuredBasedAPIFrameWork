package com.api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

public class LoginAPITest {
	
@Test

public void loginAPITest(){
    
	UserCredentials userCredentials = new UserCredentials("iamfd","password");
	
	given()
	.baseUri("http://64.227.160.186:9000/v1")
	.and()
	.contentType(JSON)
	.accept(ANY)
	.body(userCredentials)
	.log().uri()
	.log().method()
	.log().headers()
	.log().body()
	.when()
	.post("login")
	.then()
	.log().all()
	.statusCode(200)
	.body("message", equalTo("Success"))
	.body("message", Matchers.equalTo("Success")).body("data.token", notNullValue())
	.body(matchesJsonSchemaInClasspath("response-schema/loginAPIResponseSchema.json"));
  }

}
