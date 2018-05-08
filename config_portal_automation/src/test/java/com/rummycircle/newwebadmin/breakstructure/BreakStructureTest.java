package com.rummycircle.newwebadmin.breakstructure;


import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rummycircle.pageobjects.login.LoginPage;
import com.rummycircle.pageobjects.newwebadmin.breakstructure.CreateBreakStructurePage;
import com.rummycircle.pageobjects.newwebadmin.breakstructure.ManageBreakStructurePage;
import com.rummycircle.pageobjects.newwebadmin.createtables.CreateTableTemplatesPage;
import com.rummycircle.pageobjects.newwebadmin.createtables.TemplateConstants;
import com.rummycircle.ui.DriverManager;
import com.rummycircle.utils.testutils.Assert;
import com.rummycircle.utils.testutils.NewBaseTest;
import com.rummycircle.utils.testutils.PropertyReader;

public class BreakStructureTest extends NewBaseTest {
	
	protected Properties config = null;
	protected Properties custom = null;
	protected Logger log = null;

	public BreakStructureTest() {
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
	
	@Test(description="Create a break structure with all the necessary fields")
	public void CreatebreakStructureTest(){
		
		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		tableTemplates.clickNewWebAdminTab();
		
		CreateBreakStructurePage breakStructurePage = new CreateBreakStructurePage(DriverManager.getWebDriver().get());
		breakStructurePage.clickBreakStructureTab();
		breakStructurePage.clickCreateBreakStructureTab();
		String name = String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION,
				getRandomString(5));
		
		breakStructurePage.fillBreakStructureName(name);
		String[] breakStructure = new String[11];
		
		for(int index=0;index<=breakStructure.length-1;index++){
			breakStructure[index]=String.valueOf(generateRandomNumber(2));
		}
		breakStructurePage.fillBreakStructureValue(breakStructure);
		breakStructurePage.clickCreateButton();
		breakStructurePage.clickCloseButton();
		ManageBreakStructurePage manageBreakStructurePage = new ManageBreakStructurePage(DriverManager.getWebDriver().get());
		manageBreakStructurePage.clickManageBreakStructureTab();
		manageBreakStructurePage.fillSearchTerm(name);
		manageBreakStructurePage.clickEditLink();
		Assert.assertEquals(manageBreakStructurePage.getValueOfBreakStructureName(), name,"Name of break structure",false);
		int index=0;
		for(String breakValue : manageBreakStructurePage.getBreakStructureValues()){
			Assert.assertEquals(breakValue, breakStructure[index++],"Time between between rounds",false);
		}
		
	}
	
	@Test(description="Negative tests for creating a break structure")
	public void CreatebreakStructureNegativeTest(){
		
		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		tableTemplates.clickNewWebAdminTab();
		
		CreateBreakStructurePage breakStructurePage = new CreateBreakStructurePage(DriverManager.getWebDriver().get());
		breakStructurePage.clickBreakStructureTab();
		breakStructurePage.clickCreateBreakStructureTab();
		breakStructurePage.fillBreakStructureName("");
		breakStructurePage.clickCreateButton();
		Assert.assertNotNull(breakStructurePage.getErrorMessageOfBreakStructure(), "Break Structure name", true);
		breakStructurePage.clickHideButton();
		breakStructurePage.fillBreakStructureName(getRandomString(5));
		breakStructurePage.fillBreakStructureValue(new String[]{""});
		Assert.assertNotNull(breakStructurePage.getErrorMessageOfBreakStructure(), "Break Structure value", true);
		breakStructurePage.clickCreateButton();
	
		
	}
	 


}
