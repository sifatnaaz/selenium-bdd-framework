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

	private final TestDataContext context;
	private final BaseClass base;

	// FIX 1: PicoContainer injects TestDataContext - no zero-arg constructor needed
	public Hooks(TestDataContext context) {
		this.context = context;
		this.base = new BaseClass();
	}

	@Before
	public void loadExcelConfigFromTags(Scenario scenario) {
		System.out.println("inside before hook");
		base.initializeDriver();

		String dataFile = null;
		String sheetName = null;

		for (String tag : scenario.getSourceTagNames()) {
			if (tag.startsWith("@dataFile=")) {
				dataFile = tag.substring("@dataFile=".length());
			} else if (tag.startsWith("@sheet=")) {
				sheetName = tag.substring("@sheet=".length());
			}
		}

		if (dataFile == null || sheetName == null) {
			throw new RuntimeException("Missing @dataFile=... or @sheet=... tag in feature/scenario.");
		}

		// FIX 2: Populate TestDataContext so step definitions can read it
		context.setDataFile(dataFile);
		context.setSheetName(sheetName);

		System.out.println("Excel file: " + dataFile + ", Sheet: " + sheetName);
	}

	@AfterStep
	public void takeScreenshotOnFailure(Scenario scenario) {
		WebDriver driver = base.getDriver();

		if (scenario.isFailed() && driver != null) {
			String timestamp = java.time.LocalDateTime.now()
					.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
			String safeScenarioName = scenario.getName().replaceAll("\\s+", "_");
			String screenshotName = safeScenarioName + "_" + timestamp + ".png";

			ScreenshotUtils.captureScreenshot(driver, screenshotName);

			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", screenshotName);

			System.out.println("Screenshot captured for failed step: " + screenshotName);
		}
	}

	@After
	public void tearDown() {
		base.quitDriver();
	}

	public WebDriver getDriver() {
		return base.getDriver();
	}

	public TestDataContext getContext() {
		return context;
	}
}
