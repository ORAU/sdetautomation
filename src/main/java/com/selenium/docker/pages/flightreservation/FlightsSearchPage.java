package com.selenium.docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class FlightsSearchPage extends AbstractPage{

    @FindBy(id="passengers")
    private WebElement sel_passengers_select;

    public FlightsSearchPage (WebDriver driver){
        super(driver);
    }
    @FindBy(id="search-flights")
    private WebElement sel_search_flights;

    public void select_passengers(int numberOfPassengers){
      Select selector= new Select(this.sel_passengers_select);
        selector.selectByValue(Integer.toString(numberOfPassengers));
    }
    public void searchFlights(){
        focusAndClickOnElement(this.sel_search_flights,5);
    }
    @Override
    public boolean isAt() {
        return this.wait.until(ExpectedConditions.visibilityOf(this.sel_search_flights)).isDisplayed();
    }
}
