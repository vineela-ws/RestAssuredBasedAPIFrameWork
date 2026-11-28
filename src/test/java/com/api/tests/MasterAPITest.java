package com.api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import static com.api.constant.Role.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

public class MasterAPITest {
	
	@Test
	
	public void masterAPITest() throws IOException {
		
		given()
	    .spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.post("master")  //default content-type application/url-formencoded
		.then()
		.spec(SpecUtil.responseSpec_OK(DEFAULT_PORT))
		.body("message", equalTo("Success"))
		.body("data",notNullValue())
		.body("data",hasKey("mst_oem"))
		.body("data",hasKey("mst_model"))
		.body("$",hasKey("message"))
		.body("$",hasKey("data"))
		.body("data.mst_oem.size()",equalTo(2))
		.body("data.mst_model.size()",greaterThan(0))
		.body("data.mst_oem.id",everyItem(notNullValue()))
		.body("data.mst_oem.name",everyItem(notNullValue()))
		.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}
	
	@Test
	
	public void masterAPITest_MissingAuthToken() throws IOException {
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.post("master")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}

}
