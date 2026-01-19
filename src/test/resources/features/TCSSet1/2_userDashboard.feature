@dashboard @allTest
Feature: Testing Logged user dashboard feature

#------------------------------------------------------------------
# Background
# This means: if any scenario in this feature runs,
# the Background steps will automatically execute first
# (Valid login is a common precondition)
#------------------------------------------------------------------
Background:
	Given Pass "mngr652043" on "UserID" Field
	And Pass "UmEguba" on "Password" Field
	Then Click on Login button with valid credentials

#------------------------------------------------------------------
# Dashboard UI Validation
#------------------------------------------------------------------

@TC_014
Scenario: Verify Manager and Logout button is visible in the left side
	Given "Manager" button is  visible

#------------------------------------------------------------------
# Menu Navigation Validation
#------------------------------------------------------------------

@TC_015
Scenario: Verify Flash Movie Demo sub-menu visibility under Selenium menu
	Given Validate User landed on login homepage
	Then Click on "Selenium" Drop down from the top
	And the "Flash Movie Demo" sub-menu should be visible under the menu

#------------------------------------------------------------------
# Data-Driven Scenario – Excel Based Input
# Test data is fetched from external Excel file
#------------------------------------------------------------------

#Insert input field using fetching the excel file.
@TC_016
Scenario: Add New Customer using Excel Data
	Given I open the "New Customer" page with title " Guru99 Bank New Customer Entry Page"
	When I fill the form using file "testcases_for_Automation.xlsx" and sheet "Invalid_Pin_Data" with fields:
		|Customer Name |
		|Gender        |
		|Date of Birth |
		|Address       |
		|City          |
		|State         |
		|Pin           |
		|Mobile Number |
		|E-mail        |
	Then Click submit button
