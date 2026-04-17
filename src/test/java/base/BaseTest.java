package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigFileReader;

public class BaseTest {

    public WebDriver driver;
    protected String email;
    protected String password;
    String browser, baseUrl;
    boolean isHeadless = false;

    @BeforeMethod
    public void setUp() {
        browser = ConfigFileReader.getProperty("browser");
        isHeadless = Boolean.parseBoolean(ConfigFileReader.getProperty("headless"));
        baseUrl = ConfigFileReader.getProperty("url");
        email = ConfigFileReader.getProperty("email");
        password = ConfigFileReader.getProperty("password");

        switch(browser.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if(isHeadless) chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if(isHeadless) edgeOptions.addArguments("--headless=new");
                edgeOptions.addArguments("--window-size=1920,1080");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
