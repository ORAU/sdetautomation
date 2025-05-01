package com.selenium.docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    public AbstractPage(WebDriver driver){
    this.driver=driver;
    this.wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);
    }

    public abstract boolean isAt();
    public void focusAndClickOnElement(WebElement element,long seconds){
        Actions actions = new Actions(this.driver,Duration.ofSeconds(seconds));
        actions.moveToElement(element).perform();
        actions.click(element).perform();
    }

}
