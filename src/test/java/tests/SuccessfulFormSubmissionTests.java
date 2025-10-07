package tests;

import Utils.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegistrationPage;

import java.time.Duration;

public class SuccessfulFormSubmissionTests extends BaseTest {

    @BeforeMethod
    public void openPage() {
        Driver.getDriver().get("https://claruswaysda.github.io/Registration.html");
    }

    //  التأكد أن النموذج لا يُرسل قبل تعبئة جميع الحقول المطلوبة
    @Test
    public void testFormNotSubmittedWhenRequiredFieldsMissing() {
        RegistrationPage page = new RegistrationPage();
        page.clickRegister();

        // التحقق من أن التنبيه ما طلع (لأن البيانات ناقصة)
        boolean alertAppeared = isAlertPresent();
        Assert.assertFalse(alertAppeared, "Form should NOT submit when required fields are missing.");
    }

    //  التأكد أن النموذج يُرسل فقط بعد إدخال كل الحقول المطلوبة بنجاح
    @Test
    public void testFormSubmissionWithValidData() {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Test1234")
                .selectFemaleGender()
                .clickRegister();

        // انتظار ظهور رسالة النجاح
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        // التحقق من رسالة النجاح
        Alert alert = Driver.getDriver().switchTo().alert();
        String alertText = alert.getText();

        Assert.assertEquals(alertText.trim(), "Form submitted!", "Success message is incorrect.");

        alert.accept(); // نغلق التنبيه
    }

    //  التأكد أن السلوك الافتراضي للمتصفح (تحديث الصفحة) ما يصير بعد الإرسال
    @Test
    public void testFormDoesNotRefreshOnSubmit() {
        String initialUrl = Driver.getDriver().getCurrentUrl();

        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Developer")
                .uploadCV("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Test1234")
                .clickRegister();

        //  نتعامل مع التنبيه أول عشان ما يعطي خطأ UnhandledAlertException
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = Driver.getDriver().switchTo().alert();
            alert.accept(); // نقفل الـ alert
        } catch (Exception e) {
            System.out.println("No alert found, continuing...");
        }

        // الآن نتحقق إن الصفحة ما تغيّرت
        String afterSubmitUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(initialUrl, afterSubmitUrl,
                "Form should prevent default browser submission (page reload).");
    }

    // 🔹 دالة مساعدة: تتحقق إذا فيه alert مفتوح
    private boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
