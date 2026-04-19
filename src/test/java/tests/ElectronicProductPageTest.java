package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.Constants;
import pages.ElectronicsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;

public class ElectronicProductPageTest extends BaseTest {
	
	ElectronicsPage electronicsPage;
	HomePage homePage;
	LoginPage loginPage;
	ProductPage productPage;
	
	@BeforeMethod
    public void beforeElectronicsPageTest() {
        loginPage = new LoginPage(driver);
        homePage = loginPage.performLogin(email, password);
        electronicsPage = homePage.goToElectronicsPage();
        productPage = electronicsPage.clickOnProduct("Toshiba 4K HDR TV");
    }
	
	@Test
	public void addToCart() {
		productPage.clickAddToCart();
		Assert.assertTrue(productPage.verifyCartCount("1"), "Item is not added to cart!");
	}
	
	public void goToHomePage() {
		homePage = productPage.clickGoToBack();
		Assert.assertEquals(homePage.getPageTitle(), Constants.HOME_PAGE_TITLE,
				"User is not navigated to Home page!");
	}

}
