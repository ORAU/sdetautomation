package com.selenium.docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage{
    private WebDriver driver;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.btn_goToFlightSearch));
        return this.btn_goToFlightSearch.isDisplayed();
    }

    @FindBy(id="go-to-flights-search")
    private WebElement btn_goToFlightSearch;

    public  RegistrationConfirmationPage(WebDriver driver){

        super(driver);
    }

    public void userRegistrationConfirmation(){
        this.btn_goToFlightSearch.click();
    }
}

