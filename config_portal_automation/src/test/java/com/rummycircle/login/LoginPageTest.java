package com.rummycircle.login;

import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rummycircle.pageobjects.login.LoginPage;
import com.rummycircle.ui.DriverManager;
import com.rummycircle.utils.testutils.BaseTest;
import com.rummycircle.utils.testutils.PropertyReader;

public class LoginPageTest extends BaseTest {

	protected Properties config = null;
	protected LoginPage loginPage = null;

	public LoginPageTest() {
		config = PropertyReader.loadCustomProperties("config.properties");
	}

	@BeforeMethod
	void initialSetup() {
		openBaseURL();
		driver.manage().window().setSize(new Dimension(1920, 1080));

	}

	@Test(description = "Perform a successful login on config portal")
	public void loginIntoPortal() {
		LoginPage loginPage = new LoginPage(DriverManager.getWebDriver().get());

		loginPage.enterUsername(config.getProperty("config.portal.username"));
		loginPage.enterPassword(config.getProperty("config.portal.password"));
		loginPage.clickLoginButton();
		Assert.assertTrue(loginPage.isSuccessfulLogin());
	}

	@Test(description = "Negative testcases for login", dataProvider = "negativeValues")
	public void invalidLoginIntoPortal(String username, String password) {

		LoginPage loginPage = new LoginPage(driver);

		if (!username.isEmpty())
			loginPage.enterUsername(username);

		if (!password.isEmpty())
			loginPage.enterPassword(password);

		loginPage.clickLoginButton();

		if (username.isEmpty())
			Assert.assertTrue(loginPage.isUsernameEmpty());
		else if (password.isEmpty())
			Assert.assertTrue(loginPage.isPasswordEmpty());
		else
			Assert.assertTrue(loginPage.isInvalidLogin());
	}
	
	@Test
	public void errorMessageOnEmptyFields(String username , String password)
	{
		LoginPage loginPage = new LoginPage(driver);

		if (!username.isEmpty())
			loginPage.enterUsername(username);

		if (!password.isEmpty())
			loginPage.enterPassword(password);

		loginPage.clickLoginButton();

		if (username.isEmpty())
			Assert.assertTrue(loginPage.isUsernameEmpty());
			
		else if (password.isEmpty())
			Assert.assertTrue(loginPage.isPasswordEmpty());
		else
			Assert.assertTrue(loginPage.isInvalidLogin());
	}

	@DataProvider(name="negativeValues")
	public Object[][] getValues(){
		return new Object[][]{{"automation","automation"},{"automation",""},{"","automation"}};
	}
}
