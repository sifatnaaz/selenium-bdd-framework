@dataFile=testdata/uitestdata.xlsx
@sheet=Elements
Feature: Elements Section Testing on DemoQA

Scenario: Text Box Submission

Given the user is on the Elements page
When the user navigates to the Text Box section
And enters Name, Email, Current Address, and Permanent Address
And clicks Submit
Then the output should display the entered Name, Email, Current Address, and Permanent Address correctly

Scenario: Check Box Selection

Given the user is on the Elements page
When the user navigates to the Check Box section
And expands the “Home” folder
And selects Desktop, Documents, and Downloads checkboxes
Then the selected items should be displayed correctly in the output

Scenario: Radio Button Selection

Given the user is on the Elements page
When the user navigates to the Radio Button section
And selects the Yes, Impressive, or No option
Then the correct message should be displayed confirming the selection

Scenario: Web Tables Add/Edit/Delete

Given the user is on the Elements page
When the user navigates to the Web Tables section
And adds a new record with valid data
Then the new record should appear in the table

When the user edits the newly added record
Then the table should update with the edited data

When the user deletes a record
Then the record should no longer be visible in the table

Scenario: Buttons Click Actions

Given the user is on the Elements page
When the user navigates to the Buttons section
And clicks the Double Click button
Then a confirmation message for double click should appear

When the user clicks the Right Click button
Then a confirmation message for right click should appear

When the user clicks the Click Me button (single click)
Then a confirmation message for click should appear

Scenario: Links Navigation

Given the user is on the Elements page
When the user navigates to the Links section
And clicks on a link that opens a new tab or page
Then the new tab should open with the correct URL

When the user clicks on an API call link
Then the response status should be 200 OK for valid links

Scenario: Broken Links Verification

Given the user is on the Elements page
When the user navigates to the Broken Links section
And clicks on a broken link
Then the system should display an error or indicate the link is broken

When the user clicks on a valid link
Then the page should load without errors

Scenario: File Upload

Given the user is on the Elements page
When the user navigates to the Upload and Download section
And uploads a valid file
Then the file name should be displayed confirming successful upload

Scenario: File Download

Given the user is on the Elements page
When the user navigates to the Upload and Download section
And clicks the Download button
Then the file should be downloaded successfully to the local system

Scenario: Dynamic Properties – Button Enable

Given the user is on the Elements page
When the user navigates to the Dynamic Properties section
Then the “Will Enable 5 Seconds” button should be disabled initially
When 5 seconds pass
Then the button should become enabled

Scenario: Dynamic Properties – Color Change

Given the user is on the Elements page
When the user navigates to the Dynamic Properties section
Then the “Color Change” button should change color after a few seconds

Scenario: Dynamic Properties – Visibility

Given the user is on the Elements page
When the user navigates to the Dynamic Properties section
Then the “Visible After 5 Seconds” button should appear after 5 seconds
