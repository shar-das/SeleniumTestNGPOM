package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigFileReader;
import utils.DriverFactory;

public class BaseTest {

    public WebDriver driver;
    protected DriverFactory driverFactory;
    protected String email;
    protected String password;
    String browser, baseUrl;
    boolean isHeadless = false;

    @BeforeMethod
    public void setUp(ITestContext context) {
        browser = ConfigFileReader.getProperty("browser");
        isHeadless = Boolean.parseBoolean(ConfigFileReader.getProperty("headless"));
        baseUrl = ConfigFileReader.getProperty("url");
        email = ConfigFileReader.getProperty("email");
        password = ConfigFileReader.getProperty("password");

        driverFactory = new DriverFactory();
        driver = driverFactory.initDriver(browser, isHeadless);

        context.setAttribute("driver", driver);
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
