package com.rummycircle.newwebadmin.registrationcondition;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rummycircle.pageobjects.login.LoginPage;
import com.rummycircle.pageobjects.newwebadmin.createtables.CreateTableTemplatesPage;
import com.rummycircle.pageobjects.newwebadmin.createtables.TemplateConstants;
import com.rummycircle.pageobjects.newwebadmin.registration.condition.CreateRegistrationConditionPage;
import com.rummycircle.pageobjects.newwebadmin.registration.condition.ManageRegistrationConditionPage;
import com.rummycircle.pageobjects.newwebadmin.registration.condition.RegistrationConstants;
import com.rummycircle.ui.DriverManager;
import com.rummycircle.utils.testutils.Assert;
import com.rummycircle.utils.testutils.NewBaseTest;
import com.rummycircle.utils.testutils.PropertyReader;

public class RegistrationConditionTest extends NewBaseTest {

	protected Properties config = null;
	protected Properties custom = null;
	protected Logger log = null;

	public RegistrationConditionTest() {
		config = PropertyReader.loadCustomProperties("config.properties");
		custom = PropertyReader.loadCustomProperties("custom.properties");
	}

	@BeforeMethod
	public void initialize() {
		openBaseURL();
		LoginPage loginPage = new LoginPage(DriverManager.getWebDriver().get());
		loginPage.enterUsername(config.getProperty("config.portal.username"));
		loginPage.enterPassword(config.getProperty("config.portal.password"));
		loginPage.clickLoginButton();
	}

	@Test(description = "Create a registration condition with a specific club type ")
	public void CreateRegistrationConditionTest() {

		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());

		tableTemplates.clickNewWebAdminTab();

		CreateRegistrationConditionPage registrationConditionPage = new CreateRegistrationConditionPage(
				DriverManager.getWebDriver().get());

		registrationConditionPage.clickRegistrationConditionTab();
		registrationConditionPage.clickCreateRegistrationConditionTab();
		String name = String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION, getRandomString(5));

		registrationConditionPage.fillRegistrationConditionName(name);
		registrationConditionPage.clickCheckBoxClubType();
		registrationConditionPage
				.selectClubType(RegistrationConstants.CLUB_TYPE_BRONZE);
		String errorMessage = "Custom test error message";
		registrationConditionPage
				.fillCustomMessage("Custom test error message");
		registrationConditionPage.clickCreateButton();
		registrationConditionPage.clickCloseButtonInDialogue();
		ManageRegistrationConditionPage manageRegistrationConditionPage = new ManageRegistrationConditionPage(
				DriverManager.getWebDriver().get());
		manageRegistrationConditionPage.clickManageRegistrationConditionTab();
		manageRegistrationConditionPage.fillSearchTerm(name);
		manageRegistrationConditionPage.clickEditLink();
		Assert.assertEquals(
				manageRegistrationConditionPage.getValueOfSelectClubType(),
				RegistrationConstants.CLUB_TYPE_BRONZE, "Club Type", false);
		Assert.assertEquals(
				manageRegistrationConditionPage.getTextOfErrorMessage(),
				errorMessage, "Custom Error Message", false);
	}

	@Test(description = "Create a registration condition with disallowing players with a specific ticket type ")
	public void CreateRegistrationConditionWithDisallowedPlayersTest() {

		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());

		tableTemplates.clickNewWebAdminTab();

		CreateRegistrationConditionPage registrationConditionPage = new CreateRegistrationConditionPage(
				DriverManager.getWebDriver().get());

		registrationConditionPage.clickRegistrationConditionTab();
		registrationConditionPage.clickCreateRegistrationConditionTab();
		String name = String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION, getRandomString(5));

		registrationConditionPage.fillRegistrationConditionName(name);
		registrationConditionPage.clickCheckBoxDisallowPlayerEntry();
		// TODO: Modify this flow after creating as creating a ticket first and
		// then select the option from here.
		registrationConditionPage.selectDisallowPlayerEntry("XUPTest");
		registrationConditionPage.clickCreateButton();
		registrationConditionPage.clickCloseButtonInDialogue();
		ManageRegistrationConditionPage manageRegistrationConditionPage = new ManageRegistrationConditionPage(
				DriverManager.getWebDriver().get());
		manageRegistrationConditionPage.clickManageRegistrationConditionTab();
		manageRegistrationConditionPage.fillSearchTerm(name);
		manageRegistrationConditionPage.clickEditLink();
		manageRegistrationConditionPage.milliSecWait(5000);
		Assert.assertEquals(manageRegistrationConditionPage
				.getValueOfSelectDisallowEntryType(), "XUPTest",
				"DisAllowed specific player type select", false);

	}

	@Test(description = "Create a registration condition with disallowing players with a specific ticket type ")
	public void CreateRegistrationConditionWithAllowedPlayersTest() {

		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());

		tableTemplates.clickNewWebAdminTab();

		CreateRegistrationConditionPage registrationConditionPage = new CreateRegistrationConditionPage(
				DriverManager.getWebDriver().get());

		registrationConditionPage.clickRegistrationConditionTab();
		registrationConditionPage.clickCreateRegistrationConditionTab();
		String name = String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION, getRandomString(5));

		registrationConditionPage.fillRegistrationConditionName(name);
		registrationConditionPage.clickCheckBoxAllowPlayerEntry();
		// TODO: Modify this flow after creating as creating a ticket first and
		// then select the option from here.
		registrationConditionPage.selectAllowPlayerEntry("XUPTest");
		registrationConditionPage.clickCreateButton();
		registrationConditionPage.clickCloseButtonInDialogue();
		ManageRegistrationConditionPage manageRegistrationConditionPage = new ManageRegistrationConditionPage(
				DriverManager.getWebDriver().get());
		manageRegistrationConditionPage.clickManageRegistrationConditionTab();
		manageRegistrationConditionPage.fillSearchTerm(name);
		manageRegistrationConditionPage.clickEditLink();
		manageRegistrationConditionPage.milliSecWait(5000);
		Assert.assertEquals(
				manageRegistrationConditionPage.getValueOfAllowEntryType(),
				"XUPTest", "Allow specific player type select", false);

	}

	@Test(description = "Create a registration condition with disallowing players with a specific ticket type ", dataProvider = "positiveValues")
	public void CreateRegistrationConditionWithAllowedSpecificPlayersTest(
			String type) {

		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());

		tableTemplates.clickNewWebAdminTab();

		CreateRegistrationConditionPage registrationConditionPage = new CreateRegistrationConditionPage(
				DriverManager.getWebDriver().get());

		registrationConditionPage.clickRegistrationConditionTab();
		registrationConditionPage.clickCreateRegistrationConditionTab();
		String name = String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION, getRandomString(5));

		registrationConditionPage.fillRegistrationConditionName(name);
		registrationConditionPage.clickCheckBoxAllowSpecificPlayer();
		registrationConditionPage.selectSpecificType(type);
		registrationConditionPage.clickCreateButton();
		registrationConditionPage.clickCloseButtonInDialogue();
		ManageRegistrationConditionPage manageRegistrationConditionPage = new ManageRegistrationConditionPage(
				DriverManager.getWebDriver().get());
		manageRegistrationConditionPage.clickManageRegistrationConditionTab();
		manageRegistrationConditionPage.fillSearchTerm(name);
		manageRegistrationConditionPage.clickEditLink();
		manageRegistrationConditionPage.milliSecWait(5000);
		Assert.assertEquals(manageRegistrationConditionPage
				.getValueOfAllowSpecificEntryType(), type,
				"Specific player type select", false);
	}

	@DataProvider(name = "positiveValues")
	public Object[][] getValues() {
		return new Object[][] { { RegistrationConstants.PLAYER_TYPE_CASH },
				{ RegistrationConstants.PLAYER_TYPE_FUN } };
	}

	@Test(description = "Negative tests for create a registration condition")
	public void CreateRegistrationConditionNegativeTest() {

		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());

		tableTemplates.clickNewWebAdminTab();

		CreateRegistrationConditionPage registrationConditionPage = new CreateRegistrationConditionPage(
				DriverManager.getWebDriver().get());

		registrationConditionPage.clickRegistrationConditionTab();
		registrationConditionPage.clickCreateRegistrationConditionTab();
		registrationConditionPage.clickCreateButton();
		Assert.assertNotNull(
				registrationConditionPage.getErrorMessageOfRegCondition(),
				"Registration Condition error message", true);
		String name = String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION, getRandomString(5));

		registrationConditionPage.fillRegistrationConditionName(name);
		registrationConditionPage.clickCheckBoxAllowSpecificPlayer();
		registrationConditionPage.clickCreateButton();
		Assert.assertNotNull(
				registrationConditionPage.getErrorMessageOfRegCondition(),
				"Registration Condition error message", true);
		registrationConditionPage.clickCheckBoxAllowPlayerEntry();
		registrationConditionPage.clickCheckBoxDisallowPlayerEntry();
		registrationConditionPage.clickCreateButton();
		Assert.assertNotNull(
				registrationConditionPage.getErrorMessageOfRegCondition(),
				"Registration Condition error message", true);
		
	}

}
