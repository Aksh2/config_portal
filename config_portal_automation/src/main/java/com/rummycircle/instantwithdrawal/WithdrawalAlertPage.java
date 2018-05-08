package com.rummycircle.instantwithdrawal;

import java.util.Properties;

import org.mortbay.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rummycircle.AbstractBasePage;

public class WithdrawalAlertPage extends AbstractBasePage {

	protected Properties locators = null;

	public WithdrawalAlertPage(WebDriver driver) {
		super(driver);
		locators = loadLocators("instant_withdrawal.properties");
	}

	/**
	 * Clicking on Tester checkbox
	 */
	
	public void clickTesterFilter()
	{
		Log.info("Clicking on tester checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.tester.xpath")), "Clicking on tester checkbox");
		
	}
	
	/**
	 * Clicking on Non Tester checkbox
	 */
	
	public void clickNonTesterFilter()
	{
		Log.info("Clicking on non tester checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.nontester.xpath")), "Clicking on non tester checkbox");
	}
	
	/**
	 * Clicking on KYC Verified checkbox
	 */
	
	public void clickKYCVerifiedFilter()
	{
		Log.info("Clicking on KYC Verified checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.kycverified.xpath")), "Clicking on  KYC Verified checkbox");
	}
	
	/**
	 * Clicking on KYC non Verified checkbox
	 */
	
	public void clickNonKYCVerifiedFilter()
	{
		Log.info("Clicking on Non KYC Verified  checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.kycnonverified.xpath")), "Clicking on Non KYC Verified checkbox");
	}
	
	/**
	 * Clicking on both tester and non tester checkbox
	 */
	
	public void clickBothTesterAndNonTesterFilter()
	{
		Log.info("Clicking on Both Tester and Non Tester checkbox");
		clickTesterFilter();
		clickNonTesterFilter();
	}
	
	/**
	 * Clicking on both KYC verified and Non KYC verified checkbox
	 */
	
	public void clickBothKYCAndNonKYCVerifiedFilter()
	{
		Log.info("Clicking on both KYC and Non KYC Verified Checkbox");
		clickKYCVerifiedFilter();
		clickNonKYCVerifiedFilter();
	}

	/*
	 * Clicking on Bronze checkbox
	 */
	
	public void clickBronzeFilter()
	{
		Log.info("Clicking On Bronze Checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.bronze.xpath")), "Clicking On Bronze checkbox");
	}
	
	/**
	 * Clicking on Silver checkbox
	 */
	
	public void clickSilverFilter()
	{
		Log.info("Clicking On Silver checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.silver.xpath")), "Clicking On Silver checkbox");
	}
	
	/**
	 * Clicking On Gold checkbox
	 */
	
	public void clickGoldFilter()
	{
		Log.info("Clicking On Gold checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.gold.xpath")), "Clicking On Gold checkbox");
	}
	
	/**
	 * Clicking On Diamond checkbox
	 */
	
	public void clickDiamondFilter()
	{
		Log.info("Clicking On Diamond checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.diamond.xpath")), "Clicking On Diamond checkbox");
	}
	
	/**
	 * Clicking On Platinum checkbox
	 */
	
	public void clickPlatinumFilter()
	{
		Log.info("Clicking On Platinum checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.platinum.xpath")), "Clicking On Platinum checkbox");
	}
	
	/**
	 * Clicking On Platinum Elite checkbox
	 */
	
	public void clickPlatinumEliteFilter()
	{
		Log.info("Clicking On Platinum Elite checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.platinumelite.xpath")), "Clicking On Platinum Elite checkbox");
	}
	
	/**
	 * Clicking On All clubs
	 */
	
	public void clickAllClubs()
	{
		Log.info("Clicking On All Clubs checkbox");
		clickBronzeFilter();
		clickSilverFilter();
		clickGoldFilter();
		clickDiamondFilter();
		clickPlatinumFilter();
		clickPlatinumEliteFilter();
	}
	
	/**
	 * Clicking On Reject Button
	 */
	
	public void clickRejectButton()
	{
		Log.info("Clicking On Reject button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.reject.button.xpath")), "Clicking On Reject button");
	}
	
	/**
	 * Clicking On Process Via IMPS
	 */
	
	public void clickProcessViaIMPS()
	{
		Log.info("Clicking On Process Via IMPS");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.processIMPS.button.xpath")), "Clicking On Process Via IMPS");
	}
	
	/**
	 * Clicking On Reset button
	 */
	
	public void clickResetButton()
	{
		Log.info("Clicking On Reset button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.reset.button.xpath")), "Clicking On Reset button");
	}
	
	/**
	 * Clicking on from tab
	 */
	
	public void clickFromButton()
	{
		Log.info("Clicking On From Button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.from.tab.xpath")), "Clicking on from button");
	}
	
	/**
	 * Clicking on date
	 */
	
	public void clickDate()
	{
		Log.info("Clicking on Date");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.date.xpath")), "Clicking on date");
	}
	
	/**
	 * Clicking on to button
	 */
	
	public void clickToButton()
	{
		Log.info("Clicking On To button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.to.tab.xpath")), "Clicking On To button");
	}
	
	/**
	 * Clicking on check mark of record
	 */
	
	public void clickOnRecord()
	{
		Log.info("Clicking On checkmark of record");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.checkmark.xpath")), "Clicking on checkmark of record");
	}
	
	/**
	 * Clicking on Reject button
	 */
	
	public void clickRejectOnUnderProcess()
	{
		Log.info("Clicking On reject button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.reject.button.xpath")), "Clicking On Reject button");
	}
	
	/**
	 * Clicking on Retry button
	 */
	
	public void clickRetryOnUnderProcess()
	{
		Log.info("Clicking on retry button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.retry.button.underprocessing.xpath")), "Clicking on retry button");
	}
	
	/**
	 * Clicking on processed button
	 */
	
	public void clickProcessedOnUnderProcess()
	{
		Log.info("Clicking On processed button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.processed.button.underprocessing.xpath")), "Clicking on processed button");
	}
	
	/**
	 * Clicking on reset button
	 */
	
	public void clickResetOnUnderProcess()
	{
		Log.info("Clicking On reset button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.reset.button.underprocessing.xpath")), "Clicking On reset button");
	}
	
	/**
	 * Clicking on process id
	 */
	
	public void clickProcessIDOnUnderProcess()
	{
		Log.info("Clicking on process id");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.processid.underprocessing.xpath")), "Clicking On process id");
	}
}
