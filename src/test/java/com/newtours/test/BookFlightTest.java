package com.newtours.test;

import com.newtours.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTest extends BaseTest {

    private String noOfPassengers;
    private String expectedPrice;


    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setupParams(String noOfPassengers, String expectedPrice) {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void registrationPageTest() {
        RegistrationPage registrationPage = new RegistrationPage(driverThreadLocal.get());
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCredentials("selenium", "docker");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPageTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driverThreadLocal.get());
        registrationConfirmationPage.goToFlightDetailsLinkPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPageTest")
    public void flightsDetailsPage() {
        FlightsDetailsPage flightsDetailsPage = new FlightsDetailsPage(driverThreadLocal.get());
        flightsDetailsPage.selectPassengers(noOfPassengers);
        flightsDetailsPage.goToFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightsDetailsPage")
    public void findFlightPage() {
        FindFlightPage findFlightPage = new FindFlightPage(driverThreadLocal.get());
        findFlightPage.submitFindFlightPage();
        findFlightPage.goToFlightConfirmationPage();
    }

    @Test(dependsOnMethods = "findFlightPage")
    public void flightConfirmationPage() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driverThreadLocal.get());
        String actualPrice = flightConfirmationPage.getPrice();
        Assert.assertEquals(actualPrice, expectedPrice);
    }
}
