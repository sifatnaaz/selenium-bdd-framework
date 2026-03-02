package api.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.pojo.User;
import api.utils.SpecFactory;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static io.restassured.RestAssured.given;

public class UserSteps {
	private Response response;
	private User newUser;
	private int id;

	@Given("I call GET user with id {string}")
	public void i_call_get_user_with_id(String id) {

		response = given()
				.spec(SpecFactory.specBuilder("apiConfig"))
				.pathParam("userId" , id)
				.log().uri()
			.when()
			.get("/users/{userId}");
		
		
		response.then()
        .spec(SpecFactory.responseSpec());
		
		response.then().log().all();
	}
	
//	@Then("the response status should be {int}")
//    public void the_response_status_should_be(int code) {
//        response.then().statusCode(code);
//    }

    @Then("the response should contain user id {int}")
    public void the_response_should_contain_user_id(int userId) {
        response.then().body("id", equalTo(userId));
    }
    
    @Given("I prepare a new user payload")
    public void i_prepare_new_user() {
    	newUser= new User("Ramesh","ramesh.ram","rameshram@email.com");
    }
    
    @When("I send POST request to {string}")
    public void i_send_post_request(String endpoint) {
    	response = given()
    				.spec(SpecFactory.specBuilder("apiConfig"))
    				.body(newUser)
    			.when()
    				.post(endpoint);


    				response.then().spec(SpecFactory.responseSpec());   	
    	
    			
    
    }
    
    @Then ("the response should contain created user id")
    public void the_response_should_contain_created_userid() {
    	response.then().body("id", notNullValue());
    	
    	 id = response.jsonPath().get("id");
    	 System.out.println("id IS : "+ id );
    }
    @Given("fetch the created record")
	public void fetch_the_created_record() {
    	i_call_get_user_with_id(Integer.toString(id));
    }
	
    @Then ("the name of the user should be {string}")
    public void name_of_user(String userName) {
    	response.then().body("name", equalTo(userName));
    }
    
 
}
