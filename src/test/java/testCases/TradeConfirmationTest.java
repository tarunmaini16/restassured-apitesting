package testCases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import resources.getResource;

public class TradeConfirmationTest {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/java/utils/env.properties");
		prop.load(fis);
	}
	
	@Test
	public void verify1() {
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		given().
	       param("content-type ","application/json;charset=UTF-8").
	    when().
	       get("/api/trade-confirmation").
	    then().assertThat().statusCode(200).and().
	    	contentType(ContentType.JSON).and().
	    	header("Server","nginx/1.13.12").
	    	body("jsonrpc",equalTo("2.0")).
	    	body("rawResponse",equalTo(null)).
			body("result",equalTo("Geth/v1.7.2-stable/linux-amd64/go1.9.3"));
	}
	
	@Test
	public void verifyTradeConfirmation() {
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		given()
			.param("content-type ","application/json;charset=UTF-8")
	    .when()
	       	.get(getResource.tradeConfirmation())
	    .then()
	    	.assertThat().statusCode(200).and()
	    	.contentType(ContentType.JSON).and()
	    	.header("Server","nginx/1.13.12")
	    	.body("jsonrpc",equalTo("2.0"))
	    	.body("rawResponse",equalTo(null))
			.body("result",equalTo("Geth/v1.7.2-stable/linux-amd64/go1.9.3"));
	}
	
	

}
