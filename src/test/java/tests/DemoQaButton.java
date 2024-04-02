package tests;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyManager;

import java.net.MalformedURLException;

public class DemoQaButton {
    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager= new PropertyManager();
    String url =propertyManager.getProperty("APP_DEMOQA_BUTTON_URL");

    public class ScrollToElement {
        public static void scrollToElement (WebDriver driver, WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }



    @BeforeMethod(alwaysRun = true)
    public void before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get(url);

    }

    @Test
    public void openDemoQaPageTest() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"DEMOQA");
    }

    @Test
    public void clickButtonsHW(){
        WebElement buttons = driver.findElement(new By.ByCssSelector("#item-4"));
        buttons.click();

        WebElement clickMeButton = driver.findElement(new By.ByCssSelector(".col-md-6 div:nth-of-type(3) > .btn"));
        ScrollToElement.scrollToElement(driver, clickMeButton);
        clickMeButton.click();

        WebElement dynamicMessage = driver.findElement(new By.ByCssSelector("#dynamicClickMessage"));
        System.out.println(dynamicMessage.getText());
    }

    @AfterMethod(alwaysRun = true)
    public void  after(){
        webDriver.quitDriver();
    }
}
