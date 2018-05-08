package com.rummycircle.instantwithdrawal;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mortbay.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.utils.testutils.PropertyReader;

public class WithdrawalAlertPage extends AbstractBasePage {

	protected Properties locators = null;
	protected Properties messages = null;

	public WithdrawalAlertPage(WebDriver driver) {
		super(driver);
		locators = loadLocators("instant_withdrawal.properties");
		messages = loadLocators("en_us.properties");
	}

	/**
	 * Clicking on Tester check box
	 */

	public void clickTesterFilter() {
		Log.info("Clicking on tester checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.tester.xpath")),
				"Clicking on tester checkbox");

	}

	/**
	 * Clicking on Non Tester checkbox
	 */

	public void clickNonTesterFilter() {
		Log.info("Clicking on non tester checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.nontester.xpath")),
				"Clicking on non tester checkbox");
	}

	/**
	 * Clicking on KYC Verified checkbox
	 */

	public void clickKYCVerifiedFilter() {
		Log.info("Clicking on KYC Verified checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.kycverified.xpath")),
				"Clicking on  KYC Verified checkbox");
	}

	/**
	 * Clicking on KYC non Verified checkbox
	 */

	public void clickNonKYCVerifiedFilter() {
		Log.info("Clicking on Non KYC Verified  checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.kycnonverified.xpath")),
				"Clicking on Non KYC Verified checkbox");
	}

	/**
	 * Clicking on both tester and non tester checkbox
	 */

	public void clickBothTesterAndNonTesterFilter() {
		Log.info("Clicking on Both Tester and Non Tester checkbox");
		clickTesterFilter();
		clickNonTesterFilter();
	}

	/**
	 * Clicking on both KYC verified and Non KYC verified checkbox
	 */

	public void clickBothKYCAndNonKYCVerifiedFilter() {
		Log.info("Clicking on both KYC and Non KYC Verified Checkbox");
		clickKYCVerifiedFilter();
		clickNonKYCVerifiedFilter();
	}

	/*
	 * Clicking on Bronze checkbox
	 */

	public void clickBronzeFilter() {
		Log.info("Clicking On Bronze Checkbox");
		cmd.click(By.id(locators.getProperty("configportal.instantwithdrawal.bronze.xpath")),
				"Clicking On Bronze checkbox");
	}

	/**
	 * Clicking on Silver checkbox
	 */

	public void clickSilverFilter() {
		Log.info("Clicking On Silver checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.silver.xpath")),
				"Clicking On Silver checkbox");
	}

	/**
	 * Clicking On Gold checkbox
	 */

	public void clickGoldFilter() {
		Log.info("Clicking On Gold checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.gold.xpath")),
				"Clicking On Gold checkbox");
	}

	/**
	 * Clicking On Diamond checkbox
	 */

	public void clickDiamondFilter() {
		Log.info("Clicking On Diamond checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.diamond.xpath")),
				"Clicking On Diamond checkbox");
	}

	/**
	 * Clicking On Platinum checkbox
	 */

	public void clickPlatinumFilter() {
		Log.info("Clicking On Platinum checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.platinum.xpath")),
				"Clicking On Platinum checkbox");
	}

	/**
	 * Clicking On Platinum Elite checkbox
	 */

	public void clickPlatinumEliteFilter() {
		Log.info("Clicking On Platinum Elite checkbox");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.platinumelite.xpath")),
				"Clicking On Platinum Elite checkbox");
	}

	/**
	 * Clicking On All clubs
	 */

	public void clickAllClubs() {
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
	 * 
	 * @throws InterruptedException
	 */

	public void clickRejectButton() throws InterruptedException {
		Log.info("Clicking On Reject button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.reject.button.xpath")),
				"Clicking On Reject button");
	}

	/**
	 * Clicking On Process Via IMPS
	 */

	public void clickProcessViaIMPS() {
		Log.info("Clicking On Process Via IMPS");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.processIMPS.button.xpath")),
				"Clicking On Process Via IMPS");
	}

	/**
	 * Clicking On Reset button
	 */

	public void clickResetButton() {
		Log.info("Clicking On Reset button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.reset.button.xpath")),
				"Clicking On Reset button");
	}

	/**
	 * Clicking on from tab
	 */

	public void clickFromButton() {
		Log.info("Clicking On From Button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.from.tab.xpath")),
				"Clicking on from button");
	}

	/**
	 * Clicking on date
	 */

	public void clickDate() {
		Log.info("Clicking on Date");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.date.xpath")), "Clicking on date");
	}

	/**
	 * Clicking on to button
	 */

	public void clickToButton() {
		Log.info("Clicking On To button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.to.tab.xpath")),
				"Clicking On To button");
	}

	/**
	 * Clicking on check mark of record
	 */

	public void clickOnRecord() {
		Log.info("Clicking On checkmark of record");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.checkmark.xpath")),
				"Clicking on checkmark of record");
	}

	/**
	 * Clicking on Reject button
	 */

	public void clickRejectOnUnderProcess() {
		Log.info("Clicking On reject button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.reject.button.xpath")),
				"Clicking On Reject button");
	}

	/**
	 * Clicking on Retry button
	 */

	public void clickRetryOnUnderProcess() {
		Log.info("Clicking on retry button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.retry.button.underprocessing.xpath")),
				"Clicking on retry button");
	}

	/**
	 * Clicking on processed button
	 */

	public void clickProcessedOnUnderProcess() {
		Log.info("Clicking On processed button");
		cmd.click(
				By.xpath(locators.getProperty("configportal.instantwithdrawal.processed.button.underprocessing.xpath")),
				"Clicking on processed button");
	}

	/**
	 * Clicking on reset button
	 */

	public void clickResetOnUnderProcess() {
		Log.info("Clicking On reset button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.reset.button.underprocessing.xpath")),
				"Clicking On reset button");
	}

	/**
	 * Clicking on process id
	 */

	public void clickProcessIDOnUnderProcess() {
		Log.info("Clicking on process id");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.processid.underprocessing.xpath")),
				"Clicking On process id");
	}

	/*
	 * To get all the withdrawal id's
	 */

	public List<String> getWithdrawIds() {
		List<WebElement> withEle = cmd.findElements(By.xpath(locators.getProperty("iw.td.withIds.xpath")),
				"Getting all withdraw ids");
		List<String> withdrawIds = new ArrayList<String>();
		for (WebElement ele : withEle) {
			withdrawIds.add(ele.getText());
		}
		return withdrawIds;
	}

	/**
	 * To return error message when click on reject button
	 */
	public String rejectErrorMessage() {
		Log.info("Clicking on reject button before clicking on any record");
		String message = cmd.findElement(By.xpath(locators.getProperty("iw.reject.errormessage.xpath"))).getText();
		return message;
	}

	/**
	 * To click on comment box and to send keys
	 */
	public void clickCommentBox(String comment) {
		Log.info("Clicking on comment box");
		cmd.sendKeys(By.xpath(locators.getProperty("iw.reject.comment.xpath")), comment, "CLicking on comment box");
	}

	/**
	 * To click on yes button
	 * @throws InterruptedException 
	 */
	public void clickYes() throws InterruptedException {
		Log.info("Clicking on yes button on comment pop up");
		cmd.click(By.xpath(locators.getProperty("iw.yes.button.xpath")), "Clicking on yes button");
	}

	/**
	 * To click on No button
	 */
	public void clickNo() {
		Log.info("Clicking On no  button on comment pop up");
		cmd.click(By.xpath(locators.getProperty("iw.no.button.xpath")), "Clicking on no button");
	}

	/**
	 * To Check reject message
	 */
	public void RejectedMessage(String message) {
		Log.info("Clicking on alert tab");
		cmd.findElement(By.xpath(String.format(locators.getProperty("iw.succes.message.xpath"), message)));
		cmd.sleep(LONG_WAIT);
	}

	/**
	 * To check for date error message
	 */

	public boolean toDateError() {
		String message = cmd.findElement(By.xpath(locators.getProperty("iw.todate.error.xpath"))).getText().trim();
		return message.equals(messages.getProperty("iw.toDate.errormessage"));
	}

	/**
	 * To check for date error message
	 */
	public boolean fromDateError() {
		String message = cmd.findElement(By.xpath(locators.getProperty("iw.todate.error.xpath"))).getText().trim();
		return message.equals(messages.getProperty("iw.fromDate.errormessage"));
	}

	/**
	 * To click on show result
	 * @throws InterruptedException 
	 */

	public void clickShowResult() throws InterruptedException {
		Log.info("Clicking on show result");
		cmd.click(By.xpath(locators.getProperty("iw.showresult.xpath")), "Clicking on show result button");
	}

	/**
	 * To get the club
	 */

	public List<String> getclubs() {
		List<WebElement> club = cmd.findElements(By.xpath(locators.getProperty("iw.td.club.xpath")),
				"Getting all clubs");
		List<String> clubs = new ArrayList<String>();
		for (WebElement ele : club) {
			clubs.add(ele.getText());
		}
		return clubs;
	}

	/**
	 * To check for bronze text
	 */
	public List<String> bronzeText()

	{
		List<WebElement> text = cmd.findElements(By.xpath(locators.getProperty("iw.bronzetext.xpath")));
		List<String> bronze = new ArrayList<String>();
		for (WebElement ele : text) {
			bronze.add(ele.getText());
		}
		return bronze;
	}

	/**
	 * To check for silver text
	 */
	public List<String> SilverText()

	{
		List<WebElement> text = cmd
				.findElements(By.xpath(locators.getProperty("iw.silvertext.xpath")));
		List<String> silver = new ArrayList<String>();
		for (WebElement ele : text) {
			silver.add(ele.getText());
		}
		return silver;
	}

	/**
	 * To check for gold text
	 */

	public List<String> GoldText()

	{
		List<WebElement> text = cmd
				.findElements(By.xpath(locators.getProperty("iw.goldtext.xpath")));
		List<String> gold = new ArrayList<String>();
		for (WebElement ele : text) {
			gold.add(ele.getText());
		}
		return gold;
	}

	/**
	 * To check for diamond text
	 */
	public List<String> DiamondText()

	{
		List<WebElement> text = cmd
				.findElements(By.xpath(locators.getProperty("iw.diamondtext.xpath")));
		List<String> diamond = new ArrayList<String>();
		for (WebElement ele : text) {
			diamond.add(ele.getText());
		}
		return diamond;
	}

	/**
	 * To check for platinum text
	 */
	public List<String> PlatinumText()

	{
		List<WebElement> text = cmd
				.findElements(By.xpath(locators.getProperty("iw.platinumtext.xpath")));
		List<String> platinum = new ArrayList<String>();
		for (WebElement ele : text) {
			platinum.add(ele.getText());
		}
		return platinum;
	}

	/**
	 * To check for platinum elite text
	 */
	public List<String> PlatinumEliteText()

	{
		List<WebElement> text = cmd
				.findElements(By.xpath(locators.getProperty("iw.platinumelitetext.xpath")));
		List<String> elite = new ArrayList<String>();
		for (WebElement ele : text) {
			elite.add(ele.getText());
		}
		return elite;
	}

	/**
	 * Return pop up comment box error message
	 */
	public boolean popUpErrorMessage() {
		String message = cmd.findElement(By.xpath(locators.getProperty("iw.errormessageoncommentbox.xpath"))).getText()
				.trim();
		return message.equals(messages.getProperty("iw.commentboxerror"));
	}

	public boolean noDataDisplay() {
		Log.info("Check for no data");
		return cmd.isElementPresentQuickCheck(By.xpath(locators.getProperty("iw.nodata.xpath")));
	}

	/**
	 * To get first record withdraw id
	 */
	public String getWithid1stRecord() {
		String id = cmd.findElement(By.xpath(locators.getProperty("iw.1stWithID.xpath")))
				.getText();
		return id;
	}

	/**
	 * Click on refresh button
	 */
	public void clickOnRefreshButton() {
		Log.info("Clicking on Refresh button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.refresh.button.xpath")),"Click on refresh button");
	}

	/**
	 * To check for the message when no data is present
	 */
	public boolean noDataToDisplay() {
		Log.info("No data to display");
		String message = cmd.findElement(By.xpath(locators.getProperty("iw.nodata.xpath"))).getText().trim();
		return message.equals(messages.getProperty("iw.nodatamessage"));
	}
	
	/**
	 * To click on download ENET
	 * @throws InterruptedException 
	 */
	public void clickENET() throws InterruptedException
	{
		Log.info("Clicking on ENET button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.download.button.xpath")), "Click on Download button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.download.ENET.xpath")), "Clicking on ENET button");
	}
	
	/**
	 * Click on under processing tab
	 * @throws InterruptedException 
	 */
	
	public void clickUnderProcessing() throws InterruptedException
	{
		Log.info("Clicking on under processing");
		cmd.click(By.xpath(locators.getProperty("iw.underprocessing.xpath")), "Clicking on under processing tab");
	}
	
	/**
	 * Click on ECS
	 * @throws InterruptedException 
	 */
	
	public void clickECS() throws InterruptedException
	{
		Log.info("Click on ECS button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.download.button.xpath")), "Click on Download button");
		cmd.click(By.xpath(locators.getProperty("configportal.instantwithdrawal.download.ECS.xpath")), "Click on ECS ");
	}
	
	
	/**
	 * Click on repeat alerts tab
	 */
	
	public void clickRepeatTab()
	{
		Log.info("Click on repeat tab");
		cmd.click(By.id(locators.getProperty("iw.repeatalerts.id")), "Click repeat tab");
	}
	
	/**
	 * To check whether reset button is clicking or not
	 */
	
	public boolean resetButtonWorking()
	{
		Log.info("Check Reset button click");
		return cmd.isSelected(By.xpath(locators.getProperty("configportal.instantwithdrawal.reset.button.xpath")), "Checking for Reset button click");
	}
	
	/**
	 * To wait
	 */
	
	public void toWait()
	{
		cmd.sleep(MEDIUM_WAIT);
	}
}
