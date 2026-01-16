@dashboard @allTest
Feature: Testing Logged user dashboard feature

Background:
	Given Pass "mngr652043" on "UserID" Field
	And Pass "UmEguba" on "Password" Field
	Then Click on Login button with valid credentials
	
@TC_014 
Scenario: Verify Manager and Logout button is visible in the left side
	Given "Manager" button is  visible
	
#Home Work TC_015

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
	
	
		