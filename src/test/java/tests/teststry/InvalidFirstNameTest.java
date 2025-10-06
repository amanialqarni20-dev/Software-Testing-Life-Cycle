package tests.teststry;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

public class InvalidFirstNameTest extends BaseTest {

    @Test
    public void testInvalidFirstNameWithNumbers() {

        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("123456")   //  أرقام بدل ا
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amanialqarni20@gmail.com")
                .enterPassword("Pass@1234")
                .clickRegister();

        WebElement firstNameField = page.getFirstNameField();
        String validationMessage = firstNameField.getAttribute("validationMessage");

        System.out.println("Validation Message: " + validationMessage);

        Assert.assertTrue(
                validationMessage.toLowerCase().contains("letter") || 
                validationMessage.toLowerCase().contains("invalid") || 
                validationMessage.toLowerCase().contains("name"),
                "Expected a validation warning for invalid first name but got: " + validationMessage
        );
    }
}
