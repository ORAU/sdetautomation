package com.selenium.docker.portal;

import com.selenium.docker.pages.flightreservation.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {
    @FindBy(id="monthly-earning")
    private WebElement lbl_monthly_earning;
    @FindBy(id="annual-earning")
    private WebElement lbl_annual_earning;
    @FindBy(id="profit-margin")
    private WebElement lbl_profit_margin;
    @FindBy(id="available-inventory")
    private WebElement lbl_available_inventory;
    @FindBy(css="#dataTable_filter input")
    private WebElement searchInput;
    @FindBy (id="dataTable_info")
    private WebElement numberOfSearchingResults ;
    @FindBy(css="img.img-profile")
    private WebElement btn_userLogo;
    @FindBy(linkText = "Logout")
    private WebElement btn_logoutOption;
    private final static Logger log= LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(css="#logoutModal a")
    private WebElement btn_logout;

    public DashboardPage (WebDriver driver){
       super(driver);
   }
    @Override
    public boolean isAt() {
      return this.wait.until(ExpectedConditions.visibilityOf(this.lbl_monthly_earning)).isDisplayed();

    }

    public String getMonthlyEarning(){
        return this.lbl_monthly_earning.getText();
    }
    public String getAnnualEarning(){
        return this.lbl_annual_earning.getText();
    }
    public String getAvailableInventory(){
        return this.lbl_available_inventory.getText();
    }
    public String getProfitMargin(){
        return this.lbl_profit_margin.getText();
    }

  public void  searchOrderHistoryBy(String text){
        this.searchInput.sendKeys(text);
  }
  public int getSearchResultsCount(){

        String resultsText=this.numberOfSearchingResults.getText();
        String [] words=resultsText.split(" ");
      int amount=Integer.parseInt(words[5]);
      log.info("Results count : {}",amount);
        return amount;
  }
  public void logout(){
        this.btn_userLogo.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.btn_logoutOption)).click();
        this.wait.until(ExpectedConditions.visibilityOf(this.btn_logout)).click();
  }
}
