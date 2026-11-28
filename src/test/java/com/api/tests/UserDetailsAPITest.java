package com.api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import org.testng.annotations.Test;
import com.api.utils.SpecUtil;
import static com.api.constant.Role.*;

public class UserDetailsAPITest {
	
	@Test
	
	public void userDetailsAPITest() throws IOException {
		
		given()
	    .spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.get("userdetails")
		.then()
		.spec(SpecUtil.responseSpec_OK(DEFAULT_PORT))
		.body("message", equalTo("Success"))
		.body("data.id", equalTo(4))
		.and()
		.body(matchesJsonSchemaInClasspath("response-schema/userDetailsResponseSchema.json"));		
	}

}
