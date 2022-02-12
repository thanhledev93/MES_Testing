/**
 * 
 */
package com.mes.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mes.actiondriver.Action;
import com.mes.base.BaseClass;

/**
 * @author Hitendra
 *  
 */
public class LoginPage extends BaseClass {
	
	Action action= new Action();

	@FindBy(xpath = "//div[contains(text(),'Đăng nhập')]")
	WebElement lblHeader;

	@FindBy(xpath = "//input[@id='username']")
	private WebElement userName;
	
	@FindBy(xpath = "//input[@id='password']")
	private WebElement password;

	@FindBy(xpath = "//form/button")
	private WebElement signInBtn;
	
	@FindBy(xpath = "//fuse-alert[1]/div[1]/div[1]/div[2]")
	WebElement warningMessage;

	@FindBy(xpath = "//a[contains(text(),'Quên mật khẩu?')]")
	WebElement lnkForgotPassword;

	@FindBy(xpath = "//auth-sign-in[1]/div[1]/div[1]/div[1]/form[1]/mat-form-field[2]/div[1]/div[1]/div[2]/button[1]")
	WebElement viewPassword;

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public String getHeader() {
		return lblHeader.getText();
	}

	public HomePage login(String uname, String pswd, HomePage homePage) throws InterruptedException {
		action.scrollByVisibilityOfElement(getDriver(), userName);
		action.type(userName, uname);
		action.type(password, pswd);
		action.JSClick(getDriver(), signInBtn);
		Thread.sleep(2000);
		homePage = new HomePage();
		return homePage;
	}
	public void login(String uname, String pswd) {
		action.scrollByVisibilityOfElement(getDriver(), userName);
		action.type(userName, uname);
		action.type(password, pswd);
		action.JSClick(getDriver(), signInBtn);
	}

	public String getWarningMessage() {
		return this.warningMessage.getText();
	}

	public ForgotPasswordPage clickOnForgotPasswordLink() {

		lnkForgotPassword.click();
		return new ForgotPasswordPage();
	}

	public boolean validateForgotPasswordLink() {
		return action.isDisplayed(getDriver(), lnkForgotPassword);
	}

	public boolean getTypeAsText() {
		return password.getAttribute("type").equals("text");
	}
	public boolean getTypeAsPassword() {
		return password.getAttribute("type").equals("password");
	}

	public void clickOnViewPassword() {
		viewPassword.click();
	}

	public void enterPassword() {
		action.type(password, "testData");
	}

	public boolean findElementInPageSource(String text) {
		return getDriver().getPageSource().contains(text);
	}

	public String getCurrentUrl() {
		return getDriver().getCurrentUrl();
	}
	
}






