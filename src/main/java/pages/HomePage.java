package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class HomePage {

    WebDriver driver;
    CommonMethods commonMethods;
    ElectronicsPage electronicsPage;
    LoginPage loginPage;

    @FindBy(xpath="//h3[contains(text(),'Men')]//following-sibling::a")
    private WebElement menFashionLink;
    
    @FindBy(xpath="//h3[contains(text(),'Women')]//following-sibling::a")
    private WebElement womenFashionLink;
    
    @FindBy(xpath="//h3[contains(text(),'Kids')]//following-sibling::a")
    private WebElement kidsFashionLink;
    
    @FindBy(xpath="//h3[contains(text(),'Electronics')]//following-sibling::a")
    private WebElement electronicsLink;

    @FindBy(id="logoutBtn")
    private WebElement logoutButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.commonMethods = new CommonMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public ElectronicsPage goToElectronicsPage() {
        electronicsLink.click();
        electronicsPage = new ElectronicsPage(driver);
        return electronicsPage;
    }

    public LoginPage performLogout() {
        logoutButton.click();
        loginPage = new LoginPage(driver);
        return loginPage;
    }
}
