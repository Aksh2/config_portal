package com.rummycircle.pageobjects.newwebadmin.scheduletables;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.Element;
import com.rummycircle.pageobjects.newwebadmin.createtables.CreateTableTemplatesPage;

public class CreateSchedulePage extends AbstractBasePage {

	protected Properties locators = null;
	protected Logger log = null;
	boolean isFirst=false;

	WebDriver driver;
	Element element;

	public CreateSchedulePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("schedules.properties");
		log = Logger.getLogger(CreateTableTemplatesPage.class);
		element = new Element(driver);
	}
	
	public void selectOptionDropDownByVisibleText(String locator, String value) {
		Select selectBox=null ;
		if(locator.contains(".xpath"))
			selectBox=new Select(driver.findElement(By.xpath(locators.getProperty(locator))));
		else if(locator.contains(".name"))
			selectBox=new Select(driver.findElement(By.name(locators.getProperty(locator))));
		else
			selectBox=new Select(driver.findElement(By.id(locators.getProperty(locator))));
		
		selectBox.selectByVisibleText(value);
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
	
	public void clickTableSchedulesTab(){
		clickElement("tab.table.schedules.xpath");
	}
	
	public void clickCreateSchedulesTab(){
		clickElement("tab.create.schedules.xpath");
	}
	
	public void selectVariant(String value){
		selectOptionDropDownByVisibleText("select.variant.name", value);
	}
	
	public void selectTableTemplate(String value){
		selectOptionDropDownByVisibleText("select.tabletemplate.name", value);
	}
	
	public void selectAutoCreationStatus(String value){
		selectOptionDropDownByVisibleText("select.autocreation.name", value);
	}

	public void fillNoOfTables(String value){
		fillElement("input.nooftables.xpath", value);
	}
	
	public void clickCreationStartTime(){
		clickElement("input.starttime.xpath");
	}
	
	public void clickCreationEndTime(){
		clickElement("input.endtime.xpath");
	}
	
	public void clickTodaysDate(){
		clickElement("span.date.today.xpath");
	}
	
	public void clickSpecificEndDate(String value){
		String xpath=String.format(locators.getProperty("span.enddate.xpath"),value);
		log.info(String.format("Dynamically created xpath: %s", xpath));
		cmd.click(By.xpath(xpath), String.format("clicking the day %s",value));
	}
	
	public void clickSpecificStartDate(String value){
		String xpath=String.format(locators.getProperty("span.startdate.xpath"),value);
		log.info(String.format("Dynamically created xpath: %s", xpath));
		cmd.waitUntilElementAppears(By.xpath(xpath));
		cmd.click(By.xpath(xpath), String.format("clicking the day %s",value));
	}
		
	
	public void incrementStartYear(){
		clickElement("span.start.year.arrowup.xpath");
	}
	
	public void decrementStartYear(){
		clickElement("span.start.year.arrowdown.xpath");
	}

	public void incrementStartHour(){
		clickElement("span.start.hour.arrowup.xpath");
	}
	
	public void decrementStartHour(){
		clickElement("span.start.hour.arrowdown.xpath");
	}
	
	public void incrementStartMinute(){
		clickElement("span.start.minute.arrowup.xpath");
	}
	
	public void decrementStartMinute(){
		clickElement("span.start.minute.arrowdown.xpath");
	}
	
	public void incremenetEndYear(){
		clickElement("span.end.year.arrowup.xpath");
	}
	
	public void decrementEndYear(){
		clickElement("span.end.year.arrowdown.xpath");
	}
	public void incrementEndHour(){
		clickElement("span.end.hour.arrowup.xpath");
	}
	
	public void decrementEndHour(){
		clickElement("span.end.hour.arrowdown.xpath");
	}
	
	public void incrementEndMinute(){
		clickElement("span.end.minute.arrowup.xpath");
	}
	
	public void decrementEndMinute(){
		clickElement("span.end.minute.arrowdown.xpath");
	}
	
	public void clickCreateButton(){
		clickElement("button.create.xpath");
	}
	
	
}
