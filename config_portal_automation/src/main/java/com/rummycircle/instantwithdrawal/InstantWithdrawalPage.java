package com.rummycircle.instantwithdrawal;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rummycircle.AbstractBasePage;

public class InstantWithdrawalPage extends AbstractBasePage {

	protected Properties locators = null;
	private static Logger Log = null;
	protected Properties messages = null;
	
	public InstantWithdrawalPage(WebDriver driver) {
		super(driver);
		
		locators = loadLocators("instant_withdrawal.properties");
		Log = Logger.getLogger(InstantWithdrawalPage.class);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return
	 */
	public String  getInstantWithdrawalPageTitle() {
		Log.info("Getting Page title" );
		String Title= cmd.getText(By.xpath(locators.getProperty("instant_withdrawal_page_title")),"Getting Page Title");
		return Title;
		
	}
	
	public void clickShowtabname() {
		Log.info("Clicking on Menu");
		cmd.click(By.xpath(locators.getProperty("instant_withdrawal_menu_show")), "clicking to show menu");
		cmd.sleep(MEDIUM_WAIT);
		
		
	}
	
	public void clickHidetabname() {
		
		Log.info("Clicking on menu to hide ");
		cmd.click(By.xpath(locators.getProperty("instant_withdrawal_menu_hide")),"clicking to hide menu");
		cmd.sleep(MEDIUM_WAIT);
	}
	
	public void clickAlertTabs(String alertType){
		Log.info("Clicking on alert tab");
		cmd.findElement(By.xpath(String.format(locators.getProperty("select_alert_tabs"), alertType)));
		cmd.sleep(LONG_WAIT);
	}
	
	public boolean isMenuvisible() {
		boolean flag= false;
		Log.info("verifying menu options");
		flag = cmd.isDisplayed(By.xpath(locators.getProperty("instant_withdrawal_menu_show")),"");
		return flag;
	}
	public String getAlertText(String text) {
		
		Log.info("verifying menu list");
		String Alerttext= cmd.getText(By.xpath(String.format(locators.getProperty("select_alert_tabs"), text)), "getting text of alert typein menu items");
		return Alerttext;
		
	}
	public void clickonShowResult() {
		Log.info("click on show result button");
		cmd.findElement(By.xpath(locators.getProperty("instant_withdrawal_showresult")));
		cmd.sleep(MEDIUM_WAIT);
	}
	public void clickonFromDate(String fromdate) {
		
		Log.info("entering from date");
		cmd.findElement(By.xpath(String.format(locators.getProperty("instant_withdrawal_requestedalert_fromdate"), fromdate)));
		cmd.sleep(SHORT_WAIT);
	}
	public void	clickonToDate(String todate) {
		Log.info("entering to date");
		cmd.findElement(By.xpath(String.format(locators.getProperty("requestedalert_todate.xpath"), todate)));
	}
	
	public void clickWithdrawalRequest()
	{
		cmd.click(By.xpath(locators.getProperty("iw.withdrawalalerts.xpath")), "click on withdrawal alert");
	}
}
