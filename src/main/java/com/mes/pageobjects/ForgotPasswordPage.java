package com.mes.pageobjects;

import com.mes.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage extends BaseClass {
    @FindBy(xpath = "//a[normalize-space()='sign in']")
    WebElement lnkLoginPage;

    public ForgotPasswordPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public LoginPage clickOnLoginPageLink() {
        lnkLoginPage.click();
        return new LoginPage();
    }
}
