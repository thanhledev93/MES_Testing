package com.mes.pageobjects;

import com.mes.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignoutPage extends BaseClass {

    @FindBy(xpath = "//a[contains(text(),'trang đăng nhập')]")
    WebElement lnkLoginPage;

    public SignoutPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public LoginPage clickOnLoginPageLink() throws InterruptedException {

        lnkLoginPage.click();
        return new LoginPage();
    }

}
