package com.next.cucumber.utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class AfterBeforeHook {

    TestContext context;


    public AfterBeforeHook(TestContext context) {
        this.context = context;
    }
    @After
    public void afterScenario() throws IOException {
        context.basePageObject.webDriverManager().quit();
    }

    @AfterStep
    public void AddScreenshot(Scenario scenario) throws IOException {
        WebDriver driver = context.basePageObject.webDriverManager();
        if(scenario.isFailed()) {

            File sourcepath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent =  FileUtils.readFileToByteArray(sourcepath);
            scenario.attach(fileContent, "image/png", "image");
        }
    }
}
