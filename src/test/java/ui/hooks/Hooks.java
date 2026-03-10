package ui.hooks;

import ui.base.BaseClass;
import ui.context.TestDataContext;
import ui.utils.ScreenshotUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	private TestDataContext context;
	private final BaseClass base;

	// ✅ Zero-arg constructor for Cucumber instantiation
	public Hooks() {
		this.context = new TestDataContext();
		this.base = new BaseClass();
		
	}

	@Before
	public void loadExcelConfigFromTags(Scenario scenario) {
		//initialize/create the driver for the execution 
		base.initializeDriver();
		
		// defaults
		context.setDataFile(null);
		context.setSheetName(null);

		for (String tag : scenario.getSourceTagNames()) {
			if (tag.startsWith("@dataFile=")) {
				context.setDataFile(tag.substring("@dataFile=".length()));
			} else if (tag.startsWith("@sheet=")) {
				context.setSheetName(tag.substring("@sheet=".length()));
			}
		}

		if (context.getDataFile() == null || context.getSheetName() == null) {
			throw new RuntimeException("Missing @dataFile=... or @sheet=... tag in feature/scenario.");
		}
	}

	@AfterStep
	public void takeScreenshotOnFailure(Scenario scenario) {
		WebDriver driver = base.getDriver(); // static driver from BaseClass via base instance

		if (scenario.isFailed() && driver != null) {

			// Create a readable timestamp: yyyy-MM-dd_HH-mm-ss
			String timestamp = java.time.LocalDateTime.now()
					.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

			// Replace spaces in scenario name to make filename safe
			String safeScenarioName = scenario.getName().replaceAll("\\s+", "_");

			// Screenshot file name
			String screenshotName = safeScenarioName + "_" + timestamp + ".png";

			// Capture screenshot as file
			ScreenshotUtils.captureScreenshot(driver, screenshotName);

			// Also attach to Cucumber report
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", screenshotName);

			System.out.println("Screenshot captured for failed step: " + screenshotName);
		}
	}

	@After
	public void tearDown() {
		base.quitDriver();
	}

	// Expose BaseClass & context if needed in steps
	public WebDriver getDriver() {
		return base.getDriver();
	}

	public TestDataContext getContext() {
		return context;
	}
}