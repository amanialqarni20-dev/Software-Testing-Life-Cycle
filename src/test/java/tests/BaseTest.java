package tests;

import Utils.ConfigReader;
import Utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver(); // يفتح متصفح لكل تست
        String url = ConfigReader.getProperty("registration_url");
        driver.get(url); // يروح للصفحة بداية كل تست
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver(); // يقفل المتصفح بعد كل تست

    }



}
