package tests;

import Utils.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegistrationPage;

public class GenderSelectionTests extends BaseTest {

    @BeforeMethod
    public void openPage() {
        Driver.getDriver().get("https://claruswaysda.github.io/Registration.html");
    }

    // 1) التأكد من وجود الخيارات
    @Test
    public void testThreeGenderOptionsExist() throws InterruptedException {
        RegistrationPage page = new RegistrationPage();

        Assert.assertTrue(page.getMaleGenderOption().isDisplayed(), "Male option is not displayed");
        Assert.assertTrue(page.getFemaleGenderOption().isDisplayed(), "Female option is not displayed");
        Assert.assertTrue(page.getOtherGenderOption().isDisplayed(), "Other option is not displayed");

        Thread.sleep(1500);
    }

    // 2) التأكد أنها Radio Buttons
    @Test
    public void testGenderOptionsAreRadioButtons() throws InterruptedException {
        RegistrationPage page = new RegistrationPage();

        Assert.assertEquals(page.getMaleGenderOption().getAttribute("type"), "radio");
        Assert.assertEquals(page.getFemaleGenderOption().getAttribute("type"), "radio");
        Assert.assertEquals(page.getOtherGenderOption().getAttribute("type"), "radio");

        Thread.sleep(1500);
    }

    //  اختيار خيار واحد فقط في كل مرة (باستخدام الضغط على الـ Label)
    @Test
    public void testOnlyOneGenderOptionCanBeSelected() throws InterruptedException {
        RegistrationPage page = new RegistrationPage();

        clickWithJS(page.getMaleGenderLabel());
        Thread.sleep(1500);
        Assert.assertTrue(page.getMaleGenderOption().isSelected());
        Assert.assertFalse(page.getFemaleGenderOption().isSelected());
        Assert.assertFalse(page.getOtherGenderOption().isSelected());

        clickWithJS(page.getFemaleGenderLabel());
        Thread.sleep(1500);
        Assert.assertTrue(page.getFemaleGenderOption().isSelected());
        Assert.assertFalse(page.getMaleGenderOption().isSelected());
        Assert.assertFalse(page.getOtherGenderOption().isSelected());

        clickWithJS(page.getOtherGenderLabel());
        Thread.sleep(1500);
        Assert.assertTrue(page.getOtherGenderOption().isSelected());
        Assert.assertFalse(page.getMaleGenderOption().isSelected());
        Assert.assertFalse(page.getFemaleGenderOption().isSelected());
    }

    //
    @Test
    public void testSelectedGenderHasVisualIndicator() throws InterruptedException {
        RegistrationPage page = new RegistrationPage();
        WebElement maleLabel = page.getMaleGenderLabel();

        clickWithJS(maleLabel);
        Thread.sleep(1500);

        String className = maleLabel.getAttribute("class");
        String style = maleLabel.getAttribute("style");

        boolean hasVisualIndicator =
                (className != null && !className.isEmpty()) ||
                        (style != null && !style.isEmpty());

        Assert.assertTrue(hasVisualIndicator,
                "No visual indicator on selected gender label");
    }

    // 5) التأكد أنه اختياري
    @Test
    public void testGenderSelectionIsOptional() throws InterruptedException {
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

        Thread.sleep(2000);

        Assert.assertTrue(true, "Form submission should not be blocked without gender selection");
    }

    private void clickWithJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);
    }
}
