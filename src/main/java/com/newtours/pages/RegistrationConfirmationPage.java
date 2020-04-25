package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends BasePage{
    @FindBy(partialLinkText = "sign-in")
    private WebElement signingLink;

    @FindBy(linkText = "http://newtours.demoaut.com/mercuryreservation.php")
    private WebElement flightsLink;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public void goToFlightDetailsLinkPage(){
        wait.until(ExpectedConditions.visibilityOf(signingLink));
        flightsLink.click();
    }
}
