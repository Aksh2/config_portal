package com.rummycircle.pageobjects.newwebadmin.branding;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.Element;
import com.rummycircle.utils.exceptions.RCException;

public class ManageBrandingPage extends AbstractBasePage {

	protected Properties locators = null;
	protected Logger log = null;

	WebDriver driver;

	Element element;

	public ManageBrandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("branding.properties");
		log = Logger.getLogger(CreateBrandingPage.class);

		element = new Element(driver);

	}

	public void clickElement(String locator) {
		element.clickElement(locator, locators);
	}

	public void fillElement(String locator, String value) {
		element.fillElement(locator, value, locators);
	}

	public String getTextOfElement(String locator) {
		return element.getTextOfElement(locator, locators);
	}

	public String getValueOfElement(String locator) {
		return element.getValueOfElement(locator, locators);
	}
	
	

	public void fillSearchTerm(String searchTerm) {
		fillElement("input.managebranding.search.xpath", searchTerm);
		}

	public void clickManageBrandingPreviewLink() {
		clickElement("anchor.managebranding.preview.xpath");
		
	}

	public void clickManageBrandingEditLink() {
		clickElement("anchor.managebranding.edit.xpath");
		
	}

	public String getTextOfBrandingPreview() {
		return getTextOfElement("div.managebranding.details.xpath");
		
	}

	public String getValueOfBrandingNameInput() {
		return getValueOfElement("input.brandingname.xpath");
	}

	public String getValueOfBrandingDetailsInput() {
		return getValueOfElement("textarea.brandingdetails.xpath");
	}

	public String getValueOfMobileBrandingDetailsInput() {
		return getValueOfElement("textarea.mobilebranding.xpath");

	}

	public void clickCloseButton() {
		clickElement("button.close.dialogue.id");
	}
	 

}
