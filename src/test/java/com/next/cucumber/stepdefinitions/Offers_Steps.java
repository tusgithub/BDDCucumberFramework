package com.next.cucumber.stepdefinitions;

import org.testng.Assert;
import io.cucumber.java.en.*;
import com.next.cucumber.pages.*;
import com.next.cucumber.utilities.TestContextSetupDI;

public class Offers_Steps {
    public String offerPageProductName;
    TestContextSetupDI testContextSetup;

    public Offers_Steps(TestContextSetupDI testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    @Then("^User searched for (.+) shortname in offers page$")
    public void user_searched_for_same_shortname_in_offers_page(String shortName) throws InterruptedException {
        switchToOffersPage();
        OffersPage offerPage = testContextSetup.pageObjectManager.getOffersPage();
        offerPage.searchItem(shortName);
        Thread.sleep(2000);
        offerPageProductName = offerPage.getProductName();
        System.out.println(offerPageProductName + " is extracted from Offers Page");

    }

    public void switchToOffersPage() {
        LandingPage landingPage = testContextSetup.pageObjectManager.getLandingPage();
        landingPage.selectTopDealsPage();
        testContextSetup.generalUtilities.SwitchWindowToChild();
    }

    @Then("Validate product name in Offers page matches with Landing Page")
    public void validate_product_name_in_offers_page_with_Landing_Page() {
        Assert.assertEquals(offerPageProductName, testContextSetup.landingPageProductName,"Product names do not match!");
    }
}
