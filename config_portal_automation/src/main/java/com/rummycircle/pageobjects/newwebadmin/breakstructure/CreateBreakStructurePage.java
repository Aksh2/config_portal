package com.rummycircle.pageobjects.newwebadmin.breakstructure;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.Element;
import com.rummycircle.pageobjects.newwebadmin.branding.CreateBrandingPage;
import com.rummycircle.utils.exceptions.RCException;

public class CreateBreakStructurePage extends AbstractBasePage {
	
	protected Properties locators = null;
	protected Logger log = null;

	WebDriver driver;
	Element element;
	public CreateBreakStructurePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("breakstructure.properties");
		log = Logger.getLogger(CreateBreakStructurePage.class);
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
	
	public String getValueOfElement(String locator) {
		return element.getValueOfElement(locator, locators);
	}


	
	
	public void clickBreakStructureTab() {
		clickElement("tab.breakstructure.xpath");
		
	}

	public void clickCreateBreakStructureTab() {
		clickElement("tab.createbreakstructure.xpath");
		
	}
	
	public void clickCreateButton() {
		clickElement("button.create.xpath");
		
	}
	
	public void fillBreakStructureName(String name) {
		fillElement("input.breakstructure.name.xpath",name);
		
	} 
	
	public void fillBreakStructureValue(String values[]) {
		
			String xpath="";
			for(int index=0;index<values.length;index++){
				xpath = String.format(locators.getProperty("input.timebetweenrounds.xpath"), index+1);
				System.out.println(xpath);
				if (cmd.isElementPresent(xpath)) {
				cmd.sendKeys(
					By.xpath(xpath),
					values[index], String.format("Filling break structure at %s with the value %s", index+1,values[index]));
			}
				else{
					throw new RCException(String.format("Time between input field not found at %s",index+1));
				}
		}
	}
	public void clickCloseButton() {
		clickElement("button.close.xpath");
		
	}
	
	public void clickHideButton() {
		clickElement("button.hide.xpath");
		
	}
	
	public String getErrorMessageOfBreakStructure() {
		
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("div.errormessage.xpath")))) {
			return cmd.getAttribute(By.xpath(locators.getProperty("div.errormessage.xpath")),"innerHTML",
					"Getting text of break structure error message");
		} else {
			throw new RCException("Break Structure error message div not found");
		}
	}
	 
}
