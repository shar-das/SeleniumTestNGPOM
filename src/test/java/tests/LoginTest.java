package tests;

import constants.Constants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import base.BaseTest;
import utils.ExcelReader;

import java.io.IOException;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void beforeLoginTests() {
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider="correctLoginData")
    public void loginCorrectCredentials(String email, String password) {
        homePage = loginPage.performLogin(email, password);
        Assert.assertEquals(homePage.getPageTitle(), Constants.HOME_PAGE_TITLE,
                "User is not logged in");
    }

    @Test(dataProvider="incorrectLoginData")
    public void loginIncorrectCredentials(String email, String password) {
        loginPage.performLogin(email, password);
        Assert.assertTrue(loginPage.errorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), Constants.LOGIN_ERROR_MESSAGE,
                "Error message is not displayed on login page");
    }

    @DataProvider(name="correctLoginData")
    public Object[][] correctLoginDataProvider() throws IOException {
        return ExcelReader.readExcel("Correct Login Data");
    }

    @DataProvider(name="incorrectLoginData")
    public Object[][] incorrectLoginDataProvider() throws IOException {
        return ExcelReader.readExcel("Incorrect Login Data");
    }

}
