package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
		if(productImage.isDisplayed() &&
			productName.getText().equals(name) &&
			productPrice.getText().equals(price) &&
			productDescription.getText().equals(description) &&
			addToCartButton.isDisplayed()) 
		{
			return true;
		}		
		return false;
	}
	
}
