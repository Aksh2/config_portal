package com.rummycircle.pageobjects.newwebadmin.createbranding;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.utils.exceptions.RCException;

public class ManageBrandingPage extends AbstractBasePage {

	protected Properties locators = null;
	protected Logger log = null;

	WebDriver driver;

	public ManageBrandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("branding.properties");
		log = Logger.getLogger(CreateBrandingPage.class);

	}

	public void fillSearchTerm(String searchTerm) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.managebranding.search.xpath")))) {
			cmd.sendKeys(By.xpath(locators
					.getProperty("input.managebranding.search.xpath")),
					searchTerm, "Filling the search input field");
		} else {
			throw new RCException("Search input field not found");
		}
	}

	public void clickManageBrandingPreviewLink() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("anchor.managebranding.preview.xpath")))) {
			cmd.click(By.xpath(locators
					.getProperty("anchor.managebranding.preview.xpath")),
					"Clicking on preview link of manage branding");
		} else {
			throw new RCException(
					"Manage Branding preview link element not found");
		}
	}

	public void clickManageBrandingEditLink() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("anchor.managebranding.edit.xpath")))) {
			cmd.click(By.xpath(locators
					.getProperty("anchor.managebranding.edit.xpath")),
					"Clicking on edit link of manage branding");
		} else {
			throw new RCException("Manage Branding edit link element not found");
		}
	}

	public String getTextOfBrandingPreview() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("div.managebranding.details.xpath")))) {
			return cmd.getText(By.xpath(locators
					.getProperty("div.managebranding.details.xpath")),
					"Getting text of Manage branding preview");
		} else {
			throw new RCException("Manage branding preview div not found");
		}
	}

	public String getValueOfBrandingNameInput() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.brandingname.xpath")))) {

			return cmd.getAttribute(By.xpath(
					locators.getProperty("input.brandingname.xpath")), "value",
					"Value of branding name");

		} else {
			throw new RCException("Branding name div is not found");
		}
	}
	
	public String getValueOfBrandingDetailsInput() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("textarea.brandingdetails.xpath")))) {

			return cmd.getAttribute(By.xpath(
					locators.getProperty("textarea.brandingdetails.xpath")), "value",
					"Value of branding details");

		} else {
			throw new RCException("Branding textarea field is not found");
		}
	}
	
	public String getValueOfMobileBrandingDetailsInput() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("textarea.mobilebranding.xpath")))) {

			return cmd.getAttribute(By.xpath(
					locators.getProperty("textarea.mobilebranding.xpath")), "value",
					"Value of mobile branding Details");

		} else {
			throw new RCException("Mobile branding details textarea field is not found");
		}
	}



	public void clickCloseButton() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("button.close.dialogue.xpath")))) {
			cmd.click(By.xpath(locators
					.getProperty("button.close.dialogue.xpath")),
					"Clicking on close button.");

		} else {
			throw new RCException("Close button element not found");
		}
	}

}
