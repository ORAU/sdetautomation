package com.selenium.test.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setup(){

        if(Boolean.getBoolean("selenium.grid.enabled")){
            this.driver=remoteSetup();
        }else{
            this.driver=setupLocalWebdriver();
        }
        this.driver.manage().window().maximize();
    }

    /**
     * Codigo para ejecutar selenium grid
     * **/
    private WebDriver remoteSetup()  {

        Capabilities capabilities;
        if(System.getProperty("browser").equalsIgnoreCase("chrome")){
            capabilities=new ChromeOptions();
        }
        else{
            capabilities=new FirefoxOptions();
        }
        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Codigo para crear un Chrome Driver*/
    private WebDriver setupLocalWebdriver(){
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        return driver;
    }
    @AfterTest
    public void endingTest() {

        this.driver.quit();
    }
}
