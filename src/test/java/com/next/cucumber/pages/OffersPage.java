package com.next.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OffersPage {

    public WebDriver driver;
    By offerSearch = By.xpath("//input[@id='search-field']");
    By productName = By.cssSelector("tr td:nth-child(1)");

    public OffersPage(){
    }

    public OffersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchItem(String name) {
        driver.findElement(offerSearch).sendKeys(name);
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }
}
