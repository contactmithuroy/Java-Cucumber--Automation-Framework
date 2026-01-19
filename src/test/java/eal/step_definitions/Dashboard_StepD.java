package eal.step_definitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eal.utilities.CommonMethods;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.util.List;

public class Dashboard_StepD extends CommonMethods {
	private static final Logger logger = LogManager.getLogger(CommonMethods.class);

	@Given("{string} button is  visible")
	public void button_is_visible(String buttonName) {
		logger.info("Clicking on " + buttonName + " button");
		boolean elementPresences = dashboardpage_pom.verifyLinkButtonExistance(buttonName);

		softAssert.softAssertTrue(elementPresences, buttonName + " is visible", buttonName + " is not visible");
	}

	@Then("the {string} sub-menu should be visible under the menu")
	public void the_sub_menu_should_be_visible_under_the_menu(String subManu) {
		logger.info("Verifying Click " + subManu + "  From the top Nav Manu");
		boolean subNaveBtnisVisible = dashboardpage_pom.verify_sub_nav_manue_Btn_isVisible(subManu);
		softAssert.softAssertTrue(subNaveBtnisVisible, subManu + " is present in the  dropdown List",
				subManu + " is not present in the  dropdown List");

	}

	@Given("I open the {string} page with title {string}")
	public void i_open_the_page_with_title(String pageButton, String expectedTitle) {
		boolean titleMatched = dashboardpage_pom.clickOnLeftPanelButton(pageButton, expectedTitle);

		softAssert.softAssertTrue(titleMatched, pageButton + " is visible",
				" Title didnt match and could not navigate to" + pageButton);
	}

	@Given("I fill the form using file {string} and sheet {string} with fields:")
	public void i_fill_the_form_using_file_and_sheet_with_fields(String fielName, String sheetName,
			DataTable dataTable) {

		// Initialize the excel context(Opens file, Loads sheet, map headers)
		excelUtil.openExcel(fielName, sheetName);

		// 2. Get the list of fields Row keys from the cucumber DataTable
		List<String> fields = dataTable.asList();

		// 3. Start for loog to getting all value
		for (String fieldKey : fields) {
			String field = fieldKey.trim();

			String value = excelUtil.getCellData(field, "Values").trim();

			if (value.isEmpty()) {
				logger.warn("X Data not found in Excel for field/key: " + field);
				softAssert.softAssertFail("X Data not found in Excel for field/key: " + field);
				continue;
			}
			boolean result = false;
			// 4 Perform UI Action based on the field type
			switch (field) {

			// Same function calling so we can use below format
			case "Customer Name":
			case "Address":
			case "City":
			case "State":
			case "Pin":
			case "Mobile Number":
			case "E-mail":
				result = newcustomerpage_pom.enterTypableFieldValue(field, value);
				break;

			case "Gender":
				result = newcustomerpage_pom.selectGender(field, value);
				break;

			case "Date of Birth":
				result = newcustomerpage_pom.enteredDataFieldValue(field, value);

			default:
				logger.warn("File is not mapped in step defination swithch case: " + field);
			}
			softAssert.softAssertTrue(result, field + " to fill on UI Element. ",
					field + " failed to fill on UI Element.");
		}

		// finished for loop
	}

	@Then("Click submit button")
	public void click_submit_button() {

	}

}

//Finished Class

/*
 * //Generic way to use switch case case "Customer Name": result =
 * newcustomerpage_pom.enterTypableFieldValue(field, value); break; case
 * "Gender": break; case "Date Of Birth": break; case "Address": result =
 * newcustomerpage_pom.enterTypableFieldValue(field, value); break; case "City":
 * result = newcustomerpage_pom.enterTypableFieldValue(field, value); break;
 * case "State": result = newcustomerpage_pom.enterTypableFieldValue(field,
 * value); break; case "Pin": result =
 * newcustomerpage_pom.enterTypableFieldValue(field, value); break; case
 * "Mobile Number": result = newcustomerpage_pom.enterTypableFieldValue(field,
 * value); break; case "E-mail": result =
 * newcustomerpage_pom.enterTypableFieldValue(field, value); break;
 */
