package com.rummycircle.instantwithdrawal;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import com.rummycircle.login.LoginPageTest;
import com.rummycircle.pageobjects.login.LoginPage;
import com.rummycircle.utils.testutils.BaseTest;
import com.rummycircle.utils.testutils.PropertyReader;

public class WithdrawalAlertPageTest extends BaseTest {
	protected Properties config = null;

	private static Logger Log = Logger.getLogger(WithdrawalAlertPageTest.class);
	Properties confi = PropertyReader.loadCustomProperties("config.properties");

@Test
public void LoginPortal() throws InterruptedException
{
	 openBaseURL();
	 LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername(confi.getProperty("config.portal.username"));
		loginPage.enterPassword(confi.getProperty("config.portal.password"));
		loginPage.clickLoginButton();
	 driver.get("https://stage2-config.rummycircle.com/admin/withdrawals/");
	 Thread.sleep(10000);
}
}

