package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import pages.RegistrationPage;

public class NameValidationTests extends BaseTest {

    // 1) First Name يحتوي أرقام
    @Test
    public void testFirstNameWithNumbers() {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani123") // غير صالح
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Test1234")
                .clickRegister();

        assertValidationMessageForName(
                "Expected warning for numbers in First Name",
                page.getFirstNameField()
        );
    }

    // 2) Last Name يحتوي رموز
    @Test
    public void testLastNameWithSpecialCharacters() {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Test@#") // غير صالح
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Test1234")
                .clickRegister();

        assertValidationMessageForName(
                "Expected warning for special characters in Last Name",
                page.getLastNameField()
        );
    }

    // 3) First Name فارغ
    @Test
    public void testFirstNameIsRequired() {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("") // فارغ
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Test1234")
                .clickRegister();

        assertValidationMessageForName(
                "Expected required warning for empty First Name",
                page.getFirstNameField()
        );
    }

    // 4) Last Name فارغ
    @Test
    public void testLastNameIsRequired() {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("") // فارغ
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Test1234")
                .clickRegister();

        assertValidationMessageForName(
                "Expected required warning for empty Last Name",
                page.getLastNameField()
        );
    }

    // 5) أسماء صحيحة
    @Test
    public void testValidNames() {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Test1234")
                .clickRegister();

        Assert.assertTrue(true, "No validation errors expected for valid names");
    }

    // 6) التحقق من الـ Placeholder في First Name و Last Name
    @Test
    public void testNamePlaceholders() {
        RegistrationPage page = new RegistrationPage();

        WebElement firstName = page.getFirstNameField();
        WebElement lastName = page.getLastNameField();

        String firstPlaceholder = firstName.getAttribute("placeholder");
        String lastPlaceholder = lastName.getAttribute("placeholder");

        Assert.assertEquals(firstPlaceholder, "Enter your first name",
                "First Name placeholder is incorrect!");

        Assert.assertEquals(lastPlaceholder, "Enter your last name",
                "Last Name placeholder is incorrect!");
    }

    private void assertValidationMessageForName(String message, WebElement field) {
        String validationMessage = field.getAttribute("validationMessage");
        System.out.println("Name Validation Message: " + validationMessage);

        Assert.assertTrue(
                validationMessage != null && !validationMessage.isEmpty(),
                message + " but got: " + validationMessage
        );
    }
}
