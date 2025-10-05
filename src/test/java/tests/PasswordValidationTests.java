package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import pages.RegistrationPage;

public class PasswordValidationTests extends BaseTest {

    // 1) Password less than 8 characters
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
                .enterPassword("12345") // أقل من 8 أحرف
                .clickRegister();

        assertValidationMessage("Expected warning for short password", page);
    }

    // 2) Password with no uppercase
    @Test
    public void testPasswordMissingUppercase() {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("password123") // لا يحتوي حرف كبير
                .clickRegister();

        assertValidationMessage("Expected warning for missing uppercase", page);
    }

    // 3) Password with no lowercase
    @Test
    public void testPasswordMissingLowercase() {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("PASSWORD123") // لا يحتوي حرف صغير
                .clickRegister();

        assertValidationMessage("Expected warning for missing lowercase", page);
    }

    // 4) Password with no digit
    @Test
    public void testPasswordMissingDigit() {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Password") // لا يحتوي رقم
                .clickRegister();

        assertValidationMessage("Expected warning for missing digit", page);
    }

    // 5) Digits only
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
                .enterPassword("12345678") // أرقام فقط
                .clickRegister();

        assertValidationMessage("Expected warning for digits-only password", page);
    }

    // ✅ 6) Valid password
    @Test
    public void testValidPassword() {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Test1234") // يحتوي حرف كبير + صغير + رقم + 8 أحرف
                .clickRegister();

        WebElement passwordField = page.getPasswordField();
        String validationMessage = passwordField.getAttribute("validationMessage");

        System.out.println("Password Validation Message (Valid): " + validationMessage);

        Assert.assertTrue(
                validationMessage == null || validationMessage.isEmpty(),
                "Expected no validation warning for valid password but got: " + validationMessage
        );
    }

    //  Helper method to assert validation
    private void assertValidationMessage(String message, RegistrationPage page) {
        WebElement passwordField = page.getPasswordField();
        String validationMessage = passwordField.getAttribute("validationMessage");

        System.out.println("Password Validation Message: " + validationMessage);

        Assert.assertTrue(
                validationMessage != null && !validationMessage.isEmpty(),
                message + " but got: " + validationMessage
        );
    }
}
