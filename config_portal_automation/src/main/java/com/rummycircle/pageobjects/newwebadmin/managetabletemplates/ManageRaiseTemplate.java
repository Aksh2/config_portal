package com.rummycircle.pageobjects.newwebadmin.managetabletemplates;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.Element;
import com.rummycircle.pageobjects.newwebadmin.createtables.CreateTableTemplatesPage;

public class ManageRaiseTemplate extends AbstractBasePage {

	protected Properties locators = null;
	protected Logger log = null;

	WebDriver driver;
	Element element;

	public ManageRaiseTemplate(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("createtable.properties");
		log = Logger.getLogger(CreateTableTemplatesPage.class);
		element = new Element(driver);
	}
	
	public String getSelectedValue(String xpathExpression) {
		log.info("xpathexpression:" + xpathExpression);
		Select selectElement = new Select(driver.findElement(By.xpath(locators.getProperty(xpathExpression))));
		WebElement selectedOption = selectElement.getFirstSelectedOption();
		return selectedOption.getText();
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

	
	public void clickManageTemplateTab() {
		clickElement("tab.managetable.xpath");
	}

	public void clickEditLink(){
		clickElement("anchor.table.edit");
	}
	
	public void clickRaisePracticeLink() {	
		clickElement("anchor.raise.practice");
	}
	
	public void clickRaiseCashLink() {	
		clickElement("anchor.raise.cash");
	}
	
	public String getValueOfTemplateName(){
		return getValueOfElement("input.tablename.xpath");
	}
	
	public String getValueOfServiceFee(){
		return getValueOfElement("input.point.servicefee.xpath");
	}
	
	public String getValueOfEntryFee(){
		return getValueOfElement("input.entryfee.xpath");
	}
	
	public String getSelectedValueOfTableSpeed(){
		return getSelectedValue("select.tablespeed.xpath");
	}
	
	public String getSelectedValueOfSettlementType(){
		return getSelectedValue("select.settlementtype.xpath");
	}
	
	public String getSelectedValueOfTableType(){
		return getSelectedValue("select.tabletype.xpath");
	}
	
	public String getSelectedValueOfPrizeType(){
		//	return getValueOfElement("select.prizetype.xpath");
			return getSelectedValue("select.prizetype.xpath");
	}
	
	public String getSelectedValueOfTableSize(){
		return getSelectedValue("select.tablesize.xpath");
	}
	
	public String getSelectedValueOfServiceFeeType(){
		return getSelectedValue("select.servicefeetype.xpath");
	}
	
	public String getSelectedValueOfMinDecks(){
		return getSelectedValue("select.decktouse.xpath");
	}
	
	public String getValueOfTimeBetweenDeals(){
		return getValueOfElement("input.timebetweendeals.xpath");
	}
	
	public String getValueOfStartPoint(){
		return getValueOfElement("input.raise.startpointvalue");
	}
	
	public String getValueOfIncrementsPerRound(){
		return getValueOfElement("input.raise.incrementperround");
	}
	
	public String getValueOfNumberOfIncrements(){
		return getValueOfElement("input.raise.numberofincrements");
	}
	
	
	

}
