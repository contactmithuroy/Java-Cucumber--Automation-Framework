package eal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.*;
import eal.utilities.CommonMethods;
import eal.utilities.LogColor;

public class DashboardPage_POM extends CommonMethods {
	public DashboardPage_POM() {
		PageFactory.initElements(driver, this);
	}

	By logoutBtn = By.xpath("//a[text()='Log out']");
	// Controll Label XPath
	String Dashboard_Elements = "//a[text()='%s']";

	private static final String SUB_NAV_MANU_BUTTON = "//a[text()='%s']";

	public boolean verify_sub_nav_manue_Btn_isVisible(String subManu) {
		try {

			String formatedButtonXpath = String.format(SUB_NAV_MANU_BUTTON, subManu);
			logger.info("Xpath: " + formatedButtonXpath);

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

	public boolean verifyLinkButtonExistance(String buttonName) {

		try {
			String formatedButtonXpath = String.format(Dashboard_Elements, buttonName);
			logger.info("Xpath: " + formatedButtonXpath);

			waitForNetworkIdle();

			boolean ElementPresence = isElementPresent(By.xpath(formatedButtonXpath));
			return ElementPresence;

		} catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}

	}

	public boolean clickOnLeftPanelButton(String pageButton, String expectedTitle) {
		try {
			String formatedButtonXpath = String.format(Dashboard_Elements, pageButton);
			logger.info("Xpath: " + formatedButtonXpath);

			waitForNetworkIdle();

			boolean ElementPresence = isElementPresent(By.xpath(formatedButtonXpath));
			logger.info("Elements is presence");

			WebElement button = driver.findElement(By.xpath(formatedButtonXpath));
			clickAndDraw(button);

			boolean isTitleMatched = verifyPageTitle(expectedTitle);

			return isTitleMatched;
		} catch (Exception e) {
			logger.error(LogColor.RED + e + LogColor.RESET);
			return false;
		}

	}

	public void clickLogout() {
		boolean presence_logout = isElementPresent(logoutBtn);
		if (presence_logout) {
			logger.info("Logout button is present, clicking on that");
			WebElement logout_button = driver.findElement(logoutBtn);
			scrollToElement(logout_button);
			clickAndDraw(logout_button);
			acceptAlert();
			waitForPageAndAjaxToLoad();
		}

	}

}
