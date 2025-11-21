package com.next.cucumber.pageobjectmanager;

import org.openqa.selenium.WebDriver;
import com.next.cucumber.pages.*;

public class PageObjectManager {

    private WebDriver driver;

    private LandingPage landingPage;
    private OffersPage offersPage;
    private CheckoutPage checkoutPage;

    public PageObjectManager(){
    }

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public OffersPage getOffersPage() {
        offersPage = new OffersPage(driver);
        return offersPage;
    }

    public LandingPage getLandingPage() {
        return (landingPage == null) ? landingPage = new LandingPage(driver) : landingPage;
//        landingPage = new LandingPage(driver);
//        return landingPage;
    }

    public CheckoutPage getCheckoutPage() {
        return (checkoutPage == null) ? checkoutPage = (driver==null) ? new CheckoutPage() : new CheckoutPage(driver) : checkoutPage;
//        checkoutPage = new CheckoutPage(driver);
//        return checkoutPage;
    }

}
