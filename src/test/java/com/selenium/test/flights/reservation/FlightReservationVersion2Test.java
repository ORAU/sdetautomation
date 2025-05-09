package com.selenium.test.flights.reservation;

import com.selenium.docker.pages.flightreservation.*;
import com.selenium.test.base.BaseTest;
import com.selenium.test.base.BaseTestRefactorized;
import com.selenium.test.flights.reservation.model.FlightReservationTestData;
import com.selenium.test.portal.model.VendorPortalTestData;
import com.selenium.utils.Config;
import com.selenium.utils.Constants;
import com.selenium.utils.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationVersion2Test extends BaseTestRefactorized {

    private String expectedPrice;
    private FlightReservationTestData testData;
    @BeforeTest
    @Parameters({"testDataPath"})
    public void setDriver(String testDataPath){
          testData= JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }
    @Test
    public void userRegistrationTest(){

        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.goTo(Config.getConfigurationProperty(Constants.FLIGHTRESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(testData.firstName(),testData.lastName());
        registrationPage.enterCredentials(testData.email(),testData.password());
        registrationPage.enterAddress(testData.street(),testData.city(),testData.zip());
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
    flightsSearchPage.select_passengers( testData.passengersCount());
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
    Assert.assertEquals(flightConfirmationPage.getPrice(),testData.expectedPrice());
}

}
