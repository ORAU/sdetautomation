package com.selenium.docker.portal;

import com.selenium.docker.pages.flightreservation.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {


    @FindBy(id="login")
    private WebElement btn_login;
    @FindBy(id="username")
    private WebElement txt_username;
    @FindBy(id="password")
    private WebElement txt_password;

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    public void goTo(String url){
        this.driver.get(url);
    }
    public void logIn(String username,String password){
        this.txt_username.sendKeys(username);
        this.txt_password.sendKeys(password);
        this.btn_login.click();
    }
    @Override
    public boolean isAt() {
        return this.wait.until(ExpectedConditions.visibilityOf(this.btn_login)).isDisplayed();
    }
}
