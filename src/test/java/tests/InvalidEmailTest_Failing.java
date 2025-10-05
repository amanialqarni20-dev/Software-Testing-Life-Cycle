package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import org.openqa.selenium.WebElement;

public class InvalidEmailTest_Failing extends BaseTest {

    @Test
    public void testInvalidEmailFormat_Failing() {

        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:/Users/amani/OneDrive/Desktop/mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amanialqarni20gmail.com")  // بدون @
                .enterPassword("Pass@1234")
                .clickRegister();

        WebElement emailField = page.getEmailField();
        String validationMessage = emailField.getAttribute("validationMessage");

        System.out.println("Validation Message: " + validationMessage);

        //  هنا العكس — راح نفشل الاختبار متعمد
        Assert.assertFalse(validationMessage.contains("@"),
                "هذا الاختبار متعمد يفشل عشان نشوف الأحمر! الرسالة كانت: " + validationMessage);
    }
}
