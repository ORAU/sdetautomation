package com.selenium.docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationConfirmationPage {
    private WebDriver driver;
    @FindBy(id="go-to-flights-search")
    private WebElement btn_goToFlightSearch;

    public  RegistrationConfirmationPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void userRegistrationConfirmation(){
        this.btn_goToFlightSearch.click();
    }
}

