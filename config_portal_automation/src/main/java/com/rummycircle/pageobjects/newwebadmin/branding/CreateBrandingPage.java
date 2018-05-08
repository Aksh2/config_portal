package com.rummycircle.pageobjects.newwebadmin.branding;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.Element;
import com.rummycircle.utils.exceptions.RCException;

public class CreateBrandingPage extends AbstractBasePage {

	protected Properties locators = null;
	protected Logger log = null;

	WebDriver driver;
	Element element;
	
	public CreateBrandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("branding.properties");
		log = Logger.getLogger(CreateBrandingPage.class);
		element= new Element(driver);
	}
	
	public void clickElement(String locator){
		element.clickElement(locator, locators);
	}

	public void fillElement(String locator,String value){
		element.fillElement(locator, value, locators);
	}
	
	public String getTextOfElement(String locator){
		return element.getTextOfElement(locator, locators);
	}


	public void fillBrandingName(String name) {
		fillElement("input.brandingname.xpath", name);
		
	}

	public void fillTournamentDetails(String details) {
		fillElement("textarea.brandingdetails.xpath", details);
		
	}

	public void fillMobileTournamentDetails(String details) {
		fillElement("textarea.mobilebranding.xpath", details);
		
	}

	public void clickBrandingTournamentPreviewLink() {
		clickElement("anchor.tournament.branding.preview.xpath");
		}

	public void clickMobileTournamentPreviewLink() {
		clickElement("anchor.mobile.branding.preview.xpath");

	}

	public void clickCloseButton() {
	clickElement("button.close.dialogue.id");

	}

	public void clickCreateButton() {
		clickElement("button.branding.create.xpath");

	}

	public void clickBrandingTab() {
		clickElement("tab.branding.xpath");

	}

	public void clickCreateBrandingTab() {
		clickElement("tab.createbranding.xpath");
		
	}

	public void clickManageBrandingTab() {
		clickElement("tab.managebranding.xpath");
		
	}

	public void clickCloseButtonInCreateBrandingDialogue() {
		clickElement("button.close.save.dialogue.xpath");
	
	}

	public String getTextOfBrandingPreview() {
		return getTextOfElement("div.brandingdetails.xpath");
	}

	public String getErrorMessageOfBranding() {
		return getTextOfElement("span.errormsg.xpath");
	
	}
	              
	 

}
