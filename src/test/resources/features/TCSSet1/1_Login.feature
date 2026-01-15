@login @homepage
Feature: Testing Homepage Items and Login Related test cases 

Background:
	Given Validate User landed on homepage
	
@TC_001 @smoke
Scenario: Verify user landed on Homepage and UserID is available
	Then Verify "UserID" is visible 
		
@TC_002 @smoke
Scenario: Verify Password input is available
	Then Verify "Password" is visible

@TC_003 @smoke
Scenario: Verify LOGIN is available
	Then Verify LOGIN is _004 @smoke
Scenario: Verify Table Demo is available
	Then Click on Selenium Drop down from the top
	Then Verify Table Demo is available Under Selenium Drop down

# Use this separat Test cases or use bello @InvaliedTest case scenario style
#-------------------------------------------------------------------------------
#Invalid user id and Invalid Password
@TC_005
Scenario:Verify Verify logging in with invalid credentials in Login Homapage(both invalid)
	Given Pass "mngr6467" on "UserID" Field
	And Pass "000000" on "Password" Field
	Then Click on Login button
	Then Click on "OK" button from the alert

#Valid user id and invalid password
@TC_006
Scenario:Verify Verify logging in with invalid credentials in Login Homapage(both invalid)
	Given Pass "mngr646768" on "UserID" Field
	And Pass "000000" on "Password" Field
	Then Click on Login button
	Then Click on "OK" button from the alert

#Invalid user id and valid password
@TC_007
Scenario:Verify Verify logging in with invalid credentials in Login Homapage(both invalid)
	Given Pass "mngr6467" on "UserID" Field
	And Pass "nebEsAg" on "Password" Field
	Then Click on Login button
	Then Click on "OK" button from the alert

# Or use this one scenario stlye both are work fine
#-------------------------------------------------------------------------------
@InvalidLogin	
Scenario Outline: Verify logging in with Invalid credentials in Login Homepage
	Given Pass "<UserID>" on "UserID" Field
	And Pass "<Password>" on "Password" Field
	Then Click on Login button
	Then Click on "OK" button from the alert
	
	Examples:
	|UserID | Password | Tag |
	|mngr6467|000000|@TC_005 |
	|mngr646768|000000|@TC_006 |
	|mngr6467|nebEsAg|@TC_007 |
	
	

Scenario: Verify User can Clear userID Field after putting multiple Numeric userID by Mistake
	Given Pass 6 digit Numeric userID 123456 on userID Field and immidiately Clear it
	Then Pass 5 digit Numeric userID 12345 on userID Field and immidiately Clear it
	Then Pass 8 digit Numeric userID 12345678 on userID Field and immidiately Clear it
	Then Pass 10 digit Numeric userID 1234567891 on userID Field and immidiately Clear it
	Then click on Reset Button
	
	
#User Data Table and List 
	@TC_010	
Scenario: Verify User can Clear userID Field after putting multiple Numeric userID by Mistake
	Given I enter and immediately clear the following UserIDs:
	|123456|
	|12345|
	|12345678|
	|1234567891|
	Then click on Reset Button
	
	@TC_011
	Scenario: Verify multiple UserID and Password combinations
	Given I try the following credentials:
		|UserID|Password|
		|mngr6467|000000|
		|mngr646768|000000|
		|mngr6467|nebEsAg|
	Then click on Reset Button
	
	
	@TC_013
Scenario Outline: Verify logging in with valid credentials in Login Homepage
	Given Pass "mngr652043" on "UserID" Field
	And Pass "UmEguba" on "Password" Field
	Then Click on Login button with valid credentials
				