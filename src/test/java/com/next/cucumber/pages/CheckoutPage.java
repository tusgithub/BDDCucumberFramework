package com.next.cucumber.pages;

import com.next.cucumber.utilities.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    public WebDriver driver;

    By cartBag = By.xpath("//img[@alt='Cart']");
    By checkOutButton = By.xpath("//button[contains(text(), 'PROCEED TO CHECKOUT')]");
    By promoBtn = By.className("promoBtn");
    By placeOrder = By.xpath("//button[contains(text(), 'Place Order')]");
    By overlay = By.cssSelector(".blockUI.blockOverlay");

    public CheckoutPage() {
    }

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void CheckoutItems() {
        driver.findElement(cartBag).click();
        driver.findElement(checkOutButton).click();
    }

    public Boolean VerifyPromoBtn() {
        return driver.findElement(promoBtn).isDisplayed();
    }

    public Boolean VerifyPlaceOrder() {
        return driver.findElement(placeOrder).isDisplayed();
    }

}
