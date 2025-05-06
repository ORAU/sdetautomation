package com.selenium.test.portal.model;

public record VendorPortalTestData(String username,
                                   String password,
                                   String MonthlyEarning,
                                   String AnnualEarning,
                                   String ProfitMargin,
                                   String AvailableInventory,
                                   String searchKeyword,
                                   int SearchResultsCount) {
}
/*
public class VendorPortalTestData {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    protected String username;
    protected String password;
    protected String MonthlyEarning;
    protected String AnnualEarning;
    protected String ProfitMargin;
    protected String AvailableInventory;
    protected String searchKeyword;
    protected String SearchResultsCount;
    public VendorPortalTestData() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMonthlyEarning() {
        return MonthlyEarning;
    }

    public void setMonthlyEarning(String monthlyEarning) {
        MonthlyEarning = monthlyEarning;
    }

    public String getAnnualEarning() {
        return AnnualEarning;
    }

    public void setAnnualEarning(String annualEarning) {
        AnnualEarning = annualEarning;
    }

    public String getProfitMargin() {
        return ProfitMargin;
    }

    public void setProfitMargin(String profitMargin) {
        ProfitMargin = profitMargin;
    }

    public String getAvailableInventory() {
        return AvailableInventory;
    }

    public void setAvailableInventory(String availableInventory) {
        AvailableInventory = availableInventory;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchResultsCount() {
        return SearchResultsCount;
    }

    public void setSearchResultsCount(String searchResultsCount) {
        SearchResultsCount = searchResultsCount;
    }


}*/