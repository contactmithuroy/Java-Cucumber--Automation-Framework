package eal.pages;

import org.apache.logging.log4j.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eal.utilities.CommonMethods;
import eal.utilities.ConfigurationReader;
import eal.utilities.LogColor;

public class HomePagePOM extends CommonMethods {
	private static final Logger logger = LogManager.getLogger(CommonMethods.class);

	// Call page factory for webelement
	public HomePagePOM() {
		PageFactory.initElements(driver, this);
	}

	By user_id_text = By.xpath("//td[contains(text(),'UserID')]");
	By password_text = By.xpath("//td[contains(text(),'Password')]");
	@FindBy(xpath = "//input[@type='submit' and @name='btnLogin']")
	WebElement loginBtn;

	@FindBy(xpath = "//input[@type='reset' and @name='btnReset']")
	WebElement resetBtn;

	By loginText = By.xpath("//input[@type='submit' and @name='btnLogin']");
	
	private static final String NAV_MANU_BUTTON =
			"//a[contains(normalize-space(text()),'%s') and @class='dropdown-toggle']";
	
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

			String expectedTitle = ConfigurationReader.getProperty("homeTitle");
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
	
	public boolean verify_login_homepage_title() {
		try {
			logger.info("Getting the Actual Title");
			String actualTitle = driver.getTitle();
			logger.info("Got the actual title"+actualTitle);

			String expectedTitle = ConfigurationReader.getProperty("dashboardTitle");
			logger.info("Expected Title: " +expectedTitle);
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

	public boolean verify_nav_manue_Btn_isVisible(String navManu) {
		try {
			
			String formatedButtonXpath = String.format(NAV_MANU_BUTTON, navManu );
			logger.info("Xpath: " +formatedButtonXpath);
			
			boolean ElementPresence = isElementPresent(By.xpath(formatedButtonXpath));
			if (ElementPresence) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}
	}

	public boolean click_on_nav_manue_Btn(String navManu) {
		try {
			String formatedButtonXpath = String.format(NAV_MANU_BUTTON, navManu );
			logger.info("Xpath: " +formatedButtonXpath);
						
			WebElement button = driver.findElement(By.xpath(formatedButtonXpath));
			
			logger.info("Click on Nav Manu");
			clickAndDraw(button);
			
			logger.info("Check the Nav Drop down manue are present");
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

			dashboardpage_pom.clickLogout();

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
	
	public String sanitizeInput(String value) {
	    return value == null ? "" : value.trim().replaceAll("^\"|\"$", "");
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

	public boolean clickOnLoginBtnWithValidCredentials() {
		try {
			waitForClickablility(loginBtn);
			loginBtn.click();

			logger.info("Getting the Actual Title");

			String actualTitle = driver.getTitle();
			logger.info(LogColor.DarkGreen + "Getting Actual title:" + actualTitle + LogColor.RESET);

			String expectedTitle = ConfigurationReader.getProperty("dashboardTitle");
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

	public boolean isUserIdAndPasswordReset() {

		logger.info("Click on Reset Button");

		String userIdXpath = String.format(homePage_fields, "UserID");
		String passwordXpath = String.format(homePage_fields, "Password");

		WebElement userIDField = driver.findElement(By.xpath(userIdXpath));
		WebElement passwordField = driver.findElement(By.xpath(passwordXpath));

		// Values BEFORE reset
		String userIdBeforeReset = userIDField.getAttribute("value");
		String passwordBeforeReset = passwordField.getAttribute("value");

		logger.info("UserID value before reset: " + userIdBeforeReset);
		logger.info("Password value before reset: " + passwordBeforeReset);

		// Perform reset
		userIDField.clear();
		passwordField.clear();

		// Values AFTER reset
		String userIdAfterReset = userIDField.getAttribute("value");
		String passwordAfterReset = passwordField.getAttribute("value");

		logger.info("UserID value after reset: " + userIdAfterReset);
		logger.info("Password value after reset: " + passwordAfterReset);

		// Reset validation
		boolean isUserIdReset = userIdAfterReset == null || userIdAfterReset.trim().isEmpty();
		boolean isPasswordReset = passwordAfterReset == null || passwordAfterReset.trim().isEmpty();

		return isUserIdReset && isPasswordReset;
	}

	public String celarHomePageField(String fieldName) {
		String formattedFields = String.format(homePage_fields, fieldName);
		logger.info(formattedFields);
		WebElement field = driver.findElement(By.xpath(formattedFields));

		field.clear();

		String ActualFieldValueAterClear = getAttributeValue(field, "value");
		return ActualFieldValueAterClear;

	}

}
