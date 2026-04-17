package tests;

import base.BaseTest;
import constants.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.ExcelReader;

import java.io.IOException;

public class HomePageTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void beforeHomePageTests() {
        loginPage = new LoginPage(driver);
        homePage = loginPage.performLogin(email, password);
    }

    @Test
    public void logout() {
        loginPage = homePage.performLogout();
//        Assert.assertEquals(loginPage.getPageTitle(), Constants.LOGIN_PAGE_TITLE,
//                "User is not logged out");
        Assert.assertTrue(false);
    }

}
