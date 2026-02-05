package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class listofActionsPage {
	 private WebDriver driver;
	    
	    //locators 
	@FindBy(xpath="//div[@class='category-cards']//h5[text()='Elements']")
	private WebElement elementLink;
	
	private By pageLabel= By.xpath("");
	
	//constructor 
	public listofActionsPage(WebDriver driver) {
		 this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions 
	public void clickOnElements() {
		elementLink.click();
	}

}
