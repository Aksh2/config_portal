package com.rummycircle.newwebadmin.branding;

import java.util.Properties;






import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rummycircle.pageobjects.login.LoginPage;
import com.rummycircle.pageobjects.newwebadmin.CreateTableTemplatesPage;
import com.rummycircle.pageobjects.newwebadmin.TemplateConstants;
import com.rummycircle.pageobjects.newwebadmin.createbranding.CreateBrandingPage;
import com.rummycircle.pageobjects.newwebadmin.createbranding.ManageBrandingPage;
import com.rummycircle.ui.DriverManager;
import com.rummycircle.utils.testutils.Assert;
import com.rummycircle.utils.testutils.NewBaseTest;
import com.rummycircle.utils.testutils.PropertyReader;

public class BrandingTest extends NewBaseTest {
	
	protected Properties config = null;
	protected Properties custom = null;
	protected Logger log = null;

	public BrandingTest() {
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
	
	@Test(description = "Create a branding")
	public void createBrandingTest() {
		
		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		tableTemplates.clickNewWebAdminTab();
		
		CreateBrandingPage brandingPage = new CreateBrandingPage(
				DriverManager.getWebDriver().get());
		String name = String.format("%s_%s",
				TemplateConstants.TABLE_PREFIX_AUTOMATION,
				getRandomString(5));
		brandingPage.clickBrandingTab();
		brandingPage.clickCreateBrandingTab();
		brandingPage.fillBrandingName(name);
		String brandingDetails = getRandomString(15);
		brandingPage.fillTournamentDetails(brandingDetails);
		brandingPage.clickBrandingTournamentPreviewLink();
		Assert.assertEquals(brandingPage.getTextOfBrandingPreview(), brandingDetails, "Branding Details", false);
		brandingPage.clickCloseButton();
		brandingPage.fillMobileTournamentDetails(custom.getProperty("image.src"));
		brandingPage.clickMobileTournamentPreviewLink();
		brandingPage.clickCloseButton();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		brandingPage.clickCreateButton();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		brandingPage.clickCloseButtonInCreateBrandingDialogue();
		brandingPage.clickManageBrandingTab();
		// Search for the created branding and check the details in the preview link
		ManageBrandingPage manageBrandingPage = new ManageBrandingPage(DriverManager.getWebDriver().get());
		manageBrandingPage.fillSearchTerm(name);
		manageBrandingPage.clickManageBrandingPreviewLink();
		Assert.assertEquals(manageBrandingPage.getTextOfBrandingPreview(),brandingDetails ,"Manage Branding Details",false);
		manageBrandingPage.clickCloseButton();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manageBrandingPage.clickManageBrandingEditLink();
		Assert.assertEquals(manageBrandingPage.getValueOfBrandingNameInput(),name ,"Branding Name",false);
		Assert.assertEquals(manageBrandingPage.getValueOfBrandingDetailsInput(),brandingDetails ,"Branding Details",false);
		Assert.assertEquals(manageBrandingPage.getValueOfMobileBrandingDetailsInput(),custom.getProperty("image.src") ,"Mobile Branding Details",false);
		
	}
	
	@Test(description = "Negative tests of creating a branding",dataProvider="negativeValues")
	public void createBrandingNegativeTests(String name,String tournamentDetails) {
		
		CreateTableTemplatesPage tableTemplates = new CreateTableTemplatesPage(
				DriverManager.getWebDriver().get());
		tableTemplates.clickNewWebAdminTab();
		
		CreateBrandingPage brandingPage = new CreateBrandingPage(
				DriverManager.getWebDriver().get());
		
		brandingPage.clickBrandingTab();
		brandingPage.clickCreateBrandingTab();
		brandingPage.fillBrandingName(name);
		brandingPage.fillTournamentDetails(tournamentDetails);
		
		
		brandingPage.clickCreateButton();
		Assert.assertNotNull(brandingPage.getErrorMessageOfBranding(), "Error Message", true);
		
		brandingPage.clickCloseButtonInCreateBrandingDialogue();
	
		
		
	}
	
	@DataProvider(name="negativeValues")
	public Object[][] getValues(){
		return new Object[][]{{"",getRandomString(5)},{getRandomString(5),""}};
	}


	


}
