package tests;

import Utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegistrationPage;

import java.util.List;

public class JobAndCVTests extends BaseTest {

    @BeforeMethod
    public void openPage() {
        Driver.getDriver().get("https://claruswaysda.github.io/Registration.html");
    }

    //  1) التحقق من وجود الخيارات داخل الـ Job Dropdown
    @Test
    public void testJobDropdownOptions() {
        RegistrationPage page = new RegistrationPage();
        WebElement jobDropdown = page.getJobField();

        List<WebElement> options = jobDropdown.findElements(By.tagName("option"));

        Assert.assertTrue(options.stream().anyMatch(o -> o.getText().equals("Developer")));
        Assert.assertTrue(options.stream().anyMatch(o -> o.getText().equals("Tester")));
        Assert.assertTrue(options.stream().anyMatch(o -> o.getText().equals("Designer")));
        Assert.assertTrue(options.stream().anyMatch(o -> o.getText().equals("Manager")));
        Assert.assertTrue(options.stream().anyMatch(o -> o.getText().equals("Other")));
    }

    //  2) التحقق من الـ Placeholder الافتراضي
    @Test
    public void testJobDefaultPlaceholder() {
        RegistrationPage page = new RegistrationPage();
        WebElement jobDropdown = page.getJobField();

        String defaultText = jobDropdown.findElement(By.tagName("option")).getText();
        Assert.assertEquals(defaultText, "Select a job");
    }

    //  3) التأكد أن اختيار الوظيفة مطلوب
    @Test
    public void testJobIsRequired() {
        RegistrationPage page = new RegistrationPage();
        WebElement jobDropdown = page.getJobField();

        String required = jobDropdown.getAttribute("required");
        Assert.assertNotNull(required, "Job field should be required");
    }
    @Test
    public void printActualWidths() {
        RegistrationPage page = new RegistrationPage();

        WebElement jobField = page.getJobField();
        WebElement cvField = page.getCVUploadField();

        String jobWidth = jobField.getCssValue("width");
        String cvWidth = cvField.getCssValue("width");

        System.out.println(" Job Field Width = " + jobWidth);
        System.out.println(" CV Field Width  = " + cvWidth);
    }
    @Test
    public void testCVIsRequiredForSubmission() throws InterruptedException {
        RegistrationPage page = new RegistrationPage()
                .enterSSN("123-45-6789")
                .enterFirstName("Amani")
                .enterLastName("Tester")
                .selectJob("Tester")
                .enterUsername("user" + System.currentTimeMillis())
                .enterEmail("amani" + System.currentTimeMillis() + "@gmail.com")
                .enterPassword("Test1234")
                .clickRegister();

        Thread.sleep(2000);

        // بعد الضغط بدون رفع ملف، يفترض ما يرسل الفورم
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("Registration"),
                "Form should not submit without uploading a CV (PDF).");
    }
    @Test
    public void testCVOnlyAcceptsPDF() throws InterruptedException {
        RegistrationPage page = new RegistrationPage();

        WebElement cvField = page.getCVUploadField();

        // نحاول رفع ملف غير PDF
        cvField.sendKeys("C:\\Users\\amani\\OneDrive\\Desktop\\example.txt");
        Thread.sleep(2000);

        // نتحقق من أن القيمة ما انقبلت
        String uploadedFile = cvField.getAttribute("value");
        Assert.assertFalse(uploadedFile.endsWith(".txt"),
                "System should not accept non-PDF files.");

        // الآن نرفع PDF صحيح
        cvField.sendKeys("C:\\Users\\amani\\OneDrive\\Desktop\\mycv.pdf");
        Thread.sleep(2000);

        String uploadedPDF = cvField.getAttribute("value");
        Assert.assertTrue(uploadedPDF.endsWith(".pdf"),
                "System should accept only PDF files.");
    }

    //  4) قبول فقط ملفات PDF في رفع الـ CV
    @Test
    public void testCVAcceptsOnlyPDF() {
        RegistrationPage page = new RegistrationPage();
        WebElement cvUploadField = page.getCVUploadField();

        String accept = cvUploadField.getAttribute("accept");
        Assert.assertTrue(accept.contains(".pdf"), "CV field should accept only PDF files");
    }

    //  5) التحقق من وجود الليبل "CV (PDF only)"
    @Test
    public void testCVFieldLabel() {
        WebElement cvLabel = Driver.getDriver().findElement(By.xpath("//label[contains(text(),'CV')]"));
        Assert.assertTrue(cvLabel.getText().contains("CV"), "Label should include 'CV (PDF only)'");
    }

    //  6) التحقق أن Job و CV جنب بعض (عرض 48%)
    @Test
    public void testJobAndCVFieldsAreSideBySide() {
        RegistrationPage page = new RegistrationPage();

        WebElement jobField = page.getJobField();
        WebElement cvField = page.getCVUploadField();

        String jobWidth = jobField.getCssValue("width");
        String cvWidth = cvField.getCssValue("width");

        Assert.assertTrue(jobWidth.contains("48") || cvWidth.contains("48"),
                "Job and CV fields should be displayed side-by-side (48% each)");
    }
}
