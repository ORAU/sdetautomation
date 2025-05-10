package com.selenium.test.base;

import com.google.common.util.concurrent.Uninterruptibles;
import com.selenium.utils.Config;
import com.selenium.utils.Constants;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    private static final Logger log= LoggerFactory.getLogger(ResourceLoader.class);
    @BeforeTest
    public void callingInitializeProperties(){
        Config.initializeProperties();
    }
    @BeforeTest
    public void setup(){

        if(Boolean.getBoolean("selenium-grid-enabled")){
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
    @BeforeMethod
    public void sleepTime(){

        try {
            long sleepTime = Long.parseLong(Config.getConfigurationProperty(Constants.SLEEP_TIME));
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(sleepTime));
            log.info("Sleeping time fue configurado correctamente: {}",sleepTime);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: SLEEP_TIME debe ser un número entero válido.");
            // Opcional: Usar un valor por defecto
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
            log.info("Sleeping time no fue configurado correctamente");
        }

    }
}
