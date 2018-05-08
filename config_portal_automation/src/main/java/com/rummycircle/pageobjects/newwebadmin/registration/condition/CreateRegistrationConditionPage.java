package com.rummycircle.pageobjects.newwebadmin.registration.condition;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.pageobjects.newwebadmin.branding.CreateBrandingPage;
import com.rummycircle.utils.exceptions.RCException;

public class CreateRegistrationConditionPage extends AbstractBasePage {

	protected Properties locators = null;
	protected Logger log = null;

	WebDriver driver;

	public CreateRegistrationConditionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("registrationcondition.properties");
		log = Logger.getLogger(CreateRegistrationConditionPage.class);

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

	public void clickRegistrationConditionTab() {
		clickElement("tab.registrationcondition.xpath");
	}

	public void clickCreateRegistrationConditionTab() {
		clickElement("tab.create.registrationcondition.xpath");

	}

	public void selectOptionDropDownByVisibleText(String id, String value) {
		Select selectBox = new Select(driver.findElement(By.id(id)));
		selectBox.selectByVisibleText(value);
	}

	public void fillRegistrationConditionName(String name) {
		if (cmd.isElementPresent(By.id(locators
				.getProperty("input.registrationname.id")))) {
			cmd.sendKeys(
					By.id(locators.getProperty("input.registrationname.id")),
					name, "Filling the registration name");
		} else {
			throw new RCException("Registration name input field not found");
		}
	}

	public void fillCustomMessage(String name) {
		if (cmd.isElementPresent(By.id(locators
				.getProperty("textarea.custom.message.id")))) {
			cmd.sendKeys(
					By.id(locators.getProperty("textarea.custom.message.id")),
					name, "Filling the custom error message");
		} else {
			throw new RCException("Custom error message input field not found");
		}
	}

	public void clickCheckBoxClubType() {
		clickElement("checkbox.allow.club.type.id");
	}

	public void clickCheckBoxDisallowPlayerEntry() {
		clickElement("checkbox.disallow.players.id");
	}

	public void clickCheckBoxAllowPlayerEntry() {
		clickElement("checkbox.allow.player.tickets.id");
	}

	public void clickCheckBoxAllowSpecificPlayer() {
		clickElement("checkbox.allow.specific.player.id");
	}

	public void clickCheckBoxCustomMessage() {
		clickElement("checkbox.allow.custom.players.id");
	}

	public void clickCreateButton() {
		clickElement("button.create.registration.id");
			}

	public void selectClubType(String type) {
		String selectID = locators.getProperty("select.clubtype.id");
		if (cmd.isElementPresent(By.id(selectID))) {
			selectOptionDropDownByVisibleText(selectID, type);
		} else {
			throw new RCException("Club type select field is not found");
		}
	}

	public void selectSpecificType(String type) {
		String selectID = locators
				.getProperty("select.allow.specific.player.id");
		if (cmd.isElementPresent(By.id(selectID))) {
			selectOptionDropDownByVisibleText(selectID, type);
		} else {
			throw new RCException("Specific player select field is not found");
		}
	}

	public void selectDisallowPlayerEntry(String type) {
		String selectID = locators.getProperty("select.disallowplayers.id");
		if (cmd.isElementPresent(By.id(selectID))) {
			selectOptionDropDownByVisibleText(selectID, type);
		} else {
			throw new RCException(
					"Disallow entry players with tickets select field is not found");
		}
	}

	public void selectAllowPlayerEntry(String type) {
		String selectID = locators.getProperty("select.allow.player.id");
		if (cmd.isElementPresent(By.id(selectID))) {
			selectOptionDropDownByVisibleText(selectID, type);
		} else {
			throw new RCException(
					"Allow entry players with tickets select field is not found");
		}
	}

	public String getTextOfErrorMessage() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("div.brandingdetails.xpath")))) {
			return cmd
					.getText(By.xpath(locators
							.getProperty("div.brandingdetails.xpath")),
							"Getting text of branding preview");
		} else {
			throw new RCException("Branding preview div not found");
		}
	}

	public void clickCloseButtonInDialogue() {
		clickElement("button.dialogue.close.id");
	}

	public void clickHideButton() {
		clickElement("button.hide.xpath");
	}

	public String getErrorMessageOfRegCondition() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("div.errormessage.xpath")))) {
			return cmd.getText(
					By.xpath(locators.getProperty("div.errormessage.xpath")),
					"Getting text of branding preview");
		} else {
			throw new RCException("Registration Condition div not found");
		}
	}
	

}
