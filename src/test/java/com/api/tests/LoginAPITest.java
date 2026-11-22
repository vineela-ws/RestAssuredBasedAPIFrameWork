package com.api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

public class LoginAPITest {
	
@Test

public void loginAPITest() throws IOException{
	    
	UserCredentials userCredentials = new UserCredentials("iamfd","password");
	
	given()
	.baseUri(getProperty("BASE_URI"))
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
