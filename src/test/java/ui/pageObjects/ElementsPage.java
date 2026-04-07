package ui.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import ui.utils.WaitUtils;

public class ElementsPage {
	 private WebDriver driver;
	 private WaitUtils wait;
	    
	    //locators 
	@FindBy(xpath="//div[@class='category-cards']//h5[text()='Elements']")
	private WebElement elementLink;
	
	
	@FindBy(xpath="//span[contains(text(),'Text Box')]")
	private WebElement textBox;
	
	private By pageLabel= By.xpath("");
	
	//constructor 
	public ElementsPage(WebDriver driver) {
		 this.driver = driver;
		
		wait= new WaitUtils(driver);
		PageFactory.initElements(driver, this);
	}
	
	//Actions 
	
	public void homePage() {
		Assert.assertTrue((wait.waitForVisibility(elementLink)).isDisplayed());
	}
	public void clickOnElements() {
		wait.waitForVisibility(elementLink);
		wait.waitAndClick(elementLink);
		
	}
	
	  public String elementLabel() {
		  return wait.waitForVisibility(textBox).getText();
	  }

}
