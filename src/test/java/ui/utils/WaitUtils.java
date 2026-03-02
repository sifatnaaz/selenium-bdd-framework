package ui.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	private WebDriverWait wait;

	public WaitUtils(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement waitForVisibility(WebElement elem) {

		return wait.until(ExpectedConditions.visibilityOf(elem));

	}
	
	  public  WebElement waitForClickable(WebElement element) {
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }
	
	public void waitAndClick(WebElement elem) {
		int maxAttempt=3;
		int attempt=1;
		while(attempt< maxAttempt) {
			try {
			waitForClickable(elem).click();
			return;}
			catch(Exception e){
				attempt++;
				try {
					Thread.sleep(500);
				} catch (InterruptedException ignored) {
					
				}
			}
		}
		throw new RuntimeException ("Unable to Click after multiple Attempts");
	}
}
