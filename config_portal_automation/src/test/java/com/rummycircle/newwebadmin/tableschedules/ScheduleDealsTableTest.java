package com.rummycircle.newwebadmin.tableschedules;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rummycircle.APICalls;
import com.rummycircle.model.tablerequest.Context;
import com.rummycircle.model.tablerequest.TableRequest;
import com.rummycircle.model.tablerequest.Value;
import com.rummycircle.pageobject.running.schedules.RunningSchedulesPage;
import com.rummycircle.pageobjects.login.LoginPage;
import com.rummycircle.pageobjects.newwebadmin.createtables.CreateTableTemplatesPage;
import com.rummycircle.pageobjects.newwebadmin.createtables.TemplateConstants;
import com.rummycircle.pageobjects.newwebadmin.manageschedule.ManageSchedulesPage;
import com.rummycircle.pageobjects.newwebadmin.scheduletables.CreateSchedulePage;
import com.rummycircle.pageobjects.newwebadmin.scheduletables.ScheduleContants;
import com.rummycircle.ui.DriverManager;
import com.rummycircle.utils.Utility;
import com.rummycircle.utils.testutils.Assert;
import com.rummycircle.utils.testutils.NewBaseTest;
import com.rummycircle.utils.testutils.PropertyReader;

public class ScheduleDealsTableTest extends NewBaseTest {

	protected Properties config = null;
	protected Properties custom = null;
	protected Logger log = null;

	public ScheduleDealsTableTest() {
		config = PropertyReader.loadCustomProperties("config.properties");
		custom = PropertyReader.loadCustomProperties("custom.properties");
		log = Logger.getLogger(ScheduleRaiseTableTest.class);
	}

	@BeforeMethod
	public void initialize() {
		openBaseURL();
		LoginPage loginPage = new LoginPage(DriverManager.getWebDriver().get());
		loginPage.enterUsername(config.getProperty("config.portal.username"));
		loginPage.enterPassword(config.getProperty("config.portal.password"));
		loginPage.clickLoginButton();
	}

	@Test(description = "Schedule a created Deals table", dataProvider = "createDealsTableValues")
	public void scheduleDealTable(int prizeType, int settlementtype,
			String variantName, String autoCreationStatus) {
		String name=String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION, getRandomString(4));
		String noOfTables=String
				.valueOf(generateRandomNumber(2));
		createDealsTemplateUsingAPI(prizeType, settlementtype,name);
		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		tableTemplates.clickNewWebAdminTab();
		CreateSchedulePage scheduleDealsRummyPage = new CreateSchedulePage(
				DriverManager.getWebDriver().get());
		scheduleDealsRummyPage.clickTableSchedulesTab();
		scheduleDealsRummyPage.clickCreateSchedulesTab();
		if(variantName.contains(ScheduleContants.DEALS_PRACTICE_VARIANT))
			scheduleDealsRummyPage.selectVariant(ScheduleContants.DEALS_PRACTICE_VARIANT);
		else
			scheduleDealsRummyPage.selectVariant(ScheduleContants.DEALS_CASH_VARIANT);
	
		scheduleDealsRummyPage.clickCreationStartTime();
		scheduleDealsRummyPage.clickSpecificStartDate(String.valueOf(Utility.getIncrementedDay(1)));
		scheduleDealsRummyPage.clickCreationEndTime();
		scheduleDealsRummyPage.clickSpecificEndDate(String.valueOf(Utility
				.getIncrementedDay(1)));
		scheduleDealsRummyPage.incremenetEndYear();
		scheduleDealsRummyPage.incrementEndHour();
		scheduleDealsRummyPage.incrementEndMinute();
		scheduleDealsRummyPage.fillNoOfTables(noOfTables);
		scheduleDealsRummyPage.selectAutoCreationStatus(autoCreationStatus);
		scheduleDealsRummyPage.clickCreateButton();		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ManageSchedulesPage schedulesPage = new ManageSchedulesPage(DriverManager.getWebDriver().get());
		schedulesPage.clickManageSchedulesTab();
		if(variantName.contains(ScheduleContants.DEALS_PRACTICE_VARIANT))
		schedulesPage.clickDealsPractice();
		else
		schedulesPage.clickDealsCash();
		schedulesPage.clickEditSchedules();
		Assert.assertEquals(schedulesPage.getTextOfSelectedVariantName(), variantName, "Variant Name", false);
		Assert.assertEquals(schedulesPage.getTextofTableTemplate(), name, "Table Template Name", false);
		Assert.assertNotNull(schedulesPage.getTextOfStartTime(),"Start Time" , false);
		Assert.assertNotNull(schedulesPage.getTextOfEndTime(),"End Time" , false);
		Assert.assertEquals(schedulesPage.getTextOfNumberOfTables(), noOfTables, "No. of Tables", false);
		Assert.assertEquals(schedulesPage.getSelectedValueOfCreationStatus(), autoCreationStatus, "Auto Creation Status", false);
		
		runningSchedulesAction(autoCreationStatus, prizeType);
	}
	
	public void runningSchedulesAction(String autoCreationStatus,int prizeType){
		RunningSchedulesPage runningSchedulesPage = new RunningSchedulesPage(DriverManager.getWebDriver().get());
		if(autoCreationStatus.equals(ScheduleContants.CREATION_STATUS_ENABLED)){
			runningSchedulesPage.clickRunningSchedules();
			runningSchedulesPage.clickEnabledSchedules();
			if(prizeType==TemplateConstants.PRIZE_TYPE_CASH){
				runningSchedulesPage.clickDealsCash();
			}else{
				runningSchedulesPage.clickDealsPractice();
			}
			runningSchedulesPage.clickScheduledTemplate();
			runningSchedulesPage.clickDisabledButton();
			runningSchedulesPage.clickYesButton();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createDealsTemplateUsingAPI(int prizeType, int settlementtype,String name) {

		Value value = new Value();
		value.setTemplatename(name);
		value.setPrizetype(prizeType);
		value.setSettlementtype(settlementtype);
		value.setTablenametype("A");
		value.setTablenameprefix("NA");
		value.setTablespeed(15000);
		value.setTablesize(2);
		value.setPointvalue(0);
		value.setEntryfee((int) generateRandomNumber(2));
		value.setDealstoplay(5);
		value.setServicefee((int) generateRandomNumber(1));
		value.setCardstodealt(13);
		value.setDecktouse(2);
		value.setCardpuresequence(3);
		value.setJokers(2);
		value.setMinbuyin(312);
		value.setMaxbuyin(31200);
		value.setDealBetweenTime(15);
		value.setPooltype(0);
		value.setSfeetype(1);
		value.setRejoin(0);
		value.setSplit(0);
		value.setMaxdeals(0);
		value.setNoofprizes(1);
		value.setGamePlay(2);
		value.setControllerType(1);
		value.setNoOfIncrements(0);
		value.setIncrementsPerRound(0);
		value.setSkillBased(false);

		Context context = new Context();
		context.setChannelId(1);
		context.setLoginId("qa.automation3");
		context.setSource("WebadminPortal");
		context.setTime(System.currentTimeMillis());

		TableRequest tablerequest = new TableRequest();
		tablerequest.setContext(context);
		tablerequest.setValue(value);

		APICalls createTableAPI = new APICalls();
		createTableAPI.createTableProperties(tablerequest);

	}

	@DataProvider(name = "createDealsTableValues")
	public Object[][] creationValues() {
		return new Object[][] { { (int) TemplateConstants.PRIZE_TYPE_PRACTICE,
				(int) TemplateConstants.SETTLEMENT_DEALS,
				ScheduleContants.DEALS_PRACTICE_VARIANT,ScheduleContants.CREATION_STATUS_ENABLED },{ (int) TemplateConstants.PRIZE_TYPE_CASH,
					(int) TemplateConstants.SETTLEMENT_DEALS,
					ScheduleContants.DEALS_CASH_VARIANT,ScheduleContants.CREATION_STATUS_ENABLED }};
	}



}
