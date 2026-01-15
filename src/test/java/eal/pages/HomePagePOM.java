package eal.pages;

import org.apache.logging.log4j.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eal.utilities.CommonMethods;
import eal.utilities.LogColor;

public class HomePagePOM extends CommonMethods {
	private static final Logger logger = LogManager.getLogger(CommonMethods.class);
	//Call page factory for webelement
	public HomePagePOM() {
		PageFactory.initElements(driver, this);
	}

	By user_id_text = By.xpath("//td[contains(text(),'UserID')]");
	By password_text = By.xpath("//td[contains(text(),'Password')]");
	@FindBy(xpath = "//input[@type='submit' and @name='btnLogin']")
		WebElement loginBtn;
	By loginText = By.xpath("//input[@type='submit' and @name='btnLogin']");
	By seleniumBtn = By.xpath("//a[contains(normalize-space(text()),'Selenium') and @class='dropdown-toggle']");
	By tableDemoBtn = By.xpath("//a[contains(text(),'Table Demo')]");
	By dropDownManu = By.xpath("//ul[@class='dropdown-menu']");

	// Dynamic xpath that can use for "UserID" and "Password"
	String homepage_uiTextContained_elements = "//td[contains(text(),'%s')]";
	String homePage_fields = "//td[contains(text(),'%s')]/following-sibling::td/input";

	public boolean verify_homepage_title() {
		try {
			logger.info("Getting the Actual Title");
			String actualTitle = driver.getTitle();
			logger.info("Got the title");

			String expectedTitle = "Guru99 Bank Home Page";
			if (actualTitle.equals(expectedTitle)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}
	}

	public boolean verify_home_page_elements(String elementsText) {
		try {
			// Convert into actual xpath
			String formattedXpathUiElement = String.format(homepage_uiTextContained_elements, elementsText);
			logger.info(formattedXpathUiElement);
			// Use actual xpath is present or not using Common method Class
			boolean presenece = isElementPresent(By.xpath(formattedXpathUiElement));

			if (presenece) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}
	}

	 public boolean verify_LOGIN_is_visible() {
		 try {
			 boolean presenece = isElementPresent(loginText);
			 if(presenece) {
					return true;
				}else {
					return false;
				}
		} catch (Exception e) {
			logger.error(LogColor.RED+e + LogColor.RESET);
			return false;
		}
	 }

	public boolean verify_seleniumBtn_isVisible() {
		try {
			boolean presenece = isElementPresent(seleniumBtn);
			if (presenece) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}
	}

	public boolean click_on_seleniumBtn() {
		try {
			WebElement element = waitForElement(seleniumBtn);
			waitForClickablility(element);
			clickAndDraw(element);
			boolean presenece = isElementPresent(dropDownManu);
			if (presenece) {
				return true;
			} else {
				logger.error(LogColor.RED + "Dropdown List is not Expand" + "" + LogColor.RESET);
				return false;
			}
		} catch (Exception e) {
			logger.error(LogColor.RED + "Failed to click on Selenium button: " + e.getMessage() + LogColor.RESET);
			return false;
		}
	}

	public boolean verify_table_demo_isVisible() {
		try {
			boolean presenece = isElementPresent(tableDemoBtn);
			if (presenece) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}
	}

	public String passFieldValue(String fieldValue, String fieldName) {
		try {
			String formattedFields = String.format(homePage_fields, fieldName);
			logger.info(formattedFields);
			WebElement field = driver.findElement(By.xpath(formattedFields));

			logger.info("Clicking on Fields");
			clickAndDraw(field);

			logger.info("Passing value: " + fieldValue);
			safeSendKeys(field, fieldValue); // use common method for safe send value

			logger.info("Get the field value to make sure it is filled up properly");
			String actualValue = field.getAttribute("value");
			logger.info("After filled the field value is " + actualValue);

			return actualValue;

		} catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return "Null";
		}

	}
	
	public String clickOnLoginBtn() {
	    try {
	        logger.info("Clicking on login button");
	        
	        waitForClickablility(loginBtn);
	        loginBtn.click();
	        
	        logger.info("After click login button");
	        
	        waitFor(1);
	        
	        Alert alert = waitForAlert();
	        String text = alert.getText();
	        logger.info("alert text: " + text);
	        alert.accept();
	        return text;
	        
	    } catch (Exception e) {
	        logger.error(LogColor.RED + e + LogColor.RESET);
	        return "Null";
	    }
	}

}
