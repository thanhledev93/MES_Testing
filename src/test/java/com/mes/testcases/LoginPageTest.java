/**
 * 
 */
package com.mes.testcases;
import com.mes.dataprovider.DataProviders;
import com.mes.pageobjects.ForgotPasswordPage;
import com.mes.pageobjects.SignoutPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mes.base.BaseClass;
import com.mes.pageobjects.HomePage;
import com.mes.pageobjects.LoginPage;
import com.mes.utility.Log;

/**
 * @author Hitendra
 *
 */
public class LoginPageTest extends BaseClass {

	private LoginPage loginPage;
	private HomePage homePage;
	private SignoutPage signOutPage;
	private ForgotPasswordPage forgotPasswordPage;
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}


	//Validate logging into the Application using valid credentials
	@Test(groups = {"Smoke"}, dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void TC_LF_001(String uname, String pswd) throws Throwable {

		Log.startTestCase("TC_LF_001");
		loginPage = new LoginPage();

		Log.info("Enter Username and Password");
		homePage=loginPage.login(uname, pswd, homePage);

	    Log.info("Verifying if user is able to login");
	    Assert.assertEquals(homePage.getCurrentUser(), uname);
	    Log.info("Login is Success");

	    Log.endTestCase("TC_LF_001");
	}

	//Validate logging into the Application using invalid credentials (i.e. Invalid username and Invalid Password)
	// TC_LF_002 --> TC_LF_005
	@Test(groups = {"Smoke"}, dataProvider = "invalid_credentials", dataProviderClass = DataProviders.class)
	public void TC_LF_002(String uname, String pswd, String verifyMessage) {

		Log.startTestCase("TC_LF_002");
		loginPage = new LoginPage();

		Log.info("Enter Username and Password");
		loginPage.login(uname, pswd);

		Log.info("Verifying warning message should be displayed");
		Assert.assertEquals(loginPage.getWarningMessage(), verifyMessage);

		Log.endTestCase("TC_LF_002");
	}

	// Validate 'Forgotten Password' link is available in the Login page and is working
	@Test(groups = {"Smoke"})
	public void TC_LF_006() {

		Log.startTestCase("TC_LF_006");
		loginPage = new LoginPage();

		Log.info("Verifying 'Forgotten Password' link should be displayed");
		Assert.assertTrue(loginPage.validateForgotPasswordLink());

		Log.endTestCase("TC_LF_006");
	}

	// Validate Logging into the Application and browsing back using Browser back button
	@Test(groups = {"Smoke"}, dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void TC_LF_009(String uname, String pswd) throws InterruptedException {

		Log.startTestCase("TC_LF_009");
		loginPage = new LoginPage();

		Log.info("Enter Username and Password");
		loginPage.login(uname, pswd, homePage);

		Thread.sleep(1000);

		Log.info("Click on Browser back button");
		getDriver().navigate().back();

		Log.info("Verifying browsing back");
		Assert.assertEquals(homePage.getCurrentUrl(), "https://quanlysanxuat.online/home");

		Log.endTestCase("TC_LF_009");
	}

	// Validate Loggingout from the Application and browsing back using Browser back button
	@Test(groups = {"Smoke"}, dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void TC_LF_010(String uname, String pswd) throws InterruptedException {

		Log.startTestCase("TC_LF_009");
		loginPage = new LoginPage();

		Log.info("Enter Username and Password");
		homePage = loginPage.login(uname, pswd, homePage);

		Log.info("Click on 'My Account' icon and select 'Signout' option");
		loginPage = homePage.signOut(6000);

		Log.info("Click on Browser back button");
		getDriver().navigate().back();

		Log.info("Verifying browsing back");
		Assert.assertEquals(loginPage.getHeader(), "Đăng nhập");

		Log.endTestCase("TC_LF_010");
	}

	// Validate the text into the Password field is toggled to hide its visibility
	@Test(groups = {"Smoke"})
	public void TC_LF_013() throws InterruptedException {

		Log.startTestCase("TC_LF_013");
		loginPage = new LoginPage();

		Log.info("Enter Password");
		loginPage.enterPassword();

		Log.info("Verifying Password field should be toggled to hide its visibility");
		Assert.assertTrue(loginPage.getTypeAsPassword());

		Log.endTestCase("TC_LF_013");
	}

	// Validate the Password is not visible in the Page Source
	@Test(groups = {"Smoke"})
	public void TC_LF_015() throws InterruptedException {

		Log.startTestCase("TC_LF_015");
		loginPage = new LoginPage();

		Log.info("Enter password");
		loginPage.enterPassword();

		Log.info("Find element in page source");
		boolean result =  loginPage.findElementInPageSource("testData");

		Log.info("Verifying Password text should not be visible in the Page source");
		Assert.assertFalse(result);

		Log.endTestCase("TC_LF_015");
	}

	// Validate all the below ways of navigating to the Login page
	// Way 1 - Click on 'Sign in' link in the 'Forgot Password' page
	@Test(groups = {"Smoke"})
	public void TC_LF_019_1() throws InterruptedException {

		Log.startTestCase("TC_LF_019_1");
		loginPage = new LoginPage();


		Log.info("Click on Forgot Password link");
		forgotPasswordPage = loginPage.clickOnForgotPasswordLink();

		Log.info("Click on Login link");
		loginPage = forgotPasswordPage.clickOnLoginPageLink();

		Log.info("Verifying User should be navigated to 'Login' page");
		Assert.assertEquals(loginPage.getHeader(), "Đăng nhập");

		Log.endTestCase("TC_LF_019_1");
	}


	// Validate all the below ways of navigating to the Login page
	// Way 2 - Click on 'Trang đăng nhập' link in the 'Signout' page
	@Test(groups = {"Smoke"}, dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void TC_LF_019_2(String uname, String pswd) throws InterruptedException {

		Log.startTestCase("TC_LF_019_2");
		loginPage = new LoginPage();

		Log.info("Enter Username and Password");
		homePage = loginPage.login(uname, pswd, homePage);


		Log.info("Click on 'My Account' icon and select 'Signout' option");
		signOutPage = homePage.signOut();

		Log.info("Click on 'Login Page link");
		loginPage = signOutPage.clickOnLoginPageLink();

		Log.info("Verifying User should be navigated to 'Login' page");
		Assert.assertEquals(loginPage.getHeader(), "Đăng nhập");

		Log.endTestCase("TC_LF_019_2");
	}


		// Validate the Password is not visible in the Page Source
	@Test(groups = {"Smoke"})
	public void TC_LF_020() {

		Log.startTestCase("TC_LF_020");
		loginPage = new LoginPage();

		Log.info("Get page heading");
		boolean actHeading = loginPage.getHeader().equals("Đăng nhập");

		Log.info("Get page url");
		boolean actUrl = loginPage.getCurrentUrl().equals("https://quanlysanxuat.online/sign-in");

		Log.info("Verifying Password text should not be visible in the Page source");
		Assert.assertTrue(actHeading && actUrl);

		Log.endTestCase("TC_LF_020");
	}
}
