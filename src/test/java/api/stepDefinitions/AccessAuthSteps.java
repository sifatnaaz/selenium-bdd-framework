package api.stepDefinitions;

import static io.restassured.RestAssured.given;

import api.utils.SpecFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class AccessAuthSteps {
	
	private Response response;
	
	   @Given("I call httpbin bearer endpoint with token {string}")
		public void call_httpbin_bearer_endpoint_with_token(String token) {

			response = given()
					.spec(SpecFactory.specBuilder("authConfig"))
					.header("authorization", "Bearer "+token)
					.log().uri()
				.when()
				.get("/bearer");
				
					
			
			
			response.then()
	        .spec(SpecFactory.responseSpec());
			
			response.then().log().all();
		}
	   
		/*
		 * @Then("the response status should be {int}") public void
		 * the_response_status_should_be(int code) { response .then() .statusCode(code)
		 * .body("authenticated" , equalTo(true)); //.body("token", equalTo(token)); }
		 */

	    @Then("the response should confirm bearer is true")
	    public void the_response_should_confirm_bearer_is_true() {
	        response.then().body("authenticated", equalTo(true));
	    }

}
