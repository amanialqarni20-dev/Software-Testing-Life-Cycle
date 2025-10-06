package tests.teststry;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

public class WeakPassword_ShortLengthTest extends BaseTest {

    @Test
    public void testPasswordTooShort() {

        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Abc12")  // أقل من 8 أحرف
                .clickRegister();

        WebElement passwordField = page.getPasswordField();
        String validationMessage = passwordField.getAttribute("validationMessage");

        System.out.println("Password Validation Message: " + validationMessage);

        Assert.assertTrue(
                validationMessage != null && !validationMessage.isEmpty(),
                "Expected validation warning for short password but got: " + validationMessage
        );
    }
}
