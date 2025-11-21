package com.next.cucumber.utilities;

import  org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AssertionLibrary {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private static final String ACTUAL = "<br/>Actual: ";
    private static final String EXPECTED = "<br/>Expected: ";

    public AssertionLibrary(WebDriver driver) {
        AssertionLibrary.driver.set(driver);
    }

    public enum Screenshot
    {
        REQUIRED, NOTREQUIRED }

    public static void assertEquals(Object actual, Object expected, String message, Screenshot screenshot) {

        String reportMessage = message + ACTUAL + actual.toString() + EXPECTED + expected.toString();
        Assert.assertEquals(actual, expected, message);
        attachScreenshotIfRequired(screenshot, reportMessage);

    }

    public static void assertNotEquals(Object actual, Object expected, String message, Screenshot screenshot) {

        String reportMessage = message + ACTUAL + actual.toString() + EXPECTED + expected.toString();
        Assert.assertNotEquals(actual, expected, message);
        attachScreenshotIfRequired(screenshot, reportMessage);
    }

    public static void assertTrue(boolean condition, String message) {
            assertTrue(condition, message, Screenshot.REQUIRED);

    }
    public static void assertTrue(boolean condition, String message, Screenshot screenshot) {
            String reportMessage = message + "<br> Condition: " +condition;
            Assert.assertTrue(condition, message);
            attachScreenshotIfRequired(screenshot, reportMessage);
    }

    public static void assertFalse(boolean condition, String message) {
        assertFalse(condition, message, Screenshot.REQUIRED);
    }
    public static void assertFalse(boolean condition, String message, Screenshot screenshot) {
        String reportMessage = message + "<br> Condition: " +condition;
        Assert.assertFalse(condition, message);
        attachScreenshotIfRequired(screenshot, reportMessage);
    }

    public static void attachScreenshotIfRequired(Screenshot screenshot, String message) {
        if (screenshot.equals(Screenshot.REQUIRED)) {
            Screenshots.addStepWithScreenshotInReport(driver.get(), message);
        } else {
            Screenshots.addStepInReport(message);
        }
    }
}
