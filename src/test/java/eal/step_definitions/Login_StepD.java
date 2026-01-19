package eal.step_definitions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.*;
import eal.utilities.CommonMethods;
import eal.utilities.LogColor;
import io.cucumber.datatable.DataTable;
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
		logger.info("  LOGIN: " + verifyLoginBtn);
		softAssert.softAssertTrue(verifyLoginBtn, "Login button is visible", " LOGIN button is not visible");
	}

	@Given("Validate User landed on login homepage")
	public void validate_user_landed_on_login_homepage() {
		logger.info("Verify Title");
		boolean titleMatched = hmpage_pom.verify_login_homepage_title();
		logger.info("Performing Assertion");
		softAssert.softAssertTrue(titleMatched, "Title Matched Successfully", "Title didnt match");
	}

	@Then("Click on {string} Drop down from the top")
	public void click_on_drop_down_from_the_top(String navManu) {
		logger.info("Verifying Click " + navManu + " Drop Down From the top");
		boolean naveBtnisVisible = hmpage_pom.verify_nav_manue_Btn_isVisible(navManu);
		softAssert.softAssertTrue(naveBtnisVisible, "Naviagtion manu Button is visible",
				"Navigation manue Button is not visible");

		logger.info("Verifying Click on Navigation manue Drop Down ");
		boolean clickAndListExpended = hmpage_pom.click_on_nav_manue_Btn(navManu);
		logger.info("Performing Navigation Drop Down Assertion");
		softAssert.softAssertTrue(clickAndListExpended, "Click Nav manu dropdown - List expanded",
				"Not click on Nave mane dropdown - List not expanded");
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
		String expectedAlertText = "User is not valid";
		softAssert.softAssertEquals(alertActualMessage, expectedAlertText, "Alert message verified");
	}

	@Then("Click on Login button with valid credentials")
	public void click_on_login_button_with_valid_credentials() {
		boolean titleMatched = hmpage_pom.clickOnLoginBtnWithValidCredentials();
		softAssert.softAssertTrue(titleMatched, "Title Matched Successfully", "Title didnt match");

	}

	@Then("Click on {string} button from the alert")
	public void click_on_button_from_the_alert(String string) {
		boolean isUseronHomePage = hmpage_pom.verify_homepage_title();
		softAssert.softAssertTrue(isUseronHomePage, "User on homepage", "Not on homepage");
	}

	@Given("Pass {int} digit Numeric userID {int} on userID Field and immidiately Clear it")
	public void pass_digit_numeric_user_id_on_user_id_field_and_immidiately_clear_it(Integer count, Integer userID) {
		String fieldName = "UserID";
		String updateUserID = String.valueOf(userID);
		String actual_value_from_the_UI = hmpage_pom.passFieldValue(updateUserID, fieldName);

		if (actual_value_from_the_UI.equals(userID)) {
			logger.info(LogColor.Blue + "User Id Inserted" + LogColor.RESET);
		} else {
			logger.info(LogColor.RED + " Different Value Inserted " + LogColor.RESET);
		}
		String ActualValueAfterClear = hmpage_pom.celarHomePageField(fieldName);
		softAssert.softAssertEquals(ActualValueAfterClear, "", "UserField Cleared after typing");
	}

	@Given("I enter and immediately clear the following UserIDs:")
	public void enter_and_immediately_clear_ids(DataTable dataTable) {
		String fieldName = "UserID";
		List<String> userIDs = dataTable.asList();

		for (String rawUserID : userIDs) {
			
			String userID = hmpage_pom.sanitizeInput(rawUserID);

			String actual_value_from_the_UI = hmpage_pom.passFieldValue(userID, fieldName);

			if (actual_value_from_the_UI.equals(userID)) {
				logger.info(LogColor.Blue + "User id inserted" + LogColor.RESET);
			} else {
				logger.info(LogColor.RED + " Different Value Inserted " + LogColor.RESET);
			}
			String ActualValueAfterClear = hmpage_pom.celarHomePageField(fieldName);
			softAssert.softAssertEquals(ActualValueAfterClear, "", "UserField Cleared after typing");
		}
	}

	@Given("I try the following credentials:")
	public void i_try_the_following_credentials(DataTable dataTable) {
		List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> row : credentials) {
			String userID = row.get("UserID");
			String password = row.get("Password");

			String actual_User_value_from_the_UI = hmpage_pom.passFieldValue(userID, "UserID");

			if (actual_User_value_from_the_UI.equals(userID)) {
				logger.info(LogColor.Blue + "User id inserted" + LogColor.RESET);
			} else {
				logger.info(LogColor.RED + " Different UserID Value Inserted " + LogColor.RESET);
			}

			String actual_Password_value_from_the_UI = hmpage_pom.passFieldValue(password, "Password");

			if (actual_Password_value_from_the_UI.equals(password)) {
				logger.info(LogColor.Blue + "Password inserted" + LogColor.RESET);
			} else {
				logger.info(LogColor.RED + " Different Password Value Inserted " + LogColor.RESET);
			}

		}
	}

	@Then("click on Reset Button")
	public void click_on_reset_button() {

		boolean isReset = hmpage_pom.isUserIdAndPasswordReset();
		softAssert.softAssertTrue(isReset, "", "UserID and Password Field Cleared after typing");
	}

}
