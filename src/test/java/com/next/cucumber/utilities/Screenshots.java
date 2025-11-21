package com.next.cucumber.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {

    public static void addStepWithScreenshotInReport(WebDriver driver, String message) {
        try {
            // Capture screenshot as Base64
            String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            // Add the screenshot and message to the report
            System.out.println("Step with screenshot: " + message);
            System.out.println("Screenshot (Base64): " + screenshotBase64);
            // Integrate with your reporting tool here
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    public static void addStepInReport(String message) {
        // Add the message to the report without a screenshot
        System.out.println("Step: " + message);
        // Integrate with your reporting tool here
    }
}