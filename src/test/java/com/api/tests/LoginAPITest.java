package com.api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;

public class LoginAPITest {
	
@Test

public void loginAPITest() throws IOException{
	    
	UserCredentials userCredentials = new UserCredentials("iamfd","password");
	
	given()
	.spec(SpecUtil.requestSpec(userCredentials))
	.when()
	.post("login")
	.then().spec(SpecUtil.responseSpec_OK(DEFAULT_PORT))
	.body("message", equalTo("Success"))
	.body("message", Matchers.equalTo("Success")).body("data.token", notNullValue())
	.body(matchesJsonSchemaInClasspath("response-schema/loginAPIResponseSchema.json"));
  }

}
