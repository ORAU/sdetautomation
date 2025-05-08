package com.selenium.test.base;

import com.selenium.utils.ResourceLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {

    protected WebDriver driver;
    private static final Logger log= LoggerFactory.getLogger(ResourceLoader.class);
    @BeforeTest
    public void setup(){

        if(Boolean.getBoolean("selenium.grid.enabled")){
            this.driver=remoteSetup();
            log.info("Se ejecutó: remoteSetup");
        }else{
            this.driver=setupLocalWebdriver();
            log.info("Se ejecutó: setupLocalWebdriver");
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
            log.info("Navegador chrome creado");
        }
        else{
            capabilities=new FirefoxOptions();
            log.info("Navegador firefox creado");
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
