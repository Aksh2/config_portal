package com.rummycircle.pageobject.running.schedules;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.Element;
import com.rummycircle.pageobjects.newwebadmin.createtables.CreateTableTemplatesPage;
import com.rummycircle.pageobjects.newwebadmin.scheduletables.ScheduleContants;

public class RunningSchedulesPage extends AbstractBasePage {

	protected Properties locators = null;
	protected Logger log = null;

	WebDriver driver;
	Element element;

	public RunningSchedulesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("schedules.properties");
		log = Logger.getLogger(CreateTableTemplatesPage.class);
		element = new Element(driver);
	}

	public String getSelectedValue(String xpathExpression) {
		log.info("xpathexpression:" + xpathExpression);
		Select selectElement = null;
		if (xpathExpression.contains(".xpath")) {
			selectElement = new Select(driver.findElement(By.xpath(locators
					.getProperty(xpathExpression))));
		} else if (xpathExpression.contains(".id")) {
			selectElement = new Select(driver.findElement(By.id(locators
					.getProperty(xpathExpression))));
		} else {
			selectElement = new Select(driver.findElement(By.name(locators
					.getProperty(xpathExpression))));
		}

		WebElement selectedOption = selectElement.getFirstSelectedOption();
		return selectedOption.getText();
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

	public void clickManageSchedulesTab() {
		clickElement("tab.manage.schedules.xpath");
	}

	public void clickCPFPractice() {
		String path = String.format(
				locators.getProperty("anchor.variantname.xpath"),
				ScheduleContants.POINTS_PRACTICE_VARIANT);
		cmd.click(By.xpath(path), String.format("Clicking on %s link",
				ScheduleContants.POINTS_PRACTICE_VARIANT));
	}

	public void clickCPFCash() {
		String path = String.format(
				locators.getProperty("anchor.variantname.xpath"),
				ScheduleContants.POINTS_CASH_VARIANT);
		cmd.click(By.xpath(path), String.format("Clicking on %s link",
				ScheduleContants.POINTS_CASH_VARIANT));

	}

	public void clickRaisePractice() {
		String path = String.format(
				locators.getProperty("anchor.variantname.xpath"),
				ScheduleContants.RAISE_PRACTICE_VARIANT);
		cmd.click(By.xpath(path), String.format("Clicking on %s link",
				ScheduleContants.RAISE_PRACTICE_VARIANT));

	}

	public void clickRaiseCash() {
		String path = String.format(
				locators.getProperty("anchor.variantname.xpath"),
				ScheduleContants.RAISE_CASH_VARIANT);
		cmd.click(By.xpath(path), String.format("Clicking on %s link",
				ScheduleContants.RAISE_CASH_VARIANT));

	}

	public void clickPoolPractice() {
		String path = String.format(
				locators.getProperty("anchor.variantname.xpath"),
				ScheduleContants.POOL_PRACTICE_VARIANT);
		cmd.click(By.xpath(path), String.format("Clicking on %s link",
				ScheduleContants.POOL_PRACTICE_VARIANT));

	}

	public void clickPoolCash() {
		String path = String.format(
				locators.getProperty("anchor.variantname.xpath"),
				ScheduleContants.POOL_CASH_VARIANT);
		cmd.click(By.xpath(path), String.format("Clicking on %s link",
				ScheduleContants.POOL_CASH_VARIANT));

	}

	public void clickDealsPractice() {
		String path = String.format(
				locators.getProperty("anchor.variantname.xpath"),
				ScheduleContants.DEALS_PRACTICE_VARIANT);
		cmd.click(By.xpath(path), String.format("Clicking on %s link",
				ScheduleContants.DEALS_PRACTICE_VARIANT));

	}

	public void clickDealsCash() {
		String path = String.format(
				locators.getProperty("anchor.variantname.xpath"),
				ScheduleContants.DEALS_CASH_VARIANT);
		cmd.click(By.xpath(path), String.format("Clicking on %s link",
				ScheduleContants.DEALS_CASH_VARIANT));

	}

	public void clickRunningSchedules() {
		clickElement("tab.running.schedules.xpath");
	}
	
	public void clickEnabledSchedules() {
		clickElement("tab.enabled.schedules.xpath");
	}
	
	public void clickDisabledSchedules(){
		clickElement("tab.disabled.schedules.xpath");
	}
	
	public void clickScheduledTemplate(){
		clickElement("input.checkbox.xpath");
	}
	
	public void clickEnabledButton(){
		clickElement("button.disabledschedules.enable.xpath");
	}

	public void clickDisabledButton(){
		clickElement("button.enabledschedules.disable.xpath");
	}
	
	public void clickYesButton(){
		clickElement("button.yes.xpath");
	}

	public void clickNoButton(){
		clickElement("button.no.xpath");
	}
}
