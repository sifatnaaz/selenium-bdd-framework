package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;
    private WaitUtils wait;
    
    //locators 
    	@FindBy(xpath="//input[@data-test='username']")
    	private WebElement userNameField;
    	
    	@FindBy(xpath="//input[@data-test='password']")
    	private WebElement passwordField;
    	
    	@FindBy(xpath="//input[@data-test='login-button']")
    	private WebElement loginBttn;
    
    //constructor 
    	  public LoginPage(WebDriver driver) {
    	        this.driver = driver;
    	        wait =  new WaitUtils(driver);
    	        PageFactory.initElements(driver, this); // initializes @FindBy elements
    	    }
    
    //Actions/ Methods
    	  public void enterUsername(String userName) {
    		  wait.waitForVisibility(userNameField).sendKeys(userName);
    	  }
    	  
    	  public void enterPassword(String pass) {
    		  passwordField.sendKeys(pass);
    	  }
    	  
    	  public void clickLogin() {
    		  loginBttn.click();
    	  }
}
