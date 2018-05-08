package com.rummycircle.pageobjects.newwebadmin.createtables;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.Element;
import com.rummycircle.utils.exceptions.RCException;

public class CreateTableTemplatesPage extends AbstractBasePage {

	protected Properties locators = null;
	protected Logger log = null;

	WebDriver driver;
	
	public CreateTableTemplatesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		locators = loadLocators("createtable.properties");
		log = Logger.getLogger(CreateTableTemplatesPage.class);
		

	}

	public void selectOptionDropDownByVisibleText(String xPath, String value) {
		Select selectBox = new Select(driver.findElement(By.xpath(xPath)));
		selectBox.selectByVisibleText(value);
	}

	public void fillTableTemplateName(String name) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.tablename.xpath")))) {
			cmd.sendKeys(
					By.xpath(locators.getProperty("input.tablename.xpath")),
					name, "Filling the table template name");
		} else {
			throw new RCException("Template name input field not found");
		}
	}

	public void fillServiceFee(String fee) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.point.servicefee.xpath")))) {
			cmd.sendKeys(
					By.xpath(locators.getProperty("input.point.servicefee.xpath")),
					fee, "Filling the table template name");
		} else {
			throw new RCException("Service fee input field not found");
		}
	}

	public void selectPrizeType(String type) {
		String selectXpath = locators.getProperty("select.prizetype.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			selectOptionDropDownByVisibleText(selectXpath, type);
		} else {
			throw new RCException("Prize type select field is not found");
		}
	}

	public void selectSkillType(String type) {
		String selectXpath = locators.getProperty("select.skillbased.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			selectOptionDropDownByVisibleText(selectXpath, type);
		} else {
			throw new RCException("Skill type select field is not found");
		}
	}

	public void selectSettlementType(String type) {
		String selectXpath = locators
				.getProperty("select.settlementtype.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			selectOptionDropDownByVisibleText(selectXpath, type);
		} else {
			throw new RCException("Settlement type select field is not found");
		}
	}

	public void selectTableType(String type) {
		String selectXpath = locators.getProperty("select.tabletype.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			selectOptionDropDownByVisibleText(selectXpath, type);
		} else {
			throw new RCException("Table type select field is not found");
		}
	}

	public void selectTableSpeed(String type) {
		String selectXpath = locators.getProperty("select.tablespeed.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			selectOptionDropDownByVisibleText(selectXpath, type);
		} else {
			throw new RCException("Table speed select field is not found");
		}
	}

	public void selectTableSize(String size) {
		String selectXpath = locators.getProperty("select.tablesize.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			selectOptionDropDownByVisibleText(selectXpath, size);
		} else {
			throw new RCException("Table size select field is not found");
		}
	}

	public void selectServiceFeeType(String type) {
		String selectXpath = locators
				.getProperty("select.servicefeetype.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			selectOptionDropDownByVisibleText(selectXpath, type);
		} else {
			throw new RCException(
					"Service field type select field is not found");
		}
	}

	public void selectNoOfPrizesInDeals(String prizeType) {
		String selectXpath = locators.getProperty("select.noofprizes.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			selectOptionDropDownByVisibleText(selectXpath, prizeType);
		} else {
			throw new RCException("Prize select field is not found");
		}
	}

	public void fillPointValue(String points) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.pointvalue.xpath")))) {
			cmd.sendKeys(
					By.xpath(locators.getProperty("input.pointvalue.xpath")),
					points, "Filling the point value input field");
		} else {
			throw new RCException("Point value input field not found");
		}
	}

	public void fillTimeBetweenDeals(String timeInSeconds) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.timebetweendeals.xpath")))) {
			cmd.sendKeys(By.xpath(locators
					.getProperty("input.timebetweendeals.xpath")),
					timeInSeconds, "Filling the time between deals input field");
		} else {
			throw new RCException("Time between deals input field not found");
		}
	}

	public void fillNoOfDeals(String value) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.deals.noofdeals.xpath")))) {
			cmd.sendKeys(By.xpath(locators
					.getProperty("input.deals.noofdeals.xpath")), value,
					"Filling the no of deals input field");
		} else {
			throw new RCException("deals input field not found");
		}
	}

	public void fillEntryFee(String value) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.entryfee.xpath")))) {
			cmd.sendKeys(
					By.xpath(locators.getProperty("input.entryfee.xpath")),
					value, "Filling the entry fee input field");
		} else {
			throw new RCException("entry fee input field not found");
		}
	}

	public void fillNumberOfIncrementsInRaiseRummy(String value) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.raise.numberofincrements")))) {
			cmd.sendKeys(By.xpath(locators
					.getProperty("input.raise.numberofincrements")), value,
					"Filling the no. of increments input field");
		} else {
			throw new RCException("No. of increments input field not found");
		}
	}

	public void fillStartPointInRaiseRummy(String value) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.raise.startpointvalue")))) {
			cmd.sendKeys(By.xpath(locators
					.getProperty("input.raise.startpointvalue")), value,
					"Filling the start point input field in raise rummy");
		} else {
			throw new RCException(
					"start point input field in raise rummy is not found");
		}
	}

	public void fillIncrementPerRoundInRaiseRummy(String value) {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("input.raise.incrementperround")))) {
			cmd.sendKeys(By.xpath(locators
					.getProperty("input.raise.incrementperround")), value,
					"Filling the Increment per round input field in raise rummy");
		} else {
			throw new RCException(
					"Increment per round input field in raise rummy is not found");
		}
	}

	public void selectRejoinAllowed(String option) {
		String selectXpath = locators
				.getProperty("select.pool.rejoinallowed.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			switch (option) {
			case TemplateConstants.OPTION_YES:
				selectOptionDropDownByVisibleText(selectXpath,
						TemplateConstants.OPTION_YES);
				break;
			case TemplateConstants.OPTION_NO:
				selectOptionDropDownByVisibleText(selectXpath,
						TemplateConstants.OPTION_NO);
				break;

			}

		} else {
			throw new RCException("Rejoin allowed select field is not found");
		}
	}

	public void selectSplitAllowed(String option) {
		String selectXpath = locators
				.getProperty("select.pool.splitallowed.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			switch (option) {
			case TemplateConstants.OPTION_YES:
				selectOptionDropDownByVisibleText(selectXpath, option);
				break;
			case TemplateConstants.OPTION_NO:
				selectOptionDropDownByVisibleText(selectXpath,
						TemplateConstants.OPTION_NO);
				break;

			}

		} else {
			throw new RCException("Split allowed select field is not found");
		}
	}

	public void selectAutoSplit(String option) {
		String selectXpath = locators
				.getProperty("select.pool.autosplit.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			switch (option) {
			case TemplateConstants.OPTION_ON:
				selectOptionDropDownByVisibleText(selectXpath,
						TemplateConstants.OPTION_ON);
				break;
			case TemplateConstants.OPTION_OFF:
				selectOptionDropDownByVisibleText(selectXpath,
						TemplateConstants.OPTION_OFF);
				break;

			}

		} else {
			throw new RCException("Split allowed select field is not found");
		}
	}

	public void selectPoolType(String option) {
		String selectXpath = locators.getProperty("select.pooltype.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			selectOptionDropDownByVisibleText(selectXpath, option);
		} else {
			throw new RCException("Pool type split select field is not found");
		}
	}

	public void selectMinimumDeckType(String type) {
		String selectXpath = locators.getProperty("select.decktouse.xpath");
		if (cmd.isElementPresent(By.xpath(selectXpath))) {
			switch (type) {
			case TemplateConstants.MIN_DECK_1:
				selectOptionDropDownByVisibleText(selectXpath,
						TemplateConstants.MIN_DECK_1);
				break;
			case TemplateConstants.MIN_DECK_2:
				selectOptionDropDownByVisibleText(selectXpath,
						TemplateConstants.MIN_DECK_2);
				break;
			case TemplateConstants.MIN_DECK_3:
				selectOptionDropDownByVisibleText(selectXpath,
						TemplateConstants.MIN_DECK_3);
				break;
			}

		} else {
			throw new RCException("Minimum deck select field is not found");
		}
	}

	public void clickCreateButton() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("button.create.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.create.xpath")),
					"Clicking on create button");

		} else {
			throw new RCException("Element not found");
		}
	}

	public void clickConfirmButton() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("button.confirm.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.confirm.xpath")),
					"Clicking on confirm button");

		} else {
			throw new RCException("Confirm button element not found");
		}
	}

	public void clickNewWebAdminTab() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("tab.newwebadmin.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("tab.newwebadmin.xpath")),
					"Clicking on new webadmin tab");

		} else {
			throw new RCException("New webadmin tab not found");
		}
	}

	public void clickTableTemplatesTab() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("tab.tabletemplates.xpath")))) {
			cmd.click(
					By.xpath(locators.getProperty("tab.tabletemplates.xpath")),
					"Clicking on table templates tab");

		} else {
			throw new RCException("Table templates tab not found");
		}
	}

	public void clickCreateTableTemplatesTab() {
		if (cmd.isElementPresent(By.xpath(locators
				.getProperty("tab.createtabletemplates.xpath")))) {
			cmd.click(By.xpath(locators
					.getProperty("tab.createtabletemplates.xpath")),
					"Clicking on create table templates tab");

		} else {
			throw new RCException("Create table templates tab not found");
		}
	}
	
	public void milliSecWait(long milliSec) {
		cmd.sleep(milliSec);
	}
	

}
