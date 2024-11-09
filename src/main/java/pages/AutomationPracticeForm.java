package pages;

import manager.dto.StudentDTO;
import manager.enums.Gender;
import manager.enums.Hobbies;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class AutomationPracticeForm extends BasePage{

    public AutomationPracticeForm(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "firstName")
    WebElement fieldFirstName;
    @FindBy(id = "lastName")
    WebElement fieldLastName;
    @FindBy(xpath = "//*[@placeHolder='name@example.com']")
    WebElement fieldEmail;
    @FindBy(id = "userNumber")
    WebElement fieldMobileNumber;
    @FindBy(id = "dateOfBirthInput")
    WebElement fieldDateOfBirth; // 20 Sep 2024
    @FindBy(id = "subjectsInput")
    WebElement fieldSubject;
    @FindBy(id = "currentAddress")
    WebElement fieldCurrentAddress;
    @FindBy(id = "react-select-3-input")
    WebElement fieldState;
    @FindBy(id = "react-select-4-input")
    WebElement fieldCity;
    @FindBy(xpath = "//button[@id = 'submit']")
    WebElement btnSubmit;

    @FindBy(id = "example-modal-sizes-title-lg")
    WebElement textThanksFor;


    public AutomationPracticeForm fillStudentsForm(StudentDTO student) {
        hideFooter();
        fieldFirstName.sendKeys(student.getName());
        fieldLastName.sendKeys(student.getLastName());
        fieldEmail.sendKeys(student.getEmail());

        clickGender(student.getGender());
        fieldMobileNumber.sendKeys(student.getMobile());
        typeFieldDateOfBirth(student.getDateOfBirth());
        typeFieldSubjects(student.getSubject());
        typeHobbies(student.getHobbies());
        fieldCurrentAddress.sendKeys(student.getCurrentAddress());

        fieldState.sendKeys(student.getState());
        fieldState.sendKeys(Keys.ENTER);
        fieldCity.sendKeys(student.getCity());
        fieldCity.sendKeys(Keys.ENTER);

        return this;
    }

    public AutomationPracticeForm clickBtnSubmit() {
        btnSubmit.click();
        return this;
    }

    private void typeHobbies(List<Hobbies> hobbies) {
        for (Hobbies h : hobbies) {
            switch (h) {
                case MUSIC:
                    driver.findElement(By.cssSelector(h.getLocator())).click();
                    break;
                case SPORTS:
                    driver.findElement(By.cssSelector(h.getLocator())).click();
                    break;
                case READING:
                    driver.findElement(By.cssSelector(h.getLocator())).click();
                    break;


            }

        }

    }

    private void typeFieldDateOfBirth(String dateOfBirth) {
        String operationSystem = System.getProperty("os.name");
        fieldDateOfBirth.click();
        if(operationSystem.startsWith("Win"))
            fieldDateOfBirth.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        else if (operationSystem.startsWith("Mac"))
            fieldDateOfBirth.sendKeys(Keys.chord(Keys.COMMAND,"a"));
        fieldDateOfBirth.sendKeys(dateOfBirth);
        fieldDateOfBirth.sendKeys(Keys.ENTER);

    }

    private void clickGender(Gender gender) {
        WebElement elementGender = driver.findElement(By.xpath(gender.getLocator()));
        elementGender.click();
    }

    private void typeFieldSubjects (String subject) {
        String[] splitArray = subject.split(", ");
        fieldSubject.click();
        for (String str : splitArray) {
            fieldSubject.sendKeys(str);
            fieldSubject.sendKeys(Keys.ENTER);
        }
    }

    public boolean isTextPresent_textThanks() {
        return isTextPresent(textThanksFor, "Thanks for submitting the form", 5);
    }


}
