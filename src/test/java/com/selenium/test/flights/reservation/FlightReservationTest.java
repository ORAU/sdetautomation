package com.selenium.test.flights.reservation;

import com.selenium.docker.pages.flightreservation.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest {
    private WebDriver driver;
    private String passengers;
    private String expectedPrice;
    @BeforeTest
    @Parameters({"passengers","expectedPrice"})
    public void setDriver(String passengers, String expectedPrice){
        this.expectedPrice=expectedPrice;
        this.passengers=passengers;
        WebDriverManager.chromedriver().setup();
        this.driver=new ChromeDriver();
        this.driver.manage().window().maximize();
    }
    @Test
    public void userRegistrationTest(){

        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails("selenium","docker");
        registrationPage.enterCredentials("selenium@test.com","docker");
        registrationPage.enterAddress("123 street","Atlanta","3001");
        registrationPage.confirmRegistration();
    }
@Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
    RegistrationConfirmationPage registrationConfirmationPage=new RegistrationConfirmationPage(driver);
    Assert.assertTrue(registrationConfirmationPage.isAt());
    registrationConfirmationPage.userRegistrationConfirmation();
    }

@Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
        FlightsSearchPage flightsSearchPage=new FlightsSearchPage(driver);
    Assert.assertTrue(flightsSearchPage.isAt());
    flightsSearchPage.select_passengers( this.passengers);
    flightsSearchPage.searchFlights();

}

@Test(dependsOnMethods = "flightSearchTest")
    public void flightsSelectionTest(){
    FlightSelectionPage flightSelectionPage=new FlightSelectionPage(driver);
    Assert.assertTrue(flightSelectionPage.isAt());
    flightSelectionPage.selectFlights();
    flightSelectionPage.confirmAction();
}
@Test(dependsOnMethods ="flightsSelectionTest" )
public void flightReservationConfirmationTest(){
    FlightConfirmationPage flightConfirmationPage=new FlightConfirmationPage(driver);
    Assert.assertTrue(flightConfirmationPage.isAt());
    Assert.assertEquals(flightConfirmationPage.getPrice(),this.expectedPrice);
}

@AfterTest
    public void tearDown(){

        this.driver.quit();
}

}
