package com.selenium.test.portal;

import com.selenium.docker.portal.DashboardPage;
import com.selenium.docker.portal.LoginPage;
import com.selenium.test.base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    @BeforeTest
    public void setupPageObjects(){

        loginPage=new LoginPage(driver);
        dashboardPage=new DashboardPage(driver);
    }

    @Test
    public void loginTest(){

        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.logIn("sam","sam");

    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){

        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),"$40,000");
        Assert.assertEquals(dashboardPage.getAnnualEarning(),"$215,000");
        Assert.assertEquals(dashboardPage.getProfitMargin(),"50%");
        Assert.assertEquals(dashboardPage.getAvailableInventory(),"18");
        dashboardPage.searchOrderHistoryBy("adams");
        Assert.assertEquals(dashboardPage.getSearchResultsCount(),8);

    }
    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){

        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
