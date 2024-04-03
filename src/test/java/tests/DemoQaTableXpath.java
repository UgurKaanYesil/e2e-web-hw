package tests;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.PropertyManager;

import java.net.MalformedURLException;
import java.time.Duration;

public class DemoQaTableXpath {
    public static WebDriver driver;
    WebDriverWait waitDriver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager = new PropertyManager();
    String url = propertyManager.getProperty("APP_DEMOQA_TABLE_URL");

    // class with the method to use in case web elements overlap.
    public class ScrollToElement {
        public static void scrollToElement (WebDriver driver, WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }

    @BeforeTest()
    public void setUp() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get(url);
    }

    @Test
    public void openWebTables() {
        Assert.assertEquals(url, "https://demoqa.com/webtables");
    }

    @Test
    public void submitAndEditNewEntry() {
        WebElement addButton = driver.findElement(By.xpath("//*[@id='addNewRecordButton']"));
        ScrollToElement.scrollToElement(driver, addButton);
        addButton.click();

        waitDriver = new WebDriverWait(driver, Duration.ofSeconds(300));
        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='registration-form-modal']")));
        WebElement firstNameField = driver.findElement(By.xpath("//*[@id='firstName']"));
        firstNameField.sendKeys("Mehmet");

        WebElement lastNameField = driver.findElement(By.xpath("//*[@id='lastName']"));
        lastNameField.sendKeys("Topal");

        WebElement emailField = driver.findElement(By.xpath("//*[@id='userEmail']"));
        emailField.sendKeys("mehmett@example.com");

        WebElement ageField = driver.findElement(By.xpath("//*[@id='age']"));
        ageField.sendKeys("20");

        WebElement salaryField = driver.findElement(By.xpath("//*[@id='salary']"));
        salaryField.sendKeys("34550");

        WebElement departmentField = driver.findElement(By.xpath("//*[@id='department']"));
        departmentField.sendKeys("Finance");

        WebElement submitButton = driver.findElement(By.xpath("//*[@id='submit']"));
        submitButton.click();

        WebElement editButton = driver.findElement(By.xpath("//*[@id='edit-record-4']"));
        editButton.click();

        WebElement updatedFirstNameField = driver.findElement(By.xpath("//*[@id='firstName']"));
        updatedFirstNameField.clear();
        updatedFirstNameField.sendKeys("Mustafa");

        WebElement updateSubmitButton = driver.findElement(By.xpath("//*[@id='submit']"));
        updateSubmitButton.click();

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
