package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import org.openqa.selenium.WebElement;

public class WeakPassword_DigitsOnlyTest extends BaseTest {

    @Test
    public void testPasswordDigitsOnly() {

        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("12345678")  // أرقام فقط - بدون حروف
                .clickRegister();

        WebElement passwordField = page.getPasswordField();
        String validationMessage = passwordField.getAttribute("validationMessage");

        System.out.println("Password Validation Message: " + validationMessage);

        Assert.assertTrue(
                validationMessage != null && !validationMessage.isEmpty(),
                "Expected validation warning for digits-only password but got: " + validationMessage
        );
    }
}
