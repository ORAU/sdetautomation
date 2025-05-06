package com.selenium.test.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        this.driver=new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @AfterTest
    public void endingTest() {

        this.driver.quit();
    }
}
