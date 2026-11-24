package com.api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constant.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.Header;

public class UserDetailsAPITest {
	
	@Test
	
	public void userDetailsAPITest() throws IOException {
		Header authheader = new Header("Authorization",getToken(FD));
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header(authheader)
		.and()
		.log().uri()
		.log().method()
		.log().body()
		.log().headers()
		.when()
		.get("userdetails")
		.then()
		.log().all()
		.statusCode(200)
		.log().all()
		.body("message", equalTo("Success"))
		.body("data.id", equalTo(4))
		.and()
		.time(lessThan(1000L))
		.body(matchesJsonSchemaInClasspath("response-schema/userDetailsResponseSchema.json"));		
	}

}
