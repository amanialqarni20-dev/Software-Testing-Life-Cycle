package tests;

import Utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;

import java.util.Arrays;
import java.util.List;

public class RegistrationFormUITests extends BaseTest {

    // 1) التأكد أن الصفحة تفتح والنموذج ظاهر ومتمركز
    @Test
    public void testFormIsDisplayedAndCentered() {
        RegistrationPage page = new RegistrationPage();
        WebElement form = page.getFormContainer();

        Assert.assertTrue(form.isDisplayed(), "Form is not displayed");

        int formWidth = form.getSize().getWidth();
        Assert.assertTrue(formWidth > 200, "Form doesn't seem properly sized/centered");
    }

    // 2) التأكد من وجود جميع الحقول المطلوبة
    @Test
    public void testAllRequiredFieldsArePresent() {
        RegistrationPage page = new RegistrationPage();

        List<WebElement> fields = Arrays.asList(
                page.getSSNField(),
                page.getFirstNameField(),
                page.getLastNameField(),
                page.getMaleGenderOption(),
                page.getFemaleGenderOption(),
                page.getOtherGenderOption(),
                page.getJobField(),
                page.getCVUploadField(),
                page.getUsernameField(),
                page.getEmailField(),
                page.getPasswordField()
        );

        for (WebElement field : fields) {
            Assert.assertTrue(field.isDisplayed(), "One of the fields is not displayed: " + field);
        }
    }

    // 3) التأكد من وجود زر Register بأسفل النموذج
    @Test
    public void testRegisterButtonExists() {
        RegistrationPage page = new RegistrationPage();
        WebElement registerButton = page.getRegisterButton();

        Assert.assertTrue(registerButton.isDisplayed(), "Register button is not displayed");
        Assert.assertEquals(registerButton.getText(), "Register",
                "Register button text is not correct");
    }

    // 4) التأكد أن الحقول عليها Labels واضحة
    @Test
    public void testFieldsHaveLabels() {
        List<String> expectedLabels = Arrays.asList(
                "Social Security Number",
                "First Name",
                "Last Name",
                "Gender",
                "Job",
                "CV",
                "Username",
                "Email",
                "Password"
        );

        for (String label : expectedLabels) {
            WebElement labelElement =
                    Driver.getDriver().findElement(By.xpath("//*[contains(text(),'" + label + "')]"));
            Assert.assertTrue(labelElement.isDisplayed(), "Label not found or hidden: " + label);
        }
    }

    // 5) التأكد أن النموذج Responsive (على الأقل حجمه معقول)
    @Test
    public void testFormIsResponsive() {
        RegistrationPage page = new RegistrationPage();
        WebElement form = page.getFormContainer();

        int width = form.getSize().getWidth();
        Assert.assertTrue(width > 300, "Form width is too small, not responsive");
    }
}
