package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ExcelReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElectronicsPage {

    WebDriver driver;
    ProductPage productPage;
    List<String> productNames;
    List<String> actualProducts;
    List<String> expectedProducts;

    @FindBy(css=".product-item")
    private List<WebElement> products;

    @FindBy(xpath="//div[contains(@class,'product-item')]//descendant::a")
    private List<WebElement> productLinks;

    public ElectronicsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public ProductPage clickOnProduct(String productName) {
		for(WebElement product:productLinks) {
			if(product.getText().equalsIgnoreCase(productName)) {
				product.click();
				break;
			}
		}
		productPage = new ProductPage(driver);
		return productPage;
	}

    public Boolean compareProducts() throws IOException {
        actualProducts = getProducts();
        expectedProducts = ExcelReader.readProductsListExcel("electronicsProductsList");

        return (actualProducts.containsAll(expectedProducts)
                && expectedProducts.containsAll(actualProducts));
    }

    public List<String> getProducts() {
        productNames = new ArrayList<>();

        for(WebElement product:productLinks) {
            productNames.add(product.getText());
        }

        return productNames;
    }

    public Integer numberOfProducts() {
        return products.size();
    }

}
