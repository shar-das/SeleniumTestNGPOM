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

    @BeforeMethod(alwaysRun=true)
    public void beforeElectronicsPageTest() {
        loginPage = new LoginPage(driver);
        homePage = loginPage.performLogin(email, password);
        electronicsPage = homePage.goToElectronicsPage();
    }

    @Test(groups={"regression"})
    public void numberOfElectronicProducts() {
        int noOfProducts = electronicsPage.numberOfProducts();
        Assert.assertEquals(noOfProducts, Constants.NO_OF_ELECTRONIC_PRODUCTS,
                "Number of Electronics products is incorrect!");
    }

    @Test(groups={"regression"})
    public void validateElectronicProducts() throws IOException {
        Assert.assertTrue(electronicsPage.compareProducts(),
                "Electronic products are not matching!");
    }
    
    @Test(groups={"regression","smoke"})
    public void navigationToElectronicProductPage() {
    	productPage = electronicsPage.clickOnProduct("Toshiba 4K HDR TV");
    	Assert.assertTrue(productPage.verifyPageElements("Toshiba 4K HDR TV", "$350", 
    			"Volup erat ipsum diam elitr rebum et dolor. Est nonumy elitr erat diam stet sit clita ea. Sanc ipsum et, labore clita lorem magna duo dolor no sea Nonumy"),
    			"Navigation to Product page is not working!");
    }
}
