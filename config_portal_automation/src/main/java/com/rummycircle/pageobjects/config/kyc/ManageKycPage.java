package com.rummycircle.pageobjects.config.kyc;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.mortbay.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.pageobjects.login.LoginPage;
import com.rummycircle.utils.exceptions.RCException;

public class ManageKycPage extends AbstractBasePage {

	protected Properties locators = null;
	protected Logger log = null;

	public ManageKycPage(WebDriver driver) {
		super(driver);
		locators = loadLocators("kyc.properties");
		log = Logger.getLogger(LoginPage.class);
	}

	public void clickMangeKycTab() {
		if (cmd.isElementPresent(By.xpath(locators.getProperty("tab.mangekyc.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("tab.mangekyc.xpath")), "Clicking on Mange KYC parameter tab");
		} else {
			throw new RCException("Manage KYC tab not found !");
		}
	}

	public void clickEditParametersButton() {
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.editparameters.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.editparameters.xpath")),
					"Clicking on Edit parameters button");
		} else {
			throw new RCException("Edit parmeters button not found !");
		}
	}

	public void clickLatestUserIdButton() {
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.getlatestuserid.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.getlatestuserid.xpath")),
					"Clicking on Get Latest UserId button");
		} else {
			throw new RCException("Get Latest UserId button not found !");
		}
	}

	public void clickCalendarButton() {
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.calendar.xpath")))) {
			new Actions(driver)
					.moveToElement(driver.findElement(By.xpath(locators.getProperty("button.calendar.xpath")))).click()
					.click().perform();
		} else {
			throw new RCException("Calendar button not found !");
		}
	}

	public void clickOnASpecificDay(String day) {
		String xpath = String.format(locators.getProperty("button.select.date.xpath"), day);
		Log.info(String.format("The dynamic xpath is: %s",xpath));
		if (cmd.isElementPresent(By.xpath(xpath))) {
			cmd.click(By.xpath(xpath), String.format("Clicking %s day on the calendar", day));
		} else {
			throw new RCException("The specified date is not found !");
		}
	}
	
	public void clickOnUpArrowOfHourField(){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.calendar.hour.uparrow.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.calendar.hour.uparrow.xpath")),
					"Clicking on up arrow button of hour field");
		} else {
			throw new RCException("Up arrow button of hour field not found !");
		}
	}
	
	public void clickOnDownArrowOfHourField(){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.calendar.hour.downarrow.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.calendar.hour.downarrow.xpath")),
					"Clicking on down arrow button of hour field");
		} else {
			throw new RCException("Down arrow button of hour field not found !");
		}
	}
	
	public void clickOnUpArrowOfMinField(){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.calendar.min.uparrow.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.calendar.min.uparrow.xpath")),
					"Clicking on up arrow button of minute field");
		} else {
			throw new RCException("Up arrow button of minute field not found !");
		}
		
	}
	
	public void clickOnDownArrowOfMinField(){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.calendar.min.downarrow.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.calendar.min.downarrow.xpath")),
					"Clicking on down arrow button of minute field");
		} else {
			throw new RCException("down arrow button of minute field not found !");
		}
		
	}
	
	public void clickSaveParmetersButton() {
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.saveparameters.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.saveparameters.xpath")),
					"Clicking on save parameters button");
		} else {
			throw new RCException("save parameters button not found !");
		}
	}

	public void clickEditAbParmetersButton() {
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.editabparameters.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.editabparameters.xpath")),
					"Clicking on Edit A/B parameters button");
		} else {
			throw new RCException("Edit A/B parameter button not found !");
		}
	}
	
	public void clickCancelButtonInKycGoLiveParameters(){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.cancel.kycgolive.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.cancel.kycgolive.xpath")),
					"Clicking on Cancel button in Kyc go live parameters");
		} else {
			throw new RCException("Cancel button in KYC go live parameters is not found !");
		}
	}
	
	public boolean isSaveParameterButtonPresent(){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.saveparameters.xpath")))) {
			return true;
			} else {
				return false;
			}
		
	}

	
	public void clickSetButton(){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.set.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.set.xpath")),
					"Clicking on set button");
		} else {
			throw new RCException("set button not found !");
		}
	}
	
	public void setModPaths(String modValue){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("input.paths.xpath")))) {
			cmd.sendKeys(By.xpath(locators.getProperty("input.paths.xpath")), modValue, "Typing mod values");
					
		} else {
			throw new RCException("mod input field not found !");
		}
	}
	
	public void fillABPaths(String path, String value){
		String modXpath=locators.getProperty("input.mod.value.xpath").replace("%s",path);
		log.info("Dynamically rendered xpath is " + modXpath);
		if (cmd.isElementPresent(By.xpath(modXpath))) {
			cmd.sendKeys(By.xpath(modXpath), value, "Typing mod values");
					
		} else {
			throw new RCException(String.format("Mod path %s not found !", path));
		}
	}
	
	public void clickSaveABButton(){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.saveabparameters.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.saveabparameters.xpath")),
					"Clicking on Save A/B parameters button");
		} else {
			throw new RCException("Save A/B parameter button not found !");
		}
	}
	
	public void clickCancelButtonInABTestRequirements(){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.cancel.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.cancel.xpath")),
					"Clicking on Save A/B parameters button");
		} else {
			throw new RCException("Save A/B parameter button not found !");
		}
	}
	
	public boolean isDetailsSavedSuccessfully(){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("toast.save.xpath")))) {
		return true;
		} else {
			return false;
		}
	}
	
	public boolean isEditButtonPresent(){
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.editabparameters.xpath")))) {
			return true;
			} else {
				return false;
			}
	}
}
