package ui.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import ui.utils.UiConfigReader;

public class BaseClass {

    protected WebDriver driver;
    protected UiConfigReader config;

    public BaseClass() {
        config = new UiConfigReader();
    }

    public void initializeDriver() {
        String browser = config.getProperty("browser");
        System.out.println("Initializing browser: " + browser);

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.get(config.getProperty("baseUrl"));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}