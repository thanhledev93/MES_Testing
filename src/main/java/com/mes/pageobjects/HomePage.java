package com.mes.pageobjects;

import com.mes.actiondriver.Action;
import com.mes.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseClass {
    Action action= new Action();

    @FindBy(xpath = "//fuse-vertical-navigation/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]")
    WebElement uname;
    
    @FindBy(xpath = "//user-menu[1]/button[1]")
    WebElement btnUserMenu;

    @FindBy(xpath = "//body/div[2]/div[2]/div[1]/div[1]/div[1]/button[6]")
    WebElement btnSignOut;

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public String getCurrentUser() {
        return uname.getText();
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public LoginPage signOut(int time) throws InterruptedException {
        action.click(getDriver(), btnUserMenu);
        action.JSClick(getDriver(), btnSignOut);

        return new LoginPage();
    }

    public SignoutPage signOut() {
        action.click(getDriver(), btnUserMenu);
        action.JSClick(getDriver(), btnSignOut);

        return new SignoutPage();
    }

}
