package com.selenium.test.portal;

import com.selenium.docker.portal.DashboardPage;
import com.selenium.docker.portal.LoginPage;
import com.selenium.test.base.BaseTest;
import com.selenium.test.portal.model.VendorPortalTestData;
import com.selenium.utils.Config;
import com.selenium.utils.Constants;
import com.selenium.utils.JsonUtil;
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
    private VendorPortalTestData testData;
    @BeforeTest
    @Parameters({"testDataPath"})
    public void setupPageObjects(String testDataPath){

        loginPage=new LoginPage(driver);
        dashboardPage=new DashboardPage(driver);
        testData= JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){

        loginPage.goTo(Config.getConfigurationProperty(Constants.VENDORPORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.logIn(testData.username(),testData.password());

    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){

        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),testData.MonthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(),testData.AnnualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(),testData.ProfitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),testData.AvailableInventory());
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(),testData.SearchResultsCount());

    }
    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){

        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
