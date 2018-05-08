package com.rummycircle.pageobjects.newwebadmin.breakstructure;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.Element;
import com.rummycircle.pageobjects.newwebadmin.branding.CreateBrandingPage;
import com.rummycircle.utils.exceptions.RCException;

public class ManageBreakStructurePage extends AbstractBasePage {
	
	protected Properties locators = null;
	protected Logger log = null;

	WebDriver driver;
	Element element;

	public ManageBreakStructurePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("breakstructure.properties");
		log = Logger.getLogger(ManageBreakStructurePage.class);
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



	
	public String getValueOfBreakStructureName() {
		return getValueOfElement("input.breakstructure.name.xpath");
		
	}
	
	public void clickManageBreakStructureTab() {
		clickElement("tab.managebreakstructure.xpath");
		
	}
	
	public String[] getBreakStructureValues() {
		
		List<WebElement> values = cmd.findElements(By.xpath(locators.getProperty("input.totalinputfield.xpath")));
		String xpath="";
		String value[] = new String[values.size()];
		for(int index=0;index<values.size();index++){
			xpath = String.format(locators.getProperty("input.timebetweenrounds.xpath"), index+1);
			if (cmd.isElementPresent(xpath)) {
			value[index]=cmd.getAttribute(
				By.xpath(xpath),
				"value", String.format("Break structure at %d", index+1));
			
			
		}else{
				throw new RCException(String.format("Time between input field not found at %s",index+1));
			}	
		}
		log.info("Retrieved array:"+Arrays.toString(value));
		return value;
	}
	
	public void fillSearchTerm(String term) {
		fillElement("input.search.xpath", term);
		
	}
	
	public void clickEditLink() {
		clickElement("anchor.edit.xpath");
	}
	 
	
	
	
	
	


}
