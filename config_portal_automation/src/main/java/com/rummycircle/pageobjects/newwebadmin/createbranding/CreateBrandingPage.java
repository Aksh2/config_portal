package com.rummycircle.pageobjects.newwebadmin.createbranding;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.utils.exceptions.RCException;

public class CreateBrandingPage extends AbstractBasePage {
	
	protected Properties locators = null;
	protected Logger log = null;

	WebDriver driver;

	public CreateBrandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("branding.properties");
		log = Logger.getLogger(CreateBrandingPage.class);

	}

	
	public void fillBrandingName(String name) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.brandingname.xpath")))) {
			cmd.sendKeys(
					By.xpath(locators.getProperty("input.brandingname.xpath")),
					name, "Filling the branding name");
		} else {
			throw new RCException("Branding name input field not found");
		}
	}
	
	public void fillTournamentDetails(String details) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("textarea.brandingdetails.xpath")))) {
			cmd.sendKeys(
					By.xpath(locators.getProperty("textarea.brandingdetails.xpath")),
					details, "Filling the branding tournament details");
		} else {
			throw new RCException("Branding tournament details input field not found");
		}
	}
	
	public void fillMobileTournamentDetails(String details) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("textarea.mobilebranding.xpath")))) {
			cmd.sendKeys(
					By.xpath(locators.getProperty("textarea.mobilebranding.xpath")),
					details, "Filling the mobile tournament details");
		} else {
			throw new RCException("Mobile tournament details input field not found");
		}
	}
	
	public void clickBrandingTournamentPreviewLink() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("anchor.tournament.branding.preview.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("anchor.tournament.branding.preview.xpath")),
					"Clicking on preview link of branding tournament details ");

		} else {
			throw new RCException("Branding tournament details element not found");
		}
	}

	public void clickMobileTournamentPreviewLink() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("anchor.mobile.branding.preview.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("anchor.mobile.branding.preview.xpath")),
					"Clicking on preview link of mobile tournament details ");

		} else {
			throw new RCException("mobile tournament details element not found");
		}
	}
	
	public void clickCloseButton() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("button.close.dialogue.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.close.dialogue.xpath")),
					"Clicking on close button.");

		} else {
			throw new RCException("Close button element not found");
		}
	}

	public void clickCreateButton() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("button.branding.create.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.branding.create.xpath")),
					"Clicking on create button.");

		} else {
			throw new RCException("Create button element not found");
		}
	}
	
	public void clickBrandingTab() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("tab.branding.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("tab.branding.xpath")),
					"Clicking on new branding tab");

		} else {
			throw new RCException("Branding tab not found");
		}
	}

	public void clickCreateBrandingTab() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("tab.createbranding.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("tab.createbranding.xpath")),
					"Clicking on create branding tab");
		} else {
			throw new RCException("Create branding tab not found");
		}
	}
	
	public void clickManageBrandingTab() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("tab.managebranding.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("tab.managebranding.xpath")),
					"Clicking on manage branding tab");
		} else {
			throw new RCException("Manage branding tab not found");
		}
	}
	
	public void clickCloseButtonInCreateBrandingDialogue() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("button.close.save.dialogue.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.close.save.dialogue.xpath")),
					"Clicking on close button in save branding dialogue");
		} else {
			throw new RCException("Close button in save branding dialogue is not found");
		}
	}
	
	public String getTextOfBrandingPreview() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("div.brandingdetails.xpath")))) {
			return cmd.getText(By.xpath(locators.getProperty("div.brandingdetails.xpath")),
					"Getting text of branding preview");
		} else {
			throw new RCException("Branding preview div not found");
		}
	}
	
	public String getErrorMessageOfBranding() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("span.errormsg.xpath")))) {
			return cmd.getText(By.xpath(locators.getProperty("span.errormsg.xpath")),
					"Getting text of branding preview");
		} else {
			throw new RCException("Branding preview div not found");
		}
	}

	
	

	
	
	
	

}
