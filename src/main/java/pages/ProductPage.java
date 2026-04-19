package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//h3[1]")
	private WebElement productName;
	
	@FindBy(xpath="//h3[2]")
	private WebElement productPice;
	
	@FindBy(xpath="//h3//parent::div//p")
	private WebElement productDescription;
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
