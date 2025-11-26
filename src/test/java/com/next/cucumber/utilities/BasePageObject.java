package com.next.cucumber.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class BasePageObject {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePageObject(){}

    public BasePageObject(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void load(String url){
        driver.get(url);
    }

    public WebDriver webDriverManager() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        String url = prop.getProperty("QAUrl");
        String browser_properties = prop.getProperty("browser");
        String browser_maven = System.getProperty("browser");

        // result = testCondition ? value1 (true): value2 (false);
        String browser = browser_maven != null ? browser_maven : browser_properties;

        // prefer explicit system property -Dheadless=true/false, otherwise detect CI env
        String headlessProp = System.getProperty("headless");
        String ciEnv = System.getenv("CI");
        boolean headless = headlessProp != null
                ? Boolean.parseBoolean(headlessProp)
                : (ciEnv != null && ciEnv.equalsIgnoreCase("true"));

        if (driver == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                if (headless) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--no-sandbox");
                }
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
            }if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get(url);
        }
        return driver;
    }

    private String getText(String locator){
        return getElement(locator).getText();
    }

    private WebElement getElement(String locator){
        return driver.findElement(By.xpath(locator));
    }

    private List<WebElement> getElements(String locator){
        return driver.findElements(By.xpath(locator));
    }

    private void clickElement(String locator){
        getElement(locator).click();
        System.out.println("Clicked on element");
    }

    private void dropDownValue(String locator, String value){
      Select select = new Select(getElement(locator));
      select.selectByVisibleText(value);
    }

    private void setInputValue(String locator, String value, boolean clearInput){
        WebElement element = getElement(locator);
        if(clearInput) {
            element.clear();
        }
        element.sendKeys(value);
        System.out.println("Set value "+value+" in element");
    }

    private void setInputValue(String locator, String value){
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(value);
        System.out.println("Set value "+value+" in element");
    }

    private boolean isEnabled(String locator){
        return getElement(locator).isEnabled();
    }

    private boolean isDisplayed(String locator){
        return getElement(locator).isDisplayed();
    }

    private boolean isSelected(String locator){
        return getElement(locator).isSelected();
    }

    private  void  clearElement(String locator){
        driver.findElement(By.xpath(locator)).clear();
        System.out.println("Cleared the element");
    }

    private void setExplicitWaitVisible(String locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    private void setExplicitWaitClickable(String locator){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    private void setCustomImplicitWait(int seconds){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public void waitForOverlaysToDisappear(By overlay){
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("OVERLAY SIZE" + overlays.size());
        if(!overlays.isEmpty()){
            wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("OVERLAYS INVISIBLE");
        } else{
            System.out.println("OVERLAY NOT FOUND");
        }
    }

}
