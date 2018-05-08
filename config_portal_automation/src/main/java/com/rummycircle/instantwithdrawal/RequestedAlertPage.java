package com.rummycircle.instantwithdrawal;

import java.util.Properties;

import org.mortbay.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rummycircle.AbstractBasePage;

public class RequestedAlertPage extends AbstractBasePage {
	protected Properties locators = null;
	public RequestedAlertPage(WebDriver driver) {
		super(driver);
		locators = loadLocators("instant_withdrawal.properties");
	}

	/**
	 * Clicking on show result  button
	 */
	
	public void clickingOnShowResultButton()
	{
		Log.info("Clicking On Show Result Button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.showresult.button.xpath")),"Clicking On show Result button");
	}
	
	/**
	 * Clicking on re-initiate button
	 */
	
	public void clickingOnReinitiateButton()
	{
		Log.info("Clicking on re-initiate button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.actions.requestedalerts.xpath")), "Clicking on re-initiate button");
	}
}
