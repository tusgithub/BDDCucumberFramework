package com.next.cucumber.utilities;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import com.next.cucumber.pageobjectmanager.PageObjectManager;

public class TestContextSetupDI {

    public WebDriver driver;
    public String landingPageProductName;
    public PageObjectManager pageObjectManager;
    public BasePageObject basePageObject;
    public GeneralUtilities generalUtilities;

    public TestContextSetupDI() throws IOException {
        basePageObject = new BasePageObject();
        pageObjectManager = new PageObjectManager(basePageObject.webDriverManager());
        generalUtilities = new GeneralUtilities(basePageObject.webDriverManager());
    }
}
