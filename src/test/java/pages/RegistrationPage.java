package pages;

import Utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {

    // ===== Locators =====
    private final By ssnBy = By.xpath("//*[@id='ssn']");
    private final By firstNameBy = By.xpath("//*[@id='firstName']");
    private final By lastNameBy = By.xpath("//*[@id='lastName']");

    //  Locators للجندر (input + label)
    private final By maleGenderInputBy   = By.id("male");
    private final By femaleGenderInputBy = By.id("female");
    private final By otherGenderInputBy  = By.id("other");

    private final By maleGenderLabelBy   = By.cssSelector("label[for='male']");
    private final By femaleGenderLabelBy = By.cssSelector("label[for='female']");
    private final By otherGenderLabelBy  = By.cssSelector("label[for='other']");

    private final By jobBy = By.xpath("//*[@id='job']");
    private final By cvUploadBy = By.xpath("//*[@id='cv']");
    private final By usernameBy = By.xpath("//*[@id='username']");
    private final By emailBy = By.xpath("//*[@id='email']");
    private final By passwordBy = By.xpath("//*[@id='password']");
    private final By registerButtonBy = By.xpath("//*[@id='registrationForm']/button");
    private final By formContainerBy = By.id("registrationForm");

    // ===== Fluent Methods =====
    public RegistrationPage enterSSN(String ssn) {
        safeSendKeys(safeFindElement(ssnBy), ssn);
        return this;
    }

    public RegistrationPage enterFirstName(String firstName) {
        safeSendKeys(safeFindElement(firstNameBy), firstName);
        return this;
    }

    public RegistrationPage enterLastName(String lastName) {
        safeSendKeys(safeFindElement(lastNameBy), lastName);
        return this;
    }

    // ✅ تعديل الضغط على الـ Label بدل الـ Input
    public RegistrationPage selectMaleGender() {
        jsClick(Driver.getDriver().findElement(maleGenderLabelBy));
        return this;
    }

    public RegistrationPage selectFemaleGender() {
        jsClick(Driver.getDriver().findElement(femaleGenderLabelBy));
        return this;
    }

    public RegistrationPage selectOtherGender() {
        jsClick(Driver.getDriver().findElement(otherGenderLabelBy));
        return this;
    }

    public RegistrationPage selectJob(String jobTitle) {
        WebElement jobElement = safeFindElement(jobBy);
        try {
            new Select(jobElement).selectByVisibleText(jobTitle);
        } catch (Exception e) {
            safeSendKeys(jobElement, jobTitle);
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

    // ✅ أداة الضغط بـ JavaScript
    private void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    // ===== Getters =====
    public WebElement getSSNField() { return Driver.getDriver().findElement(ssnBy); }

    public WebElement getFirstNameField() { return Driver.getDriver().findElement(firstNameBy); }

    public WebElement getLastNameField() { return Driver.getDriver().findElement(lastNameBy); }

    public WebElement getMaleGenderOption() { return Driver.getDriver().findElement(maleGenderInputBy); }

    public WebElement getFemaleGenderOption() { return Driver.getDriver().findElement(femaleGenderInputBy); }

    public WebElement getOtherGenderOption() { return Driver.getDriver().findElement(otherGenderInputBy); }

    // ✅ Getters للـ Labels (للتست المرئي)
    public WebElement getMaleGenderLabel() { return Driver.getDriver().findElement(maleGenderLabelBy); }

    public WebElement getFemaleGenderLabel() { return Driver.getDriver().findElement(femaleGenderLabelBy); }

    public WebElement getOtherGenderLabel() { return Driver.getDriver().findElement(otherGenderLabelBy); }

    public WebElement getJobField() { return Driver.getDriver().findElement(jobBy); }

    public WebElement getCVUploadField() { return Driver.getDriver().findElement(cvUploadBy); }

    public WebElement getUsernameField() { return Driver.getDriver().findElement(usernameBy); }

    public WebElement getEmailField() { return Driver.getDriver().findElement(emailBy); }

    public WebElement getPasswordField() { return Driver.getDriver().findElement(passwordBy); }

    public WebElement getRegisterButton() { return Driver.getDriver().findElement(registerButtonBy); }

    public WebElement getFormContainer() { return Driver.getDriver().findElement(formContainerBy); }

}
