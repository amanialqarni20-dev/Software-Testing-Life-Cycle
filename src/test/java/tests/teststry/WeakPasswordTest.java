package tests.teststry;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

public class WeakPasswordTest extends BaseTest {

    @Test
    public void testWeakPasswordFormat() {

        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")  // إيميل صحيح هالمرة
                .enterPassword("12345")  // أقل من 6 حروف  كلمة سر ضعيفة
                .clickRegister();

        // نجلب عنصر الباسورد نفسه
        WebElement passwordField = page.getPasswordField();
        String validationMessage = passwordField.getAttribute("validationMessage");

        System.out.println("Password Validation Message: " + validationMessage);

        // الشرط الصحيح: لازم تكون الرسالة غير فاضية وتدل على رفض كلمة المرور
        Assert.assertTrue(
                validationMessage != null && !validationMessage.isEmpty(),
                "Expected password validation warning but got: " + validationMessage
        );
    }
}
