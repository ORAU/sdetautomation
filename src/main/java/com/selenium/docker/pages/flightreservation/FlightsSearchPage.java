package com.selenium.docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightsSearchPage extends AbstractPage{

    @FindBy(id="passengers")
    private WebElement sel_passengers_select;

    public FlightsSearchPage (WebDriver driver){
        super(driver);
    }
    @FindBy(id="search-flights")
    private WebElement sel_search_flights;

    public void select_passengers(String numberOfPassengers){
      Select selector= new Select(this.sel_passengers_select);
        selector.selectByValue(numberOfPassengers);
    }
    public void searchFlights(){
        this.sel_search_flights.click();

    }
    @Override
    public boolean isAt() {
        return this.wait.until(ExpectedConditions.visibilityOf(this.sel_search_flights)).isDisplayed();
    }
}
