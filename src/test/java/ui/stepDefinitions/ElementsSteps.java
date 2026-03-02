package ui.stepDefinitions;


import ui.context.TestDataContext;
import ui.pageObjects.listofActionsPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import ui.utils.ExcelRow;
import ui.utils.ExcelUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pageObjects.listofActionsPage;
import ui.base.BaseClass;

public class ElementsSteps extends BaseClass{

    private final TestDataContext context;
    listofActionsPage lp;

   
    public ElementsSteps(TestDataContext context) {
        this.context = context;
    }

    @Given("I am on home pagee")
    public void i_am_on_home_pagee() {
    	initializeDriver();
		System.out.println("Driver before page init = " + driver);
		lp = new listofActionsPage(driver);
		lp.homePage();
    }

    @When("I click on Elements pagee")
    public void i_click_on_elements_pagee() {
    	  lp.clickOnElements();
    }

    @Then("I see textbox element for test case {string}")
    public void i_see_textbox_element_for_test_case(String testCaseId) {

        ExcelRow row = ExcelUtils.findByTestCaseId(
                context.getDataFile(),
                context.getSheetName(),
                testCaseId
        );

        String expectedElement = row.getRequired("expectedElement");
        System.out.println("expectedElement : "+ expectedElement);

        // TODO: replace with real UI validation:
        // String actual = elementsPage.getTextBoxLabelText();
        // Assertions.assertEquals(expectedElement, actual);

        // demo assertion so you see it working end-to-end:
        String label = lp.elementLabel();
        Assert.assertEquals(label, expectedElement);
        quitDriver();
    }
}