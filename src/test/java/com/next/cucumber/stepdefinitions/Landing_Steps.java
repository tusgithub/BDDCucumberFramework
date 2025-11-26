package com.next.cucumber.stepdefinitions;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import com.next.cucumber.pages.*;
import com.next.cucumber.utilities.TestContext;
import com.next.cucumber.utilities.AssertionLibrary;

public class Landing_Steps {

    public WebDriver driver;
    public String landingPageProductName;
    public LandingPage landingPage;
    TestContext context;

    public Landing_Steps(TestContext context) {
        this.context = context;
        this.landingPage = context.pageObjectManager.getLandingPage();
    }

    @Given("User is on GreenCart Landing page")
    public void user_is_on_green_cart_landing_page() {
        AssertionLibrary.assertTrue(landingPage.getTitleLandingPage().contains("GreenKart"),"Landing page is not displayed");
        System.out.println("User is on Landing page");
    }

    @When("^User searched with shortname (.+) and extracted actual name of product$")
    public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortName) throws InterruptedException {
        landingPage.searchItem(shortName);
        Thread.sleep(2000);
        context.landingPageProductName = landingPage.getProductName().split("-")[0].trim();
        System.out.println(context.landingPageProductName + " is extracted from Home Page");
    }

    @And("Added {string} items of the selected product to cart")
    public void Added_items_Product(String quantity) {
        landingPage.incrementQuantity(Integer.parseInt(quantity));
        landingPage.addToCart();
    }
}
