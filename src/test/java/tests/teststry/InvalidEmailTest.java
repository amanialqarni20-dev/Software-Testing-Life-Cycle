package tests.teststry;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

public class InvalidEmailTest extends BaseTest {

    @Test
    public void testInvalidEmailFormat() {

        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amanialqarni20gmail.com")  //  بدون @
                .enterPassword("Pass@1234")
                .clickRegister();

        WebElement emailField = page.getEmailField();
        String validationMessage = emailField.getAttribute("validationMessage");

        System.out.println("Validation Message: " + validationMessage);

        //  الشرط الصحيح: لازم يحتوي التحذير على كلمة "@" أو كلمة
        Assert.assertTrue(validationMessage.contains("@") || validationMessage.toLowerCase().contains("include"),
                "Expected an email validation warning but got: " + validationMessage);
    }
}
