package com.rummycircle.pageobjects.login;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.mortbay.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.utils.exceptions.RCException;
import com.rummycircle.utils.testutils.PropertyReader;

public class LoginPage extends AbstractBasePage {

	protected Properties locators = null;
	protected Properties messages = null;
	
	protected Logger log = null;

	public LoginPage(WebDriver driver) {
		super(driver);
		locators = loadLocators("landing.properties");
		log = Logger.getLogger(LoginPage.class);
		messages = loadLocators("en_us.properties");

	}

	public void enterUsername(String username) {

		log.info(String.format("Entering the username %s", username));
		if (cmd.isElementPresent(By.xpath(locators.getProperty("input.username.id"))/*By.xpath(locators.getProperty("label.username.xpath"))*/)){
			new Actions(driver)
					.moveToElement(driver.findElement(By.xpath(locators.getProperty("input.username.id"))/*By.xpath(locators.getProperty("label.username.xpath"))*/)).click()
					.sendKeys(username).perform();
		}else{
			throw new RCException("Element not found");
		}

	}

	public void enterPassword(String password) {
		log.info(String.format("Entering the password %s", password));
		if (cmd.isElementPresent(By.xpath(locators.getProperty("input.password.id"))/*By.xpath(locators.getProperty("label.password.xpath"))*/)){
			new Actions(driver)
					.moveToElement(driver.findElement(By.xpath(locators.getProperty("input.password.id"))/*By.xpath(locators.getProperty("label.password.xpath"))*/)).click()
					.sendKeys(password).perform();
		}else{
			throw new RCException("Element not found");
		}
	}

	public void clickLoginButton() {
		if (cmd.isElementPresent(By.xpath(locators.getProperty("button.login.xpath")))) {
			cmd.click(By.xpath(locators.getProperty("button.login.xpath")), "Clicking on login button");
			cmd.sleep(3000);
		}else{
			throw new RCException("Element not found");
		}
	}

	public Boolean isSuccessfulLogin() {
		if (cmd.isElementPresent(By.xpath(locators.getProperty("h1.Adminportal.xpath"))) && cmd.isElementPresent(By.xpath(locators.getProperty("h1.Webadmin.xpath"))) && cmd.isElementPresent(By.xpath(locators.getProperty("h1.withdrawalconfiguration.xpath")))) {
			return true;
		}
		return false;
	}

	public Boolean isInvalidLogin() {
		if (cmd.isElementPresent(By.xpath(locators.getProperty("toast.invalid.msg.xpath")))
				|| cmd.isElementPresent(By.xpath(locators.getProperty("div.heading.xpath")))) {
			return true;
		}
		return false;
	}
	
	public Boolean isPasswordEmpty(){
		if(cmd.isElementPresent(By.xpath(locators.getProperty("toast.invalid.msg.xpath"))))
			return true;
		else
			return false;
	}
	
	public Boolean isUsernameEmpty(){
		if(cmd.isElementPresent(By.xpath(locators.getProperty("toast.invalid.msg.xpath"))))
			return true;
		else
			return false;
	}
	
	public boolean errorMessage()
	{
		String message = cmd.findElement(By.xpath(locators.getProperty("toast.invalid.msg.xpath"))).getText().trim();
		return message.equals(messages.getProperty("iw.loginpage.empty"));
	}
	
	public void clickOnWithdrawService()
	{
		Log.info("Clicking on withdraw service");
		cmd.click(By.xpath(locators.getProperty("Withdraw.service.click.xpath")), "Clicking on withdraw service");
	}

	public void  clickOnAdminPortal()
	{
		Log.info("Clicking on Admin portal");
		cmd.click(By.xpath(locators.getProperty("AdminPortal.click.xpath")), "Click on admin portal");
	}
	
	public void clickOnWebAdmin()
	{
		Log.info("Clicking on web admin");
		cmd.click(By.xpath(locators.getProperty("Webadmin.click.xpath")), "Click on web admin");
	}
}
