@dashboard
Feature: Testing Logged user dashboard feature

Background:
	Given Pass "mngr652043" on "UserID" Field
	And Pass "UmEguba" on "Password" Field
	Then Click on Login button with valid credentials
	
@TC_014 
Scenario: Verify Manager and Logout button is visible in the left side
	Given "Manager" button is  visible