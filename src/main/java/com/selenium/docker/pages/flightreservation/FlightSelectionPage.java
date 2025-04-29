package com.selenium.docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightOptions;

    @FindBy(id="confirm-flights")
    private WebElement confirmFlightsButton;

    public FlightSelectionPage (WebDriver driver){
        super(driver); }
    @Override
    public boolean isAt() {

        this.wait.until(ExpectedConditions.visibilityOf(this.arrivalFlightOptions.get(0)));
        return this.arrivalFlightOptions.get(0).isDisplayed();
    }
    public void selectFlights(){

        int random= ThreadLocalRandom.current().nextInt(0,this.departureFlightOptions.size());
        this.departureFlightOptions.get(random);
        this.arrivalFlightOptions.get(random);
    }
    public void confirmAction(){

        this.confirmFlightsButton.click();
    }
}
