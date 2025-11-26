package com.next.cucumber.stepdefinitions;

import com.next.cucumber.pages.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.next.cucumber.utilities.*;

public class Checkout_Steps {

    public WebDriver driver;
    public String landingPageProductName;
    public CheckoutPage checkoutPage;
    TestContext context;

    public Checkout_Steps(TestContext context) {
        this.context = context;
        this.checkoutPage = context.pageObjectManager.getCheckoutPage();
    }

    @Then("^User proceeds to Checkout and validate (.+) items in checkout page$")
    public void User_proceeds_to_Checkout(String name)  {
        checkoutPage.CheckoutItems();

        //Assertion to extract name from screen and compare with name
    }

    @And("verify user has ability to enter promo code and place the order")
    public void verify_user_has_ability_to_enter_promo() {

        Assert.assertTrue(checkoutPage.VerifyPromoBtn(),"Promo button is not displayed");
        Assert.assertTrue(checkoutPage.VerifyPlaceOrder(),"Place Order button is not displayed");

    }
}
