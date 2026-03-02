@reg
@dataFile=testdata/uitestdata.xlsx
@sheet=Elements
Feature: Access Different Selenium Features

 Scenario: Test The Elements
    Given I am on home pagee
    When I click on Elements pagee
    Then I see textbox element for test case "TC_01"