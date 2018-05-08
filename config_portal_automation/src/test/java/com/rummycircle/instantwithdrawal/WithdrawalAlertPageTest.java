package com.rummycircle.instantwithdrawal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rummycircle.pageobjects.login.LoginPage;
import com.rummycircle.utils.testutils.BaseTest;
import com.rummycircle.utils.testutils.PropertyReader;

public class WithdrawalAlertPageTest extends BaseTest {
	protected Properties config = null;
	private static Logger Log = Logger.getLogger(WithdrawalAlertPageTest.class);
	Properties confi = PropertyReader.loadCustomProperties("config.properties");
	Properties cuLocator = PropertyReader.loadLocatorProperties("instant_withdrawal.properties");
	Properties cuLocator1 = PropertyReader.loadLocatorProperties("en_us.properties");
	
	@BeforeMethod
	public void LoginPortal() throws InterruptedException {
		openBaseURL();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername(confi.getProperty("config.portal.username"));
		loginPage.enterPassword(confi.getProperty("config.portal.password"));
		loginPage.clickLoginButton();
		loginPage.clickOnWithdrawService();
		InstantWithdrawalPage iw = new InstantWithdrawalPage(driver);
		iw.clickWithdrawalRequest();
		Thread.sleep(10000);
	}
	
	/**
	 * Test to reject on first time alerts without selecting any record
	 */

	@Test
	public void testRejectOnFirstTimeAlerts() throws InterruptedException {
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		// Code to click Reject and assert the message
		wd.clickRejectButton();
		wd.rejectErrorMessage();
		Assert.assertEquals(wd.rejectErrorMessage(), cuLocator1.getProperty("iw.reject.errormessage"));
	}
	
	/**
	 * Test to check date error message
	 * @throws InterruptedException 
	 */

	@Test
	public void toDateError() throws InterruptedException {
		Log.info("Error Message when to date is empty");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		wd.clickFromButton();
		wd.clickDate();
		wd.clickToButton();
		wd.clickShowResult();
		Assert.assertTrue(wd.toDateError());
	}

	/**
	 * Test to check for date message
	 * @throws InterruptedException 
	 */
	@Test
	public void fromDateError() throws InterruptedException {
		Log.info("Error message when from date is empty");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		wd.clickToButton();
		wd.clickDate();
		wd.clickShowResult();
		Assert.assertTrue(wd.fromDateError());
	}
	
	/**
	 * Test to check filters on first time alerts
	 */

	@Test(dataProvider = "filters")
	public void Testfilter(String filter) throws InterruptedException {
		Log.info(String.format("%s", "filter"));
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		if (wd.noDataDisplay()) {
			throw new SkipException("No data to display");
		} else {
			switch (filter) {
			case "Bronze":
				wd.clickBronzeFilter();
				break;
			case "Silver":
				wd.clickSilverFilter();
				break;
			case "Gold":
				wd.clickGoldFilter();
				break; 
			case "Diamond":
				wd.clickDiamondFilter();
				break;
			case "Platinum":
				wd.clickPlatinumFilter();
				break;
			case "Platinum Elite":
			wd.clickPlatinumEliteFilter();
			break;
			}
			wd.clickShowResult();
			wd.toWait();
			if (wd.noDataDisplay()) {
				Assert.assertTrue(wd.noDataToDisplay());
			} else {
				wd.getclubs();
				int count = wd.getclubs().size();
				switch (filter) {
				case "Bronze":
					int count1 = wd.bronzeText().size();
					Assert.assertEquals(count1, count);
					break;
				case "Silver":
					int count2 = wd.SilverText().size();
					Assert.assertEquals(count2, count);
					break;
				case "Gold":
					int count3 = wd.GoldText().size();
					Assert.assertEquals(count3, count);
					break;
				case "Diamond":
					int count4 = wd.DiamondText().size();
					Assert.assertEquals(count4, count);
					break;
				case "Platinum":
					int count5 = wd.PlatinumText().size();
					Assert.assertEquals(count5, count);
					break;
				case "Platinum Elite":
					int count6 = wd.PlatinumEliteText().size();
					Assert.assertEquals(count6, count);
					break;
				}
			}
		}
	}
	
	/**
	 * Test to check error message on comment box , when clicked yes without writing anything in the box
	 */
	@Test
	public void testCommentErrorMessage() throws InterruptedException
	{
		Log.info("Test Comment error message");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		wd.clickOnRecord();
		wd.clickRejectButton();
		wd.clickYes();
		Assert.assertTrue(wd.popUpErrorMessage());
	}
	
	/**
	 * Test to check reject button on first time alerts
	 */
	@Test
	public void testRejectButton() throws InterruptedException
	{
		Log.info("Test Reject button");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		String c=wd.getWithid1stRecord();
		wd.clickOnRecord();
		wd.clickRejectButton();
		wd.clickCommentBox("asdad");
		wd.clickYes();
		wd.clickOnRefreshButton();
		wd.toWait();
		String d=wd.getWithid1stRecord();
		Assert.assertNotEquals(c, d);
	}
	
	/**
	 * Test to check download ENET
	 */
	
	@Test
	public void testENET() throws InterruptedException
	{
		Log.info("Test ENET button");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		String c=wd.getWithid1stRecord();
		wd.clickOnRecord();
		wd.clickENET();
		wd.toWait();
		wd.clickOnRefreshButton();
		String a=wd.getWithid1stRecord();
		Assert.assertNotEquals(c, a);
		wd.toWait();
		wd.clickUnderProcessing();
		String d=wd.getWithid1stRecord();
		Assert.assertEquals(c, d);
	}
	
	/**
	 * Test to check download ECS
	 * @throws InterruptedException 
	 */
	@Test
	public void testECS() throws InterruptedException
	{
		Log.info("Test ECS button");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		String c=wd.getWithid1stRecord();
		wd.clickOnRecord();
		wd.clickECS();
		wd.toWait();
		wd.clickOnRefreshButton();
		String a=wd.getWithid1stRecord();
		Assert.assertNotEquals(c, a);
		wd.toWait();
		wd.clickUnderProcessing();
		String d=wd.getWithid1stRecord();
		Assert.assertEquals(c, d);
	}

	/**
	 * Test retry button on underprocessing
	 */
	
	@Test
	public void retryButtonUnderProcessing() throws InterruptedException
	{
		Log.info("Retry button under processing");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		wd.clickUnderProcessing();
		String c=wd.getWithid1stRecord();
		wd.clickOnRecord();
		wd.clickRetryOnUnderProcess();
		wd.clickCommentBox("adasdad");
		wd.clickYes();
		wd.toWait();
		wd.clickRepeatTab();
		String d=wd.getWithid1stRecord();
		Assert.assertEquals(c, d);
	}
	
	/**
	 * Test Reset button on First time alerts
	 */
	
	@Test
	public void testResetButton()
	{
		Log.info("Reset button");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		wd.clickOnRecord();
		wd.clickResetButton();
		Assert.assertFalse(wd.resetButtonWorking());
	}
	
	/**
	 * Test Reject on under processing
	 * @throws InterruptedException 
	 */
	
	@Test
	public void testRejectUP() throws InterruptedException
	{
		Log.info("Test Reject button in under processing tab");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		wd.clickUnderProcessing();
		String c=wd.getWithid1stRecord();
		wd.clickOnRecord();
		wd.clickRejectOnUnderProcess();
		wd.clickCommentBox("adsadas");
		wd.clickYes();
		wd.RejectedMessage("Rejected");
		wd.clickOnRefreshButton();
		String d=wd.getWithid1stRecord();
		Assert.assertNotEquals(c, d);
	}
	
	/**
	 * Test processed button in under processing
	 * @throws InterruptedException 
	 */
	
	@Test
	public void testProcessedButtonUP() throws InterruptedException
	{
		Log.info("Test processed button in under processing tab");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		wd.clickUnderProcessing();
		String c=wd.getWithid1stRecord();
		wd.clickOnRecord();
		wd.clickProcessedOnUnderProcess();
		wd.clickCommentBox("adsadas");
		wd.clickYes();
		wd.RejectedMessage("successfully");
		wd.toWait();
		wd.clickOnRefreshButton();
		String a=wd.getWithid1stRecord();
		Assert.assertNotEquals(c, a);
		wd.toWait();;
		String d=wd.getWithid1stRecord();
		Assert.assertNotEquals(c, d);
	}
	
	/**
	 * To test repeat alerts download ecs
	 * @throws InterruptedException 
	 */
	
	@Test
	public void testRepeatAlertsECS() throws InterruptedException
	{
		Log.info("Test ECS button on repeat alerts");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		wd.clickRepeatTab();
		String c=wd.getWithid1stRecord();
		wd.clickOnRecord();
		wd.clickECS();
		wd.toWait();;
		wd.clickOnRefreshButton();
		String a=wd.getWithid1stRecord();
		Assert.assertNotEquals(c, a);
		wd.toWait();
		wd.clickUnderProcessing();
		String d=wd.getWithid1stRecord();
		Assert.assertEquals(c, d);
	}
	
	/**
	 * To test repeat alerts download enet
	 * @throws InterruptedException 
	 */
	@Test
	public void testRepeatalertsENET() throws InterruptedException
	{
		Log.info("Test ENet button on repeat alerts");
		WithdrawalAlertPage wd = new WithdrawalAlertPage(driver);
		wd.clickRepeatTab();
		String c=wd.getWithid1stRecord();
		wd.clickOnRecord();
		wd.clickENET();
		wd.toWait();
		wd.clickOnRefreshButton();
		String a=wd.getWithid1stRecord();
		Assert.assertNotEquals(c, a);
		wd.toWait();
		wd.clickUnderProcessing();
		String d=wd.getWithid1stRecord();
		Assert.assertEquals(c, d);
	}
	@DataProvider(name="filters")
	public Iterator<Object[]> filters() {
		List<Object[]> list = new ArrayList<>();
		list.add(new Object[] { "Bronze" });
		/*list.add(new Object[] { "Silver" });
		list.add(new Object[] { "Gold" });
		list.add(new Object[] { "Diamond" });
		list.add(new Object[] { "Platinum" });
		list.add(new Object[] { "Platinum Elite" });*/
		return list.iterator();
	}

	@DataProvider(name="getData")
	public Object[][] getData() {
		WithdrawalAlertPage wap = new WithdrawalAlertPage(driver);
		List<String> wIds = wap.getWithdrawIds();
		Object[][] data = new Object[2][2];
		if (wIds.size() > 0) {
			data[0][0] = "Single";
			data[0][1] = wap.getWithdrawIds().get(0);
		}
		data[1][0] = "None";
		data[1][1] = "";

		return data;
	}
}