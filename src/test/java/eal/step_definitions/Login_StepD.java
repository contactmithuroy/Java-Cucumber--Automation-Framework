package eal.step_definitions;

import org.apache.logging.log4j.*;
import eal.utilities.CommonMethods;
import io.cucumber.java.en.*;

public class Login_StepD extends CommonMethods {
	private static final Logger logger = LogManager.getLogger(CommonMethods.class);

	@Given("Validate User landed on homepage")
	public void validate_user_landed_on_homepage() {
		logger.info("Verify Title");
		boolean titleMatched = hmpage_pom.verify_homepage_title();
		logger.info("Performing Assertion");
		softAssert.softAssertTrue(titleMatched, "Title Matched Successfully", "Title didnt match");
	}

	@Then("Verify {string} is visible")
	public void verify_visible_string(String string_value_form_feature) {
		logger.info("Verifying presense of" + string_value_form_feature + "text");
		boolean validationStatus = hmpage_pom.verify_home_page_elements(string_value_form_feature);

		logger.info("Performing Assertion");
		softAssert.softAssertTrue(validationStatus, string_value_form_feature + " Text is Visible in the screen",
				string_value_form_feature + " text is not visible in the screen");
	}
	
	@Then("Verify LOGIN is visible")
	public void verify_login_is_visible() {
		logger.info(" Verify LOGIN is available");
		boolean verifyLoginBtn = hmpage_pom.verify_LOGIN_is_visible();
		logger.info("  LOGIN: "+verifyLoginBtn);
		softAssert.softAssertTrue(verifyLoginBtn, "Login button is visible", " LOGIN button is not visible");
	}
	
	@Then("Click on Selenium Drop down from the top")
	public void click_on_selenium_drop_down_from_the_top() {
		logger.info("Verifying Click Selenium Drop Down From the top");
		boolean seleniumBtnisVisible = hmpage_pom.verify_seleniumBtn_isVisible();
		softAssert.softAssertTrue(seleniumBtnisVisible, "Selenium Button is visible", "Selenium Button is not visible");

		logger.info("Verifying Click on Selenium Drop Down ");
		boolean clickAndListExpended = hmpage_pom.click_on_seleniumBtn();
		logger.info("Performing Selenium Drop Down Assertion");
		softAssert.softAssertTrue(clickAndListExpended, "Click Selenium dropdown - List expanded",
				"Not click on Selenium dropdown - List not expanded");
	}

	@Then("Verify Table Demo is available Under Selenium Drop down")
	public void verify_table_demo_is_available_under_selenium_drop_down() {
		logger.info("Verify Table Demo is available Under Selenium Drop down ");
		boolean tableDemoBtnisVisible = hmpage_pom.verify_table_demo_isVisible();
		logger.info("Performing Table Demo available Assertion");
		softAssert.softAssertTrue(tableDemoBtnisVisible, "Table Demo is available in the DropDown",
				"Table Demo is not available in the DropDown");
	}

	@Given("Pass {string} on {string} Field")
	public void pass_on_field(String fieldValue, String fieldName) {
		
		String actualValueOnInputBox = hmpage_pom.passFieldValue(fieldValue, fieldName);
		softAssert.softAssertEquals(actualValueOnInputBox, fieldValue, "Field is filled Up");
	}

	@Then("Click on Login button")
	public void click_on_login_button() {
	    String alertActualMessage = hmpage_pom.clickOnLoginBtn();
	    logger.info("⚠️ Alert text captured: " + alertActualMessage);
	    String expectedAlertText = "User is not valid";
	    softAssert.softAssertEquals(alertActualMessage, expectedAlertText, "Alert message verified");
	}

	@Then("Click on {string} button from the alert")
	public void click_on_button_from_the_alert(String string) {
	    boolean isUseronHomePage = hmpage_pom.verify_homepage_title();
	    softAssert.softAssertTrue(isUseronHomePage, "User on homepage", "Not on homepage");
	}
	
	@Given("Pass {int} digit Numeric userID {int} on userID Field and immidiately Clear it")
	public void pass_digit_numeric_user_id_on_user_id_field_and_immidiately_clear_it(Integer count, Integer userID) {
	   
	}

	@Then("Click on Reset Button")
	public void click_on_reset_button() {
	   

	}
                               

}
