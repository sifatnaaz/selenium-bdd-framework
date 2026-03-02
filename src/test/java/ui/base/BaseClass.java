package ui.base;

import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ui.utils.UiConfigReader;

public class BaseClass {

	protected WebDriver driver;
	protected UiConfigReader config;
	private WebDriverWait driverWait;

	public void initializeDriver() {
		config = new UiConfigReader();
		String browser = config.getProperty("browser");
		System.out.println("inside "+ browser);
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(); // driver = new ChromeDriver(options);
		} // Extendable: add Firefox, Edge, etc.

		driver.manage().window().maximize();
		driver.get(config.getProperty("baseUrl"));
		

	}

	/*
	 * How to avoid browser pop up ?
	 * 
	 * ChromeOptions options = new ChromeOptions();
	 * options.setExperimentalOption("excludeSwitches",
	 * Arrays.asList("enable-automation", "password-manager"));
	 */

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}