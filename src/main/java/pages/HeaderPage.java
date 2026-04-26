package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {
	
	WebDriver driver;
	
	@FindBy(xpath="text-decoration-none")
	static WebElement logo;
	
	@FindBy(xpath="//a[@id='cartdesk']")
	static WebElement cartButton;
	
	@FindBy(xpath="//span[@id='cartCount']")
	static WebElement cartCount;
	
	@FindBy(id="logoutBtn")
    static WebElement logoutButton;
	
	public HeaderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyElements() {
		if(logo.isDisplayed() &&
			cartButton.isDisplayed() &&
			cartCount.isDisplayed() &&
			logoutButton.isDisplayed())
		{
			return true;
		}
		return false;
	}

}
