package com.rummycircle.pageobjects.newwebadmin.registration.condition;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.utils.exceptions.RCException;

public class ManageRegistrationConditionPage extends AbstractBasePage {

	protected Properties locators = null;
	protected Logger log = null;

	WebDriver driver;

	public ManageRegistrationConditionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("registrationcondition.properties");
		log = Logger.getLogger(ManageRegistrationConditionPage.class);

	}

	public String getSelectedValue(String id) {
		Select selectElement = new Select(driver.findElement(By.id(id)));
		WebElement selectedOption = selectElement.getFirstSelectedOption();
		return selectedOption.getText();
	}

	public void clickElement(String locator) {
		if (locator.contains("id")) {
			if (cmd.isElementPresent(By.id(locators.getProperty(locator)))) {
				cmd.click(By.id(locators.getProperty(locator)), String.format(
						"Click on the element with the locator %s", locator));

			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}

		} else {
			if (cmd.isElementPresent(By.xpath(locators.getProperty(locator)))) {
				cmd.click(By.xpath(locators.getProperty(locator)), String
						.format("Click on the element with the locator %s",
								locator));

			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}
		}
	}

	public String getAttributeValueOfElement(String locator) {
		if (locator.contains("id")) {
			if (cmd.isElementPresent(By.id(locators.getProperty(locator)))) {
				return cmd.getAttribute(By.id(locators
						.getProperty("input.registrationname.id")), "value",
						String.format("Value of the element %s", locator));
			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}

		} else {
			if (cmd.isElementPresent(By.xpath(locators.getProperty(locator)))) {
				return cmd.getAttribute(By.id(locators
						.getProperty("input.registrationname.id")), "value",
						String.format("Value of the element %s", locator));
			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}
		}
	}

	public void clickManageRegistrationConditionTab() {
		clickElement("tab.manage.registrationcondition.xpath");
	}

	public String getValueOfTournamentNameInput() {
		return getAttributeValueOfElement("input.brandingname.xpath");
	}

	public String getValueOfSelectClubType() {
		String selectID = locators.getProperty("select.clubtype.id");
		if (cmd.isElementPresent(By.id(selectID))) {

			return getSelectedValue(selectID);
		} else {
			throw new RCException(
					"Club type select element is not found");
		}
	}

	public String getValueOfSelectDisallowEntryType() {
		String selectID = locators.getProperty("select.disallowplayers.id");
		if (cmd.isElementPresent(By.id(selectID))) {

			return getSelectedValue(selectID);
		} else {
			throw new RCException(
					"Disallow players select element is not found");
		}
	}

	public String getValueOfAllowEntryType() {
		String selectID = locators.getProperty("select.allow.player.id");
		if (cmd.isElementPresent(By.id(selectID))) {
			return getSelectedValue(selectID);
		} else {
			throw new RCException("Allow players select element is not found");
		}
	}

	public String getValueOfAllowSpecificEntryType() {
		String selectID = locators
				.getProperty("select.allow.specific.player.id");
		if (cmd.isElementPresent(By.id(selectID))) {
			return getSelectedValue(selectID);
		} else {
			throw new RCException(
					"Allow specific players select element is not found");
		}
	}

	public String getTextOfErrorMessage() {
		if (cmd.isElementPresent(By.id(locators
				.getProperty("textarea.custom.message.id")))) {
			return cmd.getText(
					By.id(locators.getProperty("textarea.custom.message.id")),
					"Getting text of custom error message");
		} else {
			throw new RCException("Custom error message textarea not found");
		}
	}

	public void fillSearchTerm(String term) {
		if (cmd.isElementPresent(By.id(locators
				.getProperty("input.search.registration.id")))) {
			cmd.sendKeys(
					By.id(locators.getProperty("input.search.registration.id")),
					term, "Filling the Search input field");
		} else {
			throw new RCException("Search input field not found");
		}
	}

	public void clickCloseButtonInDialogue() {
		clickElement("button.dialogue.close.id");
	}

	public void clickEditLink() {
		clickElement("anchor.manage.preview.xpath");

	}

	public void milliSecWait(long milliSec) {
		cmd.sleep(milliSec);
	} 
	

}
