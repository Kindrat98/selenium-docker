package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightsDetailsPage extends BasePage {

    @FindBy(name = "passCount")
    private WebElement passengers;

    @FindBy(name = "findFlights")
    private WebElement submitBtn;

    public FlightsDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void selectPassengers(String numOfPassengers ){
        wait.until(ExpectedConditions.elementToBeClickable(passengers));
        Select select = new Select(passengers);
        select.selectByValue(numOfPassengers);
    }

    public void goToFindFlightsPage(){
        submitBtn.click();
    }
}
