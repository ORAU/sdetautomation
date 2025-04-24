package com.selenium.docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private WebDriver driver;
    @FindBy(id="firstName")
    private WebElement txt_firstName;
    @FindBy(id="lastName")
    private WebElement txt_lastName;
    @FindBy(id="email")
    private WebElement txt_email;
    @FindBy(id="password")
    private WebElement txt_password;
    @FindBy(name="street")
    private WebElement txt_street;
    @FindBy(name="city")
    private WebElement txt_city;
    @FindBy(name="zip")
    private WebElement txt_zip;
    @FindBy(id="registration-btn")
    private WebElement btn_registration;

    public RegistrationPage(WebDriver driver)
    {

        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

 public void goTo(String url){

     this.driver.get(url);
 }
 public void enterUserDetails(String firstName,String lastName){

        this.txt_firstName.sendKeys(firstName);
        this.txt_lastName.sendKeys(lastName);

 }

 public void enterCredentials(String email,String password){
        this.txt_email.sendKeys(email);
        this.txt_password.sendKeys(password);
 }

 public void enterAddress(String street,String city,String zip){
      this.txt_street.sendKeys(street);
      this.txt_city.sendKeys(city);
      this.txt_zip.sendKeys(zip);
 }
 public void confirmRegistration(){
        this.btn_registration.click();
 }
}
