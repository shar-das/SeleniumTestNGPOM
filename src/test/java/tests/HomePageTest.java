package tests;

import base.BaseTest;
import constants.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HomePageTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod(alwaysRun=true)
    public void beforeHomePageTests() {
        loginPage = new LoginPage(driver);
        homePage = loginPage.performLogin(email, password);
    }

    @Test(groups={"regression","smoke"})
    public void logout() {
        loginPage = homePage.performLogout();
        Assert.assertEquals(loginPage.getPageTitle(), Constants.LOGIN_PAGE_TITLE,
                "User is not logged out!");
    }

}
