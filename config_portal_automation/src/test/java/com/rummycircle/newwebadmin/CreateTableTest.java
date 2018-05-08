package com.rummycircle.newwebadmin;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jcraft.jsch.jce.Random;
import com.rummycircle.pageobjects.login.LoginPage;
import com.rummycircle.pageobjects.newwebadmin.CreateTableTemplatesPage;
import com.rummycircle.pageobjects.newwebadmin.TemplateConstants;
import com.rummycircle.ui.DriverManager;
import com.rummycircle.utils.testutils.NewBaseTest;
import com.rummycircle.utils.testutils.PropertyReader;

public class CreateTableTest extends NewBaseTest {

	protected Properties config = null;
	protected Logger log = null;

	public CreateTableTest() {
		config = PropertyReader.loadCustomProperties("config.properties");
	}

	@BeforeMethod
	public void initialize() {
		openBaseURL();
		LoginPage loginPage = new LoginPage(DriverManager.getWebDriver().get());
		loginPage.enterUsername(config.getProperty("config.portal.username"));
		loginPage.enterPassword(config.getProperty("config.portal.password"));
		loginPage.clickLoginButton();
	}

	@Test(enabled=false,description = "Create a given points game table of given size with the given table speed", dataProvider = "positiveValues")
	public void createCFPTable(String prizeType, String settlementType,
			String tableSpeed, String tableSize, String serviceFeeType,
			String minDecksToUse, String points, String timeInSeconds,
			String skillType) {

		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		String name = String.format("%s%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION,
				settlementType, getRandomString(2));
		tableTemplates.clickNewWebAdminTab();
		tableTemplates.clickTableTemplatesTab();
		tableTemplates.clickCreateTableTemplatesTab();
		tableTemplates.fillTableTemplateName(name);
		tableTemplates.selectPrizeType(prizeType);
		tableTemplates.selectSettlementType(settlementType);
		tableTemplates.selectTableType(TemplateConstants.TABLE_AUTOMATIC);
		tableTemplates.selectTableSpeed(tableSpeed);
		tableTemplates.selectTableSize(tableSize);
		tableTemplates.fillServiceFee(String.valueOf(generateRandomNumber(1)));
		tableTemplates.selectServiceFeeType(serviceFeeType);
		tableTemplates.fillPointValue(points);
		tableTemplates.fillTimeBetweenDeals(timeInSeconds);
		tableTemplates.selectMinimumDeckType(minDecksToUse);
		tableTemplates.selectSkillType(skillType);
		tableTemplates.clickCreateButton();
		tableTemplates.clickConfirmButton();
		//TODO: Do the assertion here for the successful message
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO: add skill base option.

	}

	@DataProvider(name = "positiveValues")
	public Object[][] getValues() {
		return new Object[][] { { TemplateConstants.REAL_TYPE,
				TemplateConstants.CFP_SETTLEMENT_TYPE,
				TemplateConstants.TABLE_SPEED_10,
				TemplateConstants.TABLE_SIZE_2,
				TemplateConstants.SERVICEFEE_TYPE_PERCENTAGE,
				TemplateConstants.MIN_DECK_2,
				String.valueOf(generateRandomNumber(2)),
				String.valueOf(generateRandomNumber(1)),
				TemplateConstants.SKILL_BASED_FALSE } };
	}
	
	@Test(description = "Create a given pool game table of given size with the given table speed", dataProvider = "poolValues",
			enabled=false)
	public void createPoolRummyTable(String prizeType, String settlementType,
			String tableSpeed, String tableSize, String serviceFeeType,
			String minDecksToUse, String poolOption, String timeInSeconds, String autoSplitOption,
			String rejoinOption, String splitOption) {

		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		String name = String.format("%s%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION,
				settlementType, getRandomString(2));
		tableTemplates.clickNewWebAdminTab();
		tableTemplates.clickTableTemplatesTab();
		tableTemplates.clickCreateTableTemplatesTab();
		tableTemplates.fillTableTemplateName(name);
		tableTemplates.selectPrizeType(prizeType);
		tableTemplates.selectSettlementType(settlementType);
		tableTemplates.selectPoolType(poolOption);
		tableTemplates.selectTableType(TemplateConstants.TABLE_AUTOMATIC);
		tableTemplates.selectTableSpeed(tableSpeed);
		tableTemplates.selectTableSize(tableSize);
		tableTemplates.fillServiceFee(String.valueOf(generateRandomNumber(1)));
		tableTemplates.selectServiceFeeType(serviceFeeType);
		tableTemplates.fillTimeBetweenDeals(timeInSeconds);
		tableTemplates.fillEntryFee(String.valueOf(generateRandomNumber(2)));
		tableTemplates.selectMinimumDeckType(minDecksToUse);
		tableTemplates.selectAutoSplit(autoSplitOption);
		tableTemplates.selectRejoinAllowed(rejoinOption);
		tableTemplates.selectSplitAllowed(splitOption);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableTemplates.clickCreateButton();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//tableTemplates.clickConfirmButton();
		
		

	}

	@DataProvider(name = "poolValues")
	public Object[][] getPoolValues() {
		return new Object[][] { { TemplateConstants.REAL_TYPE,
				TemplateConstants.POOL_SETTLEMENT_TYPE,
				TemplateConstants.TABLE_SPEED_20,
				TemplateConstants.TABLE_SIZE_6,
				TemplateConstants.SERVICEFEE_TYPE_PERCENTAGE,
				TemplateConstants.MIN_DECK_2,
				TemplateConstants.POOL_101,
				String.valueOf(generateRandomNumber(1)),
				TemplateConstants.OPTION_OFF,
				TemplateConstants.OPTION_YES,
				TemplateConstants.OPTION_YES } };
	}
	
	@Test(enabled=false,description = "Create a given deal game table of given size with the given table speed", dataProvider = "dealValues")
	public void createDealsRummyTable(String prizeType, String settlementType,
			String tableSpeed, String tableSize, String serviceFeeType,
			String minDecksToUse, String noOfprizes) {

		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		String name = String.format("%s%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION,
				settlementType, getRandomString(2));
		tableTemplates.clickNewWebAdminTab();
		tableTemplates.clickTableTemplatesTab();
		tableTemplates.clickCreateTableTemplatesTab();
		tableTemplates.fillTableTemplateName(name);
		tableTemplates.selectPrizeType(prizeType);
		tableTemplates.selectSettlementType(settlementType);
		tableTemplates.selectTableType(TemplateConstants.TABLE_AUTOMATIC);
		tableTemplates.selectTableSpeed(tableSpeed);
		tableTemplates.selectTableSize(tableSize);
		tableTemplates.fillServiceFee(String.valueOf(generateRandomNumber(1)));
		tableTemplates.selectServiceFeeType(serviceFeeType);
		tableTemplates.fillEntryFee(String.valueOf(generateRandomNumber(2)));
		java.util.Random random = new java.util.Random();
		int noOfDeals = Integer.valueOf(tableSize)+random.nextInt(5);		
		tableTemplates.fillNoOfDeals(String.valueOf(noOfDeals));
		tableTemplates.selectNoOfPrizesInDeals(noOfprizes);
		tableTemplates.selectMinimumDeckType(minDecksToUse);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableTemplates.clickCreateButton();
		//tableTemplates.clickConfirmButton();
	
		
	}

	@DataProvider(name = "dealValues")
	public Object[][] getDealsValues() {
		return new Object[][] { { TemplateConstants.REAL_TYPE,
				TemplateConstants.DEALS_SETTLEMENT_TYPE,
				TemplateConstants.TABLE_SPEED_30,
				TemplateConstants.TABLE_SIZE_2,
				TemplateConstants.SERVICEFEE_TYPE_PERCENTAGE,
				TemplateConstants.MIN_DECK_2,
				TemplateConstants.NO_OF_PRIZES_1
				 } };
	}

	@Test(description = "Create a given Raise rummy game table of given size with the given table speed", dataProvider = "raiseValues")
	public void createRaiseRummyTable(String prizeType, String settlementType,
			String tableSpeed, String tableSize, String serviceFeeType,
			String minDecksToUse) {

		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		String name = String.format("%s%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION,
				settlementType, getRandomString(2));
		tableTemplates.clickNewWebAdminTab();
		tableTemplates.clickTableTemplatesTab();
		tableTemplates.clickCreateTableTemplatesTab();
		tableTemplates.fillTableTemplateName(name);
		tableTemplates.selectPrizeType(prizeType);
		tableTemplates.selectSettlementType(settlementType);
		tableTemplates.selectTableType(TemplateConstants.TABLE_AUTOMATIC);
		tableTemplates.selectTableSpeed(tableSpeed);
		tableTemplates.selectTableSize(tableSize);
		tableTemplates.fillServiceFee(String.valueOf(generateRandomNumber(1)));
		tableTemplates.selectServiceFeeType(serviceFeeType);
		tableTemplates.fillStartPointInRaiseRummy(String.valueOf(generateRandomNumber(1)));
		tableTemplates.fillTimeBetweenDeals(String.valueOf(generateRandomNumber(1)));
	
		tableTemplates.fillNumberOfIncrementsInRaiseRummy(String.valueOf(generateRandomNumber(1)));
		tableTemplates.fillIncrementPerRoundInRaiseRummy(String.valueOf(generateRandomNumber(1)));
		tableTemplates.selectMinimumDeckType(minDecksToUse);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableTemplates.clickCreateButton();
		//tableTemplates.clickConfirmButton();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@DataProvider(name = "raiseValues")
	public Object[][] getRaiseValues() {
		return new Object[][] { { TemplateConstants.REAL_TYPE,
				TemplateConstants.RAISE_SETTLEMENT_TYPE,
				TemplateConstants.TABLE_SPEED_30,
				TemplateConstants.TABLE_SIZE_2,
				TemplateConstants.SERVICEFEE_TYPE_PERCENTAGE,
				TemplateConstants.MIN_DECK_2,
			
				 } };
	}

}
