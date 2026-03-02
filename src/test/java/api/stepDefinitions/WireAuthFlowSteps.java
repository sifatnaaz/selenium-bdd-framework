package api.stepDefinitions;

import java.util.HashMap;
import java.util.Map;

import api.utils.SpecFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;




public class WireAuthFlowSteps {
			private Response response;
			private String token;
	
	 @Given("I login with username {string} and password {string}")
	    public void i_login_with_username_and_password(String username, String password) {
		 
		 	Map<String , Object> userDetails = new HashMap<>();
		 	userDetails.put("username", username);
		 	userDetails.put("password", password);
		 	
		  response = given()
	                .spec(SpecFactory.specBuilder("authConfig"))
	                .body(userDetails)
	        .when()
	                .post("/auth/login");
	 }
	 
	 @Then("the response status should be {int}")
	    public void response_status_should_be(int code) {
		 		response.then().statusCode(code);
	    }

	    @Then("I store the auth token")
	    public void i_store_the_auth_token() {
	    		token = response.jsonPath().get("token");
	    		System.out.println("token: " + token);
	    }



	    @When("I call secure profile endpoint")
	    public void i_call_secure_profile_endpoint() {
	        response = given()
	                .spec(SpecFactory.specBuilder("authConfig"))
	                .header("Authorization", "Bearer " + token)
	        .when()
	                .get("/secure/profile");
	    }

	  

	    @Then("the profile name should be {string}")
	    public void the_profile_name_should_be(String expectedName) {
	        response.then().body("name", equalTo(expectedName));
	    }
	}

