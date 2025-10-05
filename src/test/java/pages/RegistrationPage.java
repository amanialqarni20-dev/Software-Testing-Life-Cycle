package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePage {

    // ===== Locators =====
    private final By ssnBy = By.xpath("//*[@id='ssn']");
    private final By firstNameBy = By.xpath("//*[@id='firstName']");
    private final By lastNameBy = By.xpath("//*[@id='lastName']");
    private final By jobTesterBy = By.xpath("//*[@id='job']/option[1]");
    private final By jobDeveloperBy = By.xpath("//*[@id='job']/option[2]");
    private final By cvUploadBy = By.xpath("//*[@id='cv']");
    private final By usernameBy = By.xpath("//*[@id='username']");
    private final By emailBy = By.xpath("//*[@id='email']");
    private final By passwordBy = By.xpath("//*[@id='password']");
    private final By registerButtonBy = By.xpath("//*[@id='registrationForm']/button");

    // ===== Fluent Methods =====
    public RegistrationPage enterSSN(String ssn) {
        safeSendKeys(safeFindElement(ssnBy), ssn);
        return this;
    }
    public WebElement getFirstNameField() {
        return getDriver().findElement(By.id("firstName")); // غيّري الـ id إذا مختلف عندك
    }

    public RegistrationPage enterFirstName(String firstName) {
        safeSendKeys(safeFindElement(firstNameBy), firstName);
        return this;
    }
    public WebElement getPasswordField() {
        return safeFindElement(passwordBy);
    }

    public RegistrationPage enterLastName(String lastName) {
        safeSendKeys(safeFindElement(lastNameBy), lastName);
        return this;
    }

    public RegistrationPage selectJob(String jobTitle) {
        if (jobTitle.equalsIgnoreCase("Tester")) {
            safeClick(safeFindElement(jobTesterBy));
        } else {
            safeClick(safeFindElement(jobDeveloperBy));
        }
        return this;
    }

    public RegistrationPage uploadCV(String filePath) {
        safeSendKeys(safeFindElement(cvUploadBy), filePath);
        return this;
    }

    public RegistrationPage enterUsername(String username) {
        safeSendKeys(safeFindElement(usernameBy), username);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        safeSendKeys(safeFindElement(emailBy), email);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        safeSendKeys(safeFindElement(passwordBy), password);
        return this;
    }

    public RegistrationPage clickRegister() {
        safeClick(safeFindElement(registerButtonBy));
        return this;
    }

    // Optional: للتحقق من ظهور alert
    public RegistrationPage assertFormSubmitted() {
        try {
            String alertText = getDriver().switchTo().alert().getText();
            if (!alertText.equals("Form submitted!")) {
                throw new AssertionError("Expected 'Form submitted!' but got: " + alertText);
            }
            getDriver().switchTo().alert().accept();
        } catch (Exception e) {
            throw new AssertionError("No alert was displayed after clicking Register!");
        }
        return this;
    }

    // Getter اختياري لو بتشيكي رسائل الخطأ
    public WebElement getEmailField() {
        return safeFindElement(emailBy);
    }
}
