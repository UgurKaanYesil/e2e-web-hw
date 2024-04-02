package tests;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyManager;

import java.net.MalformedURLException;
import java.time.Duration;

public class DemoQaTable {
    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager= new PropertyManager();
    String url =propertyManager.getProperty("APP_DEMOQA_TABLE_URL");

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
    public void sendAndEditTableTest(){
        WebElement addButton = driver.findElement(By.cssSelector("#addNewRecordButton"));
        ScrollToElement.scrollToElement(driver, addButton);
        addButton.click();

        WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(300));
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.id("registration-form-modal")));
        WebElement firstNameField = driver.findElement(By.cssSelector("#firstName"));
        firstNameField.sendKeys("Uğur");

        WebElement lastNameField = driver.findElement(By.cssSelector("#lastName"));
        lastNameField.sendKeys("Yeşil");

        WebElement emailField = driver.findElement(By.cssSelector("#userEmail"));
        emailField.sendKeys("asdsf@example.com");

        WebElement ageField = driver.findElement(By.cssSelector("#age"));
        ageField.sendKeys("26");

        WebElement salaryField = driver.findElement(By.cssSelector("#salary"));
        salaryField.sendKeys("50000");

        WebElement departmentField = driver.findElement(By.cssSelector("#department"));
        departmentField.sendKeys("IT");

        WebElement submitButton = driver.findElement(By.cssSelector("#submit"));
        submitButton.click();

        WebElement editButton = driver.findElement(By.cssSelector("#edit-record-4"));
        editButton.click();

        WebElement updatedFirstNameField = driver.findElement(By.cssSelector("#firstName"));
        updatedFirstNameField.clear();
        updatedFirstNameField.sendKeys("Kaan");

        WebElement updateSubmitButton = driver.findElement(By.cssSelector("#submit"));
        updateSubmitButton.click();

        WebElement updatedFirstNameCell = driver.findElement((By.cssSelector("div.rt-tbody > div:nth-child(4) > div > div:nth-child(1)")));
        System.out.println("Field modified as:" + " " + updatedFirstNameCell.getText());
    }

    @AfterMethod(alwaysRun = true)
    public void  after(){
        webDriver.quitDriver();
    }
}
