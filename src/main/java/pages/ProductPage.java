package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage extends HeaderPage {
	
	WebDriver driver;
	HomePage homePage;
	
	@FindBy(xpath="//div[@id='product-carousel']//descendant::img")
	WebElement productImage;
	
	@FindBy(xpath="//h3[1]")
	private WebElement productName;
	
	@FindBy(xpath="//h3[2]")
	private WebElement productPrice;
	
	@FindBy(xpath="//h3//parent::div//p")
	private WebElement productDescription;
	
	@FindBy(xpath="//button[contains(@class,'addToCart')]")
	private WebElement addToCartButton;
	
	@FindBy(xpath="//a[contains(@class,'btn-primary')][@href='shop.php']")
	private WebElement goToBackButton;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// wait for key elements to be visible to ensure we're on the product page
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.or(
					ExpectedConditions.visibilityOf(productName),
					ExpectedConditions.visibilityOf(addToCartButton)
			));
		} catch (Exception e) {
			// if wait fails, we'll let verify methods handle missing elements gracefully
		}
	}
	
	public void clickAddToCart() {
		addToCartButton.click();
	}
	
	public HomePage clickGoToBack() {
		goToBackButton.click();
		homePage = new HomePage(driver);
		return homePage;
	}
	
	public boolean verifyCartCount(String count) {
		if(cartCount.getText().equals(count))
			return true;
		return false;
	}
	
	public boolean verifyPageElements(String name, String price, String description) {
		try {
			boolean imgDisplayed = productImage != null && productImage.isDisplayed();
			boolean nameMatch = productName != null && productName.getText().equals(name);
			boolean priceMatch = productPrice != null && productPrice.getText().equals(price);
			boolean descMatch = productDescription != null && productDescription.getText().equals(description);
			boolean addDisplayed = addToCartButton != null && addToCartButton.isDisplayed();

			return imgDisplayed && nameMatch && priceMatch && descMatch && addDisplayed;
		} catch (Exception e) {
			// If any element is missing or a Stale/NoSuchElement exception occurs, return false
			return false;
		}
	}
	
}
