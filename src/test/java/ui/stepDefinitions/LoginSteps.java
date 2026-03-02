package ui.stepDefinitions;

import java.awt.AWTException;

import org.junit.Assert;

import ui.base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pageObjects.LoginPage;
import ui.utils.UiConfigReader;
import ui.utils.LoggerUtils;
import ui.utils.WindowsAlertHandler;

public class LoginSteps extends BaseClass {
	private LoginPage loginPage;
	WindowsAlertHandler winAlert = new WindowsAlertHandler();
	
	
	@Given("user is on login page")
	public void user_is_on_login_page() {
		 initializeDriver();
		 loginPage =new LoginPage(driver);
		 LoggerUtils.log("This is the page opened : "+driver.getCurrentUrl());
	}

	@When("user enters valid credentials")
	public void user_enters_valid_credentials() {
		
		loginPage.enterUsername(config.getProperty("username"));
		loginPage.enterPassword(config.getProperty("password"));
		loginPage.clickLogin();
	}

	@Then("user should see the homepage")
	public void user_should_see_the_homepage() throws InterruptedException, AWTException {
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
		winAlert.clickOK();
//		driver.quit();
	}
}
