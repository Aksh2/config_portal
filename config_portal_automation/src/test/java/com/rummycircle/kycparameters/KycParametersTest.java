package com.rummycircle.kycparameters;

import java.util.Properties;
import java.util.Random;

import org.apache.commons.math.random.RandomGenerator;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.mortbay.log.Log;
import org.openqa.selenium.Dimension;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rummycircle.login.LoginPageTest;
import com.rummycircle.pageobjects.config.kyc.ManageKycPage;
import com.rummycircle.pageobjects.login.LoginPage;
import com.rummycircle.utils.testutils.BaseTest;
import com.rummycircle.utils.testutils.PropertyReader;

public class KycParametersTest extends BaseTest {
	protected Properties config = null;
	protected Logger log = null;
	
	public KycParametersTest() {
		config = PropertyReader.loadCustomProperties("config.properties");
		log = Logger.getLogger(KycParametersTest.class);
	}

	@BeforeMethod
	void initialSetup() {
		openBaseURL();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername(config.getProperty("config.portal.username"));
		loginPage.enterPassword(config.getProperty("config.portal.password"));
		loginPage.clickLoginButton();
	}

	@Test(enabled=false,description="Tests the KYC verfication Go-Live parameter flow")
	public void testKycGoLiveParameter() throws InterruptedException{
		ManageKycPage kycPage = new ManageKycPage(driver);
		kycPage.clickMangeKycTab();
		kycPage.clickEditParametersButton();
		kycPage.clickLatestUserIdButton();
		kycPage.clickCalendarButton();
		kycPage.clickOnASpecificDay("11");
		kycPage.clickOnUpArrowOfHourField();
		kycPage.clickOnDownArrowOfHourField();
		kycPage.clickOnUpArrowOfMinField();
		kycPage.clickOnDownArrowOfMinField();
		kycPage.clickSaveParmetersButton();
		Thread.sleep(5000);
			
	}
	
	@Test(enabled=true,description="Tests the working of cancel button in the Save parameters flow")
	public void testCancelParameters(){
		ManageKycPage kycPage = new ManageKycPage(driver);
		kycPage.clickMangeKycTab();
		kycPage.clickEditParametersButton();
		kycPage.clickCancelButtonInKycGoLiveParameters();
		Assert.assertTrue(kycPage.isSaveParameterButtonPresent());
	
		
	}
	
	@Test(enabled=false,description="Tests the A/B Test Requirements flow")
	public void testKycABRequirements() throws InterruptedException{
		Random random = new Random();
		int modPaths = random.nextInt(5)+1;
		
		
		ManageKycPage kycPage = new ManageKycPage(driver);
		kycPage.clickMangeKycTab();
		kycPage.clickEditAbParmetersButton();
		log.info(String.format("No.of mod paths:%s", modPaths));
		kycPage.setModPaths(String.valueOf(modPaths));
		kycPage.clickSetButton();
		for(int index=0;index<modPaths;index++){
			int randomAmount=random.nextInt(10000)+1;
			log.info(String.format("Path:%s=%s", index+1,randomAmount));
			kycPage.fillABPaths(String.valueOf(index+1), String.valueOf(randomAmount));
		}
		
		kycPage.clickSaveABButton();
		Assert.assertTrue(kycPage.isDetailsSavedSuccessfully());
		
				
		
	}
	
	@Test(enabled=false,description="Cancel editing the KYC A/B parameters")
	public void testCancelABParameterEdit() throws InterruptedException{
		
		ManageKycPage kycPage = new ManageKycPage(driver);
		kycPage.clickMangeKycTab();
		kycPage.clickEditAbParmetersButton();
		kycPage.clickCancelButtonInABTestRequirements();
		Assert.assertTrue(kycPage.isEditButtonPresent());
		
	}

}
