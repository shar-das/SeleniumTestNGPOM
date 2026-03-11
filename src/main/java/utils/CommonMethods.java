package utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
        int waitTime = Integer.parseInt(ConfigFileReader.getProperty("waitTime"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
    }

    public boolean elementIsDisplayed(WebElement element) {
        waitForElement(element);
        return element.isDisplayed();
    }

    public void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
