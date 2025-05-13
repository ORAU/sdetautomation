package com.selenium.test.base;

import com.selenium.listener.TestListener;
import com.selenium.utils.Config;
import com.selenium.utils.Constants;
import com.selenium.utils.ResourceLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

@Listeners({TestListener.class})
public abstract class BaseTestRefactorized {

    protected WebDriver driver;
    private static final Logger log= LoggerFactory.getLogger(ResourceLoader.class);
   @BeforeSuite
   public void callingInitializeProperties(){
       Config.initializeProperties();
   }
    @BeforeTest
    public void setup(ITestContext context){

        //Boolean.getBoolean("selenium.grid.enabled"))
        //this.driver=(Boolean.getBoolean(Constants.SELENIUM_GRID_ENABLED))? remoteSetup() : setupLocalWebdriver();
        if(Boolean.getBoolean(Constants.SELENIUM_GRID_ENABLED)){
            log.info("Iniciando metodo: remoteSetup");
            this.driver=remoteSetup();
            log.info("Se ejecutó: remoteSetup");
        }else{
            this.driver=setupLocalWebdriver();
            log.info("Se ejecutó: setupLocalWebdriver");
        }
        this.driver.manage().window().maximize();
//Storing current driver
    context.setAttribute(Constants.DRIVER,this.driver);
    }

    /**
     * Codigo para ejecutar selenium grid
     * **/
    private WebDriver remoteSetup()  {

        Capabilities capabilities;
        //comparar si la propiedad navegador es una en especifico

//        log.info("ChromeOptions default creado");
//        if(Config.getConfigurationProperty(Constants.BROWSER).equalsIgnoreCase(Constants.FIREFOX)){
//            capabilities=new FirefoxOptions();
//            log.info("Navegador firefox creado");
//        }
//        else{
//            capabilities=new EdgeOptions();
//            log.info("Navegador edge creado");
//        }
        switch(Config.getConfigurationProperty(Constants.BROWSER))
        {
            case Constants.CHROME:
                capabilities=new ChromeOptions();
                log.info("Navegador Chrome creado");
                break;
            case Constants.FIREFOX:
                capabilities=new FirefoxOptions();
                log.info("Navegador firefox creado");
                break;
            case Constants.EDGE:
            capabilities=new EdgeOptions();
            log.info("Navegador edge creado");
            break;
            default:
                capabilities=new ChromeOptions();

        }
        try {
            String urlFormat= Config.getConfigurationProperty(Constants.SELENIUM_GRID_URLFORMAT);
            String host= Config.getConfigurationProperty(Constants.SELENIUM_GRID_HUBHOST);
            String finalGridURL= String.format(urlFormat,host);
            log.info("La url generada del Grid HUB es: {}",finalGridURL);
            return new RemoteWebDriver(new URL(finalGridURL),capabilities);

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
