package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.Constants;
import pages.ElectronicsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;

public class ElectronicsPageTest extends BaseTest {

    HomePage homePage;
    ElectronicsPage electronicsPage;
    LoginPage loginPage;
    ProductPage productPage;

    @BeforeMethod
    public void beforeElectronicsPageTest() {
        loginPage = new LoginPage(driver);
        homePage = loginPage.performLogin(email, password);
        electronicsPage = homePage.goToElectronicsPage();
    }

    @Test
    public void numberOfElectronicProducts() {
        int noOfProducts = electronicsPage.numberOfProducts();
        Assert.assertEquals(noOfProducts, Constants.NO_OF_ELECTRONIC_PRODUCTS,
                "Number of Electronics products is incorrect");
    }

    @Test
    public void validateElectronicProducts() throws IOException {
        Assert.assertTrue(electronicsPage.compareProducts(),
                "Electronic products are not matching");
    }
    
    @Test
    public void navigationToElectronicProductPage() {
    	productPage = electronicsPage.clickOnProduct("Toshiba 4K HDR TV");
    }
}
