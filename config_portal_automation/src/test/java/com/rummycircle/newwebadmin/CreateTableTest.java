package com.rummycircle.newwebadmin;

import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rummycircle.APICalls;
import com.rummycircle.ServicesEndPoint;
import com.rummycircle.Util;
import com.rummycircle.model.TableResponse;
import com.rummycircle.pageobjects.login.LoginPage;
import com.rummycircle.pageobjects.newwebadmin.createtables.CreateTableTemplatesPage;
import com.rummycircle.pageobjects.newwebadmin.createtables.TemplateConstants;
import com.rummycircle.pageobjects.newwebadmin.managetabletemplates.ManageDealsTemplate;
import com.rummycircle.pageobjects.newwebadmin.managetabletemplates.ManagePointsTemplate;
import com.rummycircle.pageobjects.newwebadmin.managetabletemplates.ManagePoolsTemplate;
import com.rummycircle.pageobjects.newwebadmin.managetabletemplates.ManageRaiseTemplate;
import com.rummycircle.restclient.HTTPHeaders;
import com.rummycircle.restclient.HTTPMethod;
import com.rummycircle.restclient.HTTPParams;
import com.rummycircle.restclient.HTTPRequest;
import com.rummycircle.restclient.HTTPResponse;
import com.rummycircle.ui.DriverManager;
import com.rummycircle.utils.testutils.Assert;
import com.rummycircle.utils.testutils.NewBaseTest;
import com.rummycircle.utils.testutils.PropertyReader;

import dataprovider.RC_DataProvider;

public class CreateTableTest extends NewBaseTest {

	protected Properties config = null;
	protected Properties custom = null;
	protected Logger log = null;

	public CreateTableTest() {
		config = PropertyReader.loadCustomProperties("config.properties");
		custom = PropertyReader.loadCustomProperties("custom.properties");
		log = Logger.getLogger(CreateTableTest.class);
	}

	@BeforeMethod
	public void initialize() {
		openBaseURL();
		LoginPage loginPage = new LoginPage(DriverManager.getWebDriver().get());
		loginPage.enterUsername(config.getProperty("config.portal.username"));
		loginPage.enterPassword(config.getProperty("config.portal.password"));
		loginPage.clickLoginButton();
	}

	@Test(description = "Create a given points game table of given size with the given table speed", dataProvider = "getTestDataForMyTest", dataProviderClass = RC_DataProvider.class)
	public void createCFPTable(String testData) {

		Map<String, String> map = Util.convertObjMapToStringMap(Util
				.jsonToMapConverter(testData));
		String name = String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION, getRandomString(4));
		long serviceFee = generateRandomNumber(1);
		long timeBetweenDeals = generateRandomNumber(1);
		long pointValue = generateRandomNumber(3);// change this to insert a
													// custom value.

		createPointsTemplate(map, name, serviceFee, timeBetweenDeals,
				pointValue);

		TableResponse tableResponse = new APICalls()
				.getTablePropertiesByName(name);
		Assert.assertNotNull(tableResponse, "Response of table template", false);
		if (map.get("prizetype").equals("Real Cash")) {
			Assert.assertEquals(
					tableResponse.getValues().get(0).getPrizetype(),
					TemplateConstants.PRIZE_TYPE_CASH, "Prize Type", false);

		} else {
			Assert.assertEquals(
					tableResponse.getValues().get(0).getPrizetype(),
					TemplateConstants.PRIZE_TYPE_PRACTICE, "Prize Type", false);

		}
		Assert.assertEquals(tableResponse.getValues().get(0)
				.getSettlementtype(), TemplateConstants.SETTLEMENT_POINTS,
				"Settlement Type", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getTablesize(),
				Long.valueOf(map.get("tablesize")), "Table Size", false);
		Assert.assertEquals(
				tableResponse.getValues().get(0).getTablespeed() / 1000,
				Long.valueOf(map.get("tablespeed")), "Table Speed", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getDecktouse(),
				Long.valueOf(map.get("minimumdecktype")), "Minimum Deck Type",
				false);
		Assert.assertEquals(tableResponse.getValues().get(0).isSkillBased(),
				Boolean.valueOf(map.get("skilltype")), "Skill type", false);

		managePointsTemplates(map, name, serviceFee, timeBetweenDeals,
				pointValue);
		map.clear();
	}

	public void createPointsTemplate(Map<String, String> map, String name,
			long serviceFee, long timeBetweenDeals, long points) {
		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());

		tableTemplates.clickNewWebAdminTab();
		tableTemplates.clickTableTemplatesTab();
		tableTemplates.clickCreateTableTemplatesTab();
		tableTemplates.fillTableTemplateName(name);
		tableTemplates.selectPrizeType(map.get("prizetype"));
		tableTemplates.selectSettlementType(map.get("settlementtype"));
		tableTemplates.selectTableType(TemplateConstants.TABLE_AUTOMATIC);
		tableTemplates.selectTableSpeed(map.get("tablespeed"));
		tableTemplates.selectTableSize(map.get("tablesize"));
		tableTemplates.fillServiceFee(String.valueOf(serviceFee));
		tableTemplates.selectServiceFeeType(map.get("servicefeetype"));
		if (map.get("points") == null)
			tableTemplates.fillPointValue(String.valueOf(points));
		else
			tableTemplates.fillPointValue(map.get("points"));
		tableTemplates.fillTimeBetweenDeals(String.valueOf(timeBetweenDeals));
		tableTemplates.selectMinimumDeckType(map.get("minimumdecktype"));
		tableTemplates.selectSkillType(map.get("skilltype"));
		tableTemplates.clickCreateButton();
		tableTemplates.clickConfirmButton();
		tableTemplates.milliSecWait(5000);

	}

	public void managePointsTemplates(Map<String, String> map, String name,
			long serviceFee, long timeBetweenDeals, long points) {
		ManagePointsTemplate manageTemplate = new ManagePointsTemplate(
				DriverManager.getWebDriver().get());
		manageTemplate.clickManageTemplateTab();
		if (map.get("prizetype").equals("Real Cash")) {
			manageTemplate.clickCFPCashLink();
		} else {
			manageTemplate.clickCFPPracticeLink();
		}

		manageTemplate.clickEditLink();
		Assert.assertEquals(manageTemplate.getValueOfTemplateName(), name,
				"Name of the template", false);
		Assert.assertEquals(manageTemplate.getSelectedValueOfTableType(),
				TemplateConstants.TABLE_AUTOMATIC, "table naming type", false);
		Assert.assertEquals(manageTemplate.getSelectedValueOfServiceFeeType(),
				map.get("servicefeetype"), "Service fee type", false);
		if (map.get("points") == null)
			Assert.assertEquals(manageTemplate.getValueOfPointValue(),
					String.valueOf(points), "Point Value", false);
		else
			Assert.assertEquals(manageTemplate.getValueOfPointValue(),
					map.get("points"), "Point Value", false);
		Assert.assertEquals(manageTemplate.getValueOfServiceFee(),
				String.valueOf(serviceFee), "Service fee", false);
		Assert.assertEquals(manageTemplate.getValueOfTimeBetweenDeals(),
				String.valueOf(timeBetweenDeals), "Time between deals", false);

	}

	@Test(enabled = false,description = "Create a given pool game table of given size with the given table speed", dataProvider = "getTestDataForMyTest", dataProviderClass = RC_DataProvider.class)
	public void createPoolRummyTable(String testData) {

		Map<String, String> map = Util.convertObjMapToStringMap(Util
				.jsonToMapConverter(testData));
		String name = String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION, getRandomString(4));
		String servicefee = String.valueOf(generateRandomNumber(2));
		String entryfee = String.valueOf(generateRandomNumber(3));
		String timeBetweenDeals = String.valueOf(generateRandomNumber(1));
		createPoolTemplate(name, map, entryfee, servicefee, timeBetweenDeals);
		TableResponse tableResponse = new APICalls()
				.getTablePropertiesByName(name);
		Assert.assertNotNull(tableResponse, "Response of table template", false);
		if (map.get("prizetype").equals("Real Cash")) {
			Assert.assertEquals(
					tableResponse.getValues().get(0).getPrizetype(),
					TemplateConstants.PRIZE_TYPE_CASH, "Prize Type", false);

		} else {
			Assert.assertEquals(
					tableResponse.getValues().get(0).getPrizetype(),
					TemplateConstants.PRIZE_TYPE_PRACTICE, "Prize Type", false);

		}
		Assert.assertEquals(tableResponse.getValues().get(0)
				.getSettlementtype(), TemplateConstants.SETTLEMENT_POOL,
				"Settlement Type", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getTablesize(),
				Long.valueOf(map.get("tablesize")), "Table Size", false);
		Assert.assertEquals(
				tableResponse.getValues().get(0).getTablespeed() / 1000,
				Long.valueOf(map.get("tablespeed")), "Table Speed", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getDecktouse(),
				Long.valueOf(map.get("minimumdecktype")), "Minimum Deck Type",
				false);
		Assert.assertEquals(
				tableResponse.getValues().get(0).getPooltype(),
				map.get("pooltype").equals("Pool 101") ? Long
						.valueOf(TemplateConstants.POOL_101_CODE) : Long
						.valueOf(TemplateConstants.POOL_201_CODE), "Pool type",
				false);
		Assert.assertEquals(tableResponse.getValues().get(0).getAutosplit(),
				fetchAutoSplitCode(map.get("isAutoSplit")), "Auto Split Value",
				false);

		Assert.assertEquals(tableResponse.getValues().get(0).getRejoin(),
				fetchCode(map.get("isRejoin")), "Rejoin Value", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getSplit(),
				fetchCode(map.get("isSplit")), "Split Value", false);

		managePoolTemplates(name, map, servicefee, timeBetweenDeals, entryfee);
		map.clear();

	}

	public long fetchAutoSplitCode(String property) {
		return property.equals("On") ? TemplateConstants.OPTION_ON_CODE
				: TemplateConstants.OPTION_OFF_CODE;
	}

	public long fetchCode(String property) {

		return property.equals("Yes") ? TemplateConstants.OPTION_YES_CODE
				: TemplateConstants.OPTION_NO_CODE;
	}

	public void createPoolTemplate(String name, Map<String, String> map,
			String entryfee, String servicefee, String timeBetweenDeals) {
		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		tableTemplates.clickNewWebAdminTab();
		tableTemplates.clickTableTemplatesTab();
		tableTemplates.clickCreateTableTemplatesTab();
		tableTemplates.fillTableTemplateName(name);
		tableTemplates.selectPrizeType(map.get("prizetype"));
		tableTemplates.selectSettlementType(map.get("settlementtype"));
		tableTemplates.selectPoolType(map.get("pooltype"));
		tableTemplates.selectTableType(TemplateConstants.TABLE_AUTOMATIC);
		tableTemplates.selectTableSpeed(map.get("tablespeed"));
		tableTemplates.selectTableSize(map.get("tablesize"));
		tableTemplates.fillServiceFee(servicefee);
		tableTemplates.selectServiceFeeType(map.get("servicefeetype"));
		tableTemplates.fillTimeBetweenDeals(timeBetweenDeals);
		tableTemplates.fillEntryFee(entryfee);
		tableTemplates.selectMinimumDeckType(map.get("minimumdecktype"));
		tableTemplates.selectAutoSplit(map.get("isAutoSplit"));
		tableTemplates.selectRejoinAllowed(map.get("isRejoin"));
		tableTemplates.selectSplitAllowed(map.get("isSplit"));
		tableTemplates.clickCreateButton();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableTemplates.clickConfirmButton();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void managePoolTemplates(String name, Map<String, String> map,
			String serviceFee, String timeBetweenDeals, String entryfee) {
		ManagePoolsTemplate managePoolsTemplate = new ManagePoolsTemplate(
				DriverManager.getWebDriver().get());
		managePoolsTemplate.clickManageTemplateTab();
		if (map.get("prizetype").equals("Real Cash")) {
			managePoolsTemplate.clickPoolsCashLink();
		} else {
			managePoolsTemplate.clickPoolsPracticeLink();
		}

		managePoolsTemplate.clickEditLink();
		Assert.assertEquals(managePoolsTemplate.getValueOfTemplateName(), name,
				"Name of the template", false);
		Assert.assertEquals(managePoolsTemplate.getSelectedValueOfPrizeType(),
				map.get("prizetype"), "Prize type", false);
		Assert.assertEquals(
				managePoolsTemplate.getSelectedValueOfSettlementType(),
				map.get("settlementtype"), "Settlement type", false);

		if (map.get("pooltype").equals(TemplateConstants.POOL_101))
			Assert.assertEquals(
					managePoolsTemplate.getSelectedValueOfPoolType(),
					TemplateConstants.POOL_101, "Pool Type", false);
		else
			Assert.assertEquals(
					managePoolsTemplate.getSelectedValueOfPoolType(),
					TemplateConstants.POOL_201, "Pool Type", false);

		Assert.assertEquals(managePoolsTemplate.getSelectedValueOfTableType(),
				TemplateConstants.TABLE_AUTOMATIC, "table naming type", false);
		Assert.assertEquals(managePoolsTemplate.getSelectedValueOfTableSize(),
				map.get("tablesize"), "Table Size", false);
		Assert.assertEquals(
				managePoolsTemplate.getSelectedValueOfServiceFeeType(),
				map.get("servicefeetype"), "Service fee type", false);

		Assert.assertEquals(managePoolsTemplate.getValueOfServiceFee(),
				String.valueOf(serviceFee), "Service fee", false);
		Assert.assertEquals(managePoolsTemplate.getValueOfTimeBetweenDeals(),
				String.valueOf(timeBetweenDeals), "Time between deals", false);
		Assert.assertEquals(managePoolsTemplate.getValueOfEntryFee(), entryfee,
				"Entry Fee", false);

		if (map.get("isRejoin").equals("Yes"))
			Assert.assertEquals(
					managePoolsTemplate.getSelectedValueOfRejoinAllowed(),
					TemplateConstants.OPTION_YES, "isRejoin", false);
		else
			Assert.assertEquals(
					managePoolsTemplate.getSelectedValueOfRejoinAllowed(),
					TemplateConstants.OPTION_NO, "isRejoin", false);

		if (map.get("isSplit").equals("Yes"))
			Assert.assertEquals(
					managePoolsTemplate.getSelectedValueOfPrizeSplitAllowed(),
					TemplateConstants.OPTION_YES, "isSplit Allowed", false);
		else
			Assert.assertEquals(
					managePoolsTemplate.getSelectedValueOfPrizeSplitAllowed(),
					TemplateConstants.OPTION_NO, "isSplit Allowed", false);

		if (map.get("isAutoSplit").equals("Off"))
			Assert.assertEquals(
					managePoolsTemplate.getSelectedValueOfAutoSplitAllowed(),
					TemplateConstants.OPTION_OFF, "isAutoSplit Allowed", false);
		else
			Assert.assertEquals(
					managePoolsTemplate.getSelectedValueOfAutoSplitAllowed(),
					TemplateConstants.OPTION_ON, "isAutoSplit Allowed", false);

		Assert.assertEquals(managePoolsTemplate.getSelectedValueOfMinDecks(),
				map.get("minimumdecktype"), "Deck type", false);
		Assert.assertEquals(managePoolsTemplate.getSelectedValueOfTableSpeed(),
				map.get("tablespeed"), "Table speed", false);
	}

	@DataProvider(name = "poolValues")
	public Object[][] getPoolValues() {
		return new Object[][] { { TemplateConstants.REAL_TYPE,
				TemplateConstants.POOL_SETTLEMENT_TYPE,
				TemplateConstants.TABLE_SPEED_20,
				TemplateConstants.TABLE_SIZE_6,
				TemplateConstants.SERVICEFEE_TYPE_PERCENTAGE,
				TemplateConstants.MIN_DECK_2, TemplateConstants.POOL_101,
				String.valueOf(generateRandomNumber(1)),
				TemplateConstants.OPTION_OFF, TemplateConstants.OPTION_YES,
				TemplateConstants.OPTION_YES } };
	}

	@Test(enabled = false, description = "Create a given deal game table of given size with the given table speed", dataProvider = "getTestDataForMyTest", dataProviderClass = RC_DataProvider.class)
	public void createDealsRummyTable(String testData) {
		Map<String, String> map = Util.convertObjMapToStringMap(Util
				.jsonToMapConverter(testData));

		String name = String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION, getRandomString(4));
		String servicefee = String.valueOf(generateRandomNumber(1));
		String entryfee = String.valueOf(generateRandomNumber(2));
		java.util.Random random = new java.util.Random();
		int noOfDeals = Integer.valueOf(map.get("tablesize"))
				+ random.nextInt(5);
		createDealsTemplate(name, map, servicefee, entryfee,
				String.valueOf(noOfDeals));

		TableResponse tableResponse = new APICalls()
				.getTablePropertiesByName(name);
		Assert.assertNotNull(tableResponse, "Response of table template", false);
		if (map.get("prizetype").equals("Real Cash")) {
			Assert.assertEquals(
					tableResponse.getValues().get(0).getPrizetype(),
					TemplateConstants.PRIZE_TYPE_CASH, "Prize Type", false);

		} else {
			Assert.assertEquals(
					tableResponse.getValues().get(0).getPrizetype(),
					TemplateConstants.PRIZE_TYPE_PRACTICE, "Prize Type", false);

		}
		Assert.assertEquals(tableResponse.getValues().get(0)
				.getSettlementtype(), TemplateConstants.SETTLEMENT_DEALS,
				"Settlement Type", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getTablesize(),
				Long.valueOf(map.get("tablesize")), "Table Size", false);
		Assert.assertEquals(
				tableResponse.getValues().get(0).getTablespeed() / 1000,
				Long.valueOf(map.get("tablespeed")), "Table Speed", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getDecktouse(),
				Long.valueOf(map.get("minimumdecktype")), "Minimum Deck", false);

		manageDealsTemplate(name, map, servicefee, entryfee,
				String.valueOf(noOfDeals));

	}

	public void createDealsTemplate(String name, Map<String, String> map,
			String sfee, String entryFee, String noOfDeals) {
		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		tableTemplates.clickNewWebAdminTab();
		tableTemplates.clickTableTemplatesTab();
		tableTemplates.clickCreateTableTemplatesTab();
		tableTemplates.fillTableTemplateName(name);
		tableTemplates.selectPrizeType(map.get("prizetype"));
		tableTemplates.selectSettlementType(map.get("settlementtype"));
		tableTemplates.selectTableType(TemplateConstants.TABLE_AUTOMATIC);
		tableTemplates.selectTableSpeed(map.get("tablespeed"));
		tableTemplates.selectTableSize(map.get("tablesize"));
		tableTemplates.fillServiceFee(sfee);
		tableTemplates.selectServiceFeeType(map.get("servicefeetype"));
		tableTemplates.fillEntryFee(entryFee);

		tableTemplates.fillNoOfDeals(String.valueOf(noOfDeals));
		tableTemplates.selectNoOfPrizesInDeals(map.get("noofprizes"));
		tableTemplates.selectMinimumDeckType(map.get("minimumdecktype"));
		tableTemplates.clickCreateButton();
		tableTemplates.clickConfirmButton();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void manageDealsTemplate(String name, Map<String, String> map,
			String sfee, String entryFee, String noOfDeals) {
		ManageDealsTemplate manageDealsTemplate = new ManageDealsTemplate(
				DriverManager.getWebDriver().get());
		manageDealsTemplate.clickManageTemplateTab();
		if (map.get("prizetype").equals("Real Cash")) {
			manageDealsTemplate.clickDealsCashLink();
		} else
			manageDealsTemplate.clickDealsPracticeLink();
		manageDealsTemplate.clickEditLink();
		Assert.assertEquals(manageDealsTemplate.getValueOfTemplateName(), name,
				"Name of the template", false);
		Assert.assertEquals(manageDealsTemplate.getSelectedValueOfTableType(),
				TemplateConstants.TABLE_AUTOMATIC, "table naming type", false);
		Assert.assertEquals(
				manageDealsTemplate.getSelectedValueOfServiceFeeType(),
				map.get("servicefeetype"), "Service fee type", false);
		/*
		 * Initially checks if the map contains this value, other it retrieves
		 * the value from the function parameter
		 */
		if (map.get("deals") == null)
			Assert.assertEquals(manageDealsTemplate.getValueOfDeals(),
					noOfDeals, "deals Value", false);
		else
			Assert.assertEquals(manageDealsTemplate.getValueOfDeals(),
					map.get("deals"), "deals Value", false);
		Assert.assertEquals(manageDealsTemplate.getValueOfServiceFee(),
				String.valueOf(sfee), "Service fee", false);
		Assert.assertEquals(manageDealsTemplate.getValueOfEntryFee(), entryFee,
				"Entry fee", false);
		Assert.assertEquals(manageDealsTemplate.getSelectedValueOfNoOfPrizes(),
				map.get("noofprizes"), "No. of prizes", false);

	}

	@Test( description = "Create a given Raise rummy game table of given size with the given table speed", dataProvider = "getTestDataForMyTest", dataProviderClass = RC_DataProvider.class)
	public void createRaiseRummyTable(String testData) {

		Map<String, String> map = Util.convertObjMapToStringMap(Util
				.jsonToMapConverter(testData));

		String name = String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION, getRandomString(4));
		String startPoint = String.valueOf(generateRandomNumber(2));
		String timeBetweenDeals = String.valueOf(generateRandomNumber(1));
		String noOfIncrements = String.valueOf(generateRandomNumber(1));
		String incrementPerRound = String.valueOf(generateRandomNumber(1));
		String serviceFee = String.valueOf(generateRandomNumber(1));

		createRaiseTemplate(name, map, startPoint, timeBetweenDeals,
				noOfIncrements, incrementPerRound, serviceFee);

		TableResponse tableResponse = new APICalls()
				.getTablePropertiesByName(name);
		Assert.assertNotNull(tableResponse, "Response of table template", false);
		if (map.get("prizetype").equals("Real Cash")) {
			Assert.assertEquals(
					tableResponse.getValues().get(0).getPrizetype(),
					TemplateConstants.PRIZE_TYPE_CASH, "Prize Type", false);

		} else {
			Assert.assertEquals(
					tableResponse.getValues().get(0).getPrizetype(),
					TemplateConstants.PRIZE_TYPE_PRACTICE, "Prize Type", false);

		}
		Assert.assertEquals(tableResponse.getValues().get(0)
				.getSettlementtype(), TemplateConstants.SETTLEMENT_RAISE,
				"Settlement Type", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getTablesize(),
				Long.valueOf(map.get("tablesize")), "Table Size", false);
		Assert.assertEquals(
				tableResponse.getValues().get(0).getTablespeed() / 1000,
				Long.valueOf(map.get("tablespeed")), "Table Speed", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getDecktouse(),
				Long.valueOf(map.get("minimumdecktype")), "Minimum Deck Type",
				false);
		Assert.assertEquals(tableResponse.getValues().get(0).getServicefee(),Long.valueOf(serviceFee) , "Service Fee", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getPointvalue(),Long.valueOf(startPoint) , "Start Point", false);
		
		Assert.assertEquals(tableResponse.getValues().get(0).getDealBetweenTime(), Long.valueOf(timeBetweenDeals), "Time between deals", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getNoOfIncrements(), Long.valueOf(noOfIncrements), "No. of Increments", false);
		Assert.assertEquals(tableResponse.getValues().get(0).getIncrementsPerRound(), Long.valueOf(incrementPerRound), "Increment Per Round", false);
		
		manageRaiseTemplate(name, map, startPoint, timeBetweenDeals, noOfIncrements, incrementPerRound, serviceFee);

		

	}

	public void createRaiseTemplate(String name, Map<String, String> map,
			String startPoint, String timeBetweenDeals, String noOfIncrements,
			String incremeentPerRound, String serviceFee) {
		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		tableTemplates.clickNewWebAdminTab();
		tableTemplates.clickTableTemplatesTab();
		tableTemplates.clickCreateTableTemplatesTab();
		tableTemplates.fillTableTemplateName(name);
		tableTemplates.selectPrizeType(map.get("prizetype"));
		tableTemplates.selectSettlementType(map.get("settlementtype"));
		tableTemplates.selectTableType(TemplateConstants.TABLE_AUTOMATIC);
		tableTemplates.selectTableSpeed(map.get("tablespeed"));
		tableTemplates.selectTableSize(map.get("tablesize"));
		tableTemplates.fillServiceFee(serviceFee);
		tableTemplates.selectServiceFeeType(map.get("servicefeetype"));
		tableTemplates.fillStartPointInRaiseRummy(startPoint);
		tableTemplates.fillTimeBetweenDeals(timeBetweenDeals);

		tableTemplates.fillNumberOfIncrementsInRaiseRummy(noOfIncrements);
		tableTemplates.fillIncrementPerRoundInRaiseRummy(incremeentPerRound);
		tableTemplates.selectMinimumDeckType(map.get("minimumdecktype"));
		tableTemplates.clickCreateButton();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableTemplates.clickConfirmButton();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void manageRaiseTemplate(String name, Map<String, String> map,
			String startPoint, String timeBetweenDeals, String noOfIncrements,
			String incrementPerRound, String serviceFee){
		
		ManageRaiseTemplate manageRaiseTemplate = new ManageRaiseTemplate(
				DriverManager.getWebDriver().get());
		manageRaiseTemplate.clickManageTemplateTab();
		if (map.get("prizetype").equals("Real Cash")) {
			manageRaiseTemplate.clickRaiseCashLink();
		} else {
			manageRaiseTemplate.clickRaisePracticeLink();
		}

		manageRaiseTemplate.clickEditLink();
		
		Assert.assertEquals(manageRaiseTemplate.getValueOfTemplateName(), name,
				"Name of the template", false);
		Assert.assertEquals(manageRaiseTemplate.getSelectedValueOfPrizeType(),
				map.get("prizetype"), "Prize type", false);
		Assert.assertEquals(
				manageRaiseTemplate.getSelectedValueOfSettlementType(),
				map.get("settlementtype"), "Settlement type", false);

		
		Assert.assertEquals(manageRaiseTemplate.getSelectedValueOfTableType(),
				TemplateConstants.TABLE_AUTOMATIC, "table naming type", false);
		Assert.assertEquals(manageRaiseTemplate.getSelectedValueOfTableSize(),
				map.get("tablesize"), "Table Size", false);
		Assert.assertEquals(
				manageRaiseTemplate.getSelectedValueOfServiceFeeType(),
				map.get("servicefeetype"), "Service fee type", false);
		Assert.assertEquals(
				manageRaiseTemplate.getSelectedValueOfTableSpeed(),
				map.get("tablespeed"), "Table speed", false);

		Assert.assertEquals(manageRaiseTemplate.getValueOfServiceFee(),
				String.valueOf(serviceFee), "Service fee", false);
		Assert.assertEquals(manageRaiseTemplate.getValueOfTimeBetweenDeals(),
				String.valueOf(timeBetweenDeals), "Time between deals", false);
		Assert.assertEquals(manageRaiseTemplate.getSelectedValueOfMinDecks(), map.get("minimumdecktype"), "Min Decks", false);
		Assert.assertEquals(manageRaiseTemplate.getValueOfStartPoint(), startPoint, "Start point", false);
		Assert.assertEquals(manageRaiseTemplate.getValueOfIncrementsPerRound(), incrementPerRound, "Increment per round", false);
		Assert.assertEquals(manageRaiseTemplate.getValueOfNumberOfIncrements(), noOfIncrements, "No. Of Increments", false);


		
		
	}

}
