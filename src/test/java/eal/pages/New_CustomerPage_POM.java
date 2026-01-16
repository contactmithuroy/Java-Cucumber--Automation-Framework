package eal.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import eal.utilities.CommonMethods;
import eal.utilities.LogColor;

public class New_CustomerPage_POM  extends CommonMethods {
	private static final Logger logger = LogManager.getLogger(CommonMethods.class);

	public New_CustomerPage_POM() {
		PageFactory.initElements(driver, this);
	}
	
	
	//-------------------------------------
	//-----------Locator ------------
	//-------------------------------------
	
	private static final String FIELD_INPUT_XPATH = 
			"//td[text()='%s']/following-sibling::td/input | //td[text()='%S']/following-sibling::td/input | //td[text()='%s']/following-sibling::td/textarea ";
	
	private static final String GENDER_INPUT_XPATH = 
			"//td[text()='Gender']/following-sibling::td/input[@value ='%s']";
	
	//-------------------------------------
	//-----------Action Method ------------
	//-------------------------------------
	public boolean enterTypableFieldValue(String fielName, String value) {
		try {
			String formatedButtonXpath = String.format(FIELD_INPUT_XPATH, fielName,fielName, fielName );
			logger.info("Xpath: " +formatedButtonXpath);
			
			WebElement input = driver.findElement(By.xpath(formatedButtonXpath));
			safeSendKeys(input, value);
			
			String actualValue =  input.getAttribute("value");
			boolean success = actualValue.equals(value);
			
			if(success) {
				logger.info("Entered value for field: " 
						+fielName + " -> "+ value);
				logger.info(LogColor.DarkGreen+ "Insert value on field: "+fielName+ LogColor.RESET);
			}else {
				logger.warn("Entered value mismatch: " +fielName + 
						" Expected: "+ value + "Actual Value: "+actualValue);
			}
			
			return success;
			
		} catch (Exception e) {
			logger.error(LogColor.RED + "Error entering value for field "+fielName+ LogColor.RESET);
			return false;
		}
	}
	
	public boolean enteredDataFieldValue(String fielName, String value) {
		try {
			String formatedButtonXpath = String.format(FIELD_INPUT_XPATH, fielName,fielName, fielName );
			logger.info("Xpath: " +formatedButtonXpath);
			
			WebElement input = driver.findElement(By.xpath(formatedButtonXpath));
			
			boolean isDateEntered = safeSetDateValue(input, value);
		
			return isDateEntered;
			
		} catch (Exception e) {
			logger.error(LogColor.RED + "Error entering value for field "+fielName+ LogColor.RESET);
			return false;
		}
	}
	
	public boolean selectGender(String fielName, String value) {
		try {
			
			WebElement input = null;
			
			if(value.equalsIgnoreCase("male")) {
				String formatedButtonXpath = String.format(GENDER_INPUT_XPATH, "m" ); // create string xpath " "
				input = driver.findElement(By.xpath(formatedButtonXpath)); //make xpath as a locator
			}
			else if(value.equalsIgnoreCase("female")) {
				String formatedButtonXpath = String.format(FIELD_INPUT_XPATH, "f" );
				input = driver.findElement(By.xpath(formatedButtonXpath));
			}
			
			if(input == null) {
				logger.error(LogColor.RED + "Invalid Gender caption passed "+fielName+ LogColor.RESET);
				return false;
			}
			
			safeSelectRadioButton(input);
			
			boolean isRadioButtonSelected = input.isSelected();
			if(isRadioButtonSelected) {
				logger.info("Radio Button Selected successfully for :" 
						+fielName + " -> "+ value);
				logger.info(LogColor.DarkGreen+ "Insert value on field: "+fielName+ LogColor.RESET);
			}else {
				logger.warn("Radio button not selected : " +fielName + 
						" Expected: "+ value + "Actual Value: "+value);
			}
			return isRadioButtonSelected;
			
		} catch (Exception e) {
			logger.error(LogColor.RED + "Error entering value for field "+fielName+ LogColor.RESET);
			return false;
		}
	}
	
}
