package com.selenium.docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage{
    private static final Logger log= LoggerFactory.getLogger(FlightConfirmationPage.class);
    @FindBy(css="#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    WebElement txt_price;

    @FindBy(css="#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    WebElement txt_confirmationElement;

   public FlightConfirmationPage(WebDriver driver){
       super(driver);
   }
   public String getPrice(){
       String price =this.txt_price.getText();
       log.info("The flight confirmation number is: {} and price: {} ",this.getConfirmationId(),this.txt_price.getText());
       return this.txt_price.getText();
   }
    public String getConfirmationId(){
       String confirmation=this.txt_confirmationElement.getText();
       return this.txt_confirmationElement.getText();
    }
    @Override
    public boolean isAt() {
       return this.wait.until(ExpectedConditions.visibilityOf(this.txt_confirmationElement)).isDisplayed();

    }
}
