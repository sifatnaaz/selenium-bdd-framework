package ui.stepDefinitions;

import org.openqa.selenium.WebDriver;

import ui.base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pageObjects.listofActionsPage;

public class listofActionsSteps extends BaseClass{

	listofActionsPage lp;
	
	
	@Given("I am on home page")
	public void i_am_on_home_page() {
		initializeDriver();
		System.out.println("Driver before page init = " + driver);
		lp = new listofActionsPage(driver);
		lp.homePage();
	}

	@When("I click on Elements page")
	public void i_click_on_elements_page() {
	    lp.clickOnElements();
	}

	@Then("I see textbox element")
	public void i_see_textbox_element() {
	    // Write code here that turns the phrase above into concrete actions
	}
}
