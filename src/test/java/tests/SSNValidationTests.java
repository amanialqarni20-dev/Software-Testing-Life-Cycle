package tests;

import Utils.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import pages.RegistrationPage;

public class SSNValidationTests extends BaseTest {

    //  التحقق من وجود الحقل و الـ placeholder
    @Test
    public void testSSNFieldPresenceAndPlaceholder() {
        RegistrationPage page = new RegistrationPage();
        WebElement ssnField = page.getSSNField();

        Assert.assertTrue(ssnField.isDisplayed(), "SSN field is not displayed");
        Assert.assertEquals(ssnField.getAttribute("placeholder"), "123-45-6789",
                "Placeholder text is incorrect or missing");
    }


    @Test
    public void testSSNFieldIsRequired() {
        RegistrationPage page = new RegistrationPage()
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Test1234")
                .clickRegister();

        handleAlertIfPresent();

        WebElement ssnField = page.getSSNField();
        String validationMessage = ssnField.getAttribute("validationMessage");

        Assert.assertTrue(validationMessage != null && !validationMessage.isEmpty(),
                "Expected required field warning for SSN, but got: " + validationMessage);
    }

    //  إدخال SSN بصيغة غير صحيحة
    @Test
    public void testInvalidSSNFormat() {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("12345678") // بدون الشرطات
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Test1234")
                .clickRegister();

        WebElement ssnField = page.getSSNField();

        // نحفز ظهور رسالة الخطأ بنفس طريقة الضغط اليدوي
        ssnField.click();
        Driver.getDriver().switchTo().activeElement();

        String validationMessage = ssnField.getAttribute("validationMessage");
        System.out.println("Validation Message: " + validationMessage);

        Assert.assertTrue(
                validationMessage != null && !validationMessage.isEmpty(),
                "Expected warning for invalid SSN format, but got: " + validationMessage
        );
    }

    // 4) إدخال SSN صحيح
    @Test
    public void testValidSSNFormat() {
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

        handleAlertIfPresent();

        WebElement ssnField = page.getSSNField();
        String validationMessage = ssnField.getAttribute("validationMessage");

        Assert.assertTrue(
                validationMessage == null || validationMessage.isEmpty(),
                "SSN field should accept valid input, but got warning: " + validationMessage
        );
    }

    // ميثود للتعامل مع التنبيه إن وُجد
    private void handleAlertIfPresent() {
        try {
            Driver.getDriver().switchTo().alert().accept();
        } catch (Exception ignored) {
        }
    }
}
