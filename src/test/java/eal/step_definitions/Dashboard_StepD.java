package eal.step_definitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eal.utilities.CommonMethods;
import io.cucumber.java.en.Given;

public class Dashboard_StepD extends CommonMethods {
	private static final Logger logger = LogManager.getLogger(CommonMethods.class);

	@Given("{string} button is not visible")
	public void button_is_not_visible(String buttonName) {
		logger.info("Clicking on " +buttonName+ " button");
		boolean elementPresences = dashboardpage_pom.verifyLinkButtonExistance(buttonName);
		
		softAssert.softAssertTrue(elementPresences,buttonName + " is visible",buttonName +  " is not visible");
	}
	
	
}
