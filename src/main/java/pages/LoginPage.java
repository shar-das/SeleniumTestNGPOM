package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class LoginPage {

    WebDriver driver;
    CommonMethods commonMethods;
    HomePage homePage;

    @FindBy(id="email")
    WebElement emailField;

    @FindBy(id="errorMsg")
    WebElement errorMessage;

    @FindBy(id="loginBtn")
    WebElement loginButton;

    @FindBy(id="password")
    WebElement passwordField;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.commonMethods = new CommonMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean errorMessageDisplayed() {
        return commonMethods.elementIsDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public HomePage performLogin(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
        homePage = new HomePage(driver);
        return homePage;
    }

}
