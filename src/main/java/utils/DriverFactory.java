package utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {
	
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	public WebDriver initDriver(String browser, boolean isHeadless) {
		
		switch(browser.toLowerCase()) {

	        case "chrome":
	            ChromeOptions chromeOptions = new ChromeOptions();
	            if(isHeadless) chromeOptions.addArguments("--headless=new");
	            chromeOptions.addArguments("--window-size=1920,1080");
	            chromeOptions.addArguments("--disable-features=PasswordLeakDetection");
	            chromeOptions.addArguments("--disable-save-password-bubble");
	            chromeOptions.addArguments("--disable-notifications");
	            
	            Map<String, Object> prefs = new HashMap<>();
	            prefs.put("credentials_enable_service", false);
	            prefs.put("profile.password_manager_enabled", false);

	            chromeOptions.setExperimentalOption("prefs", prefs);
	            
	            tlDriver.set(new ChromeDriver(chromeOptions));
	            break;
	
	        case "edge":
	            EdgeOptions edgeOptions = new EdgeOptions();
	            if(isHeadless) edgeOptions.addArguments("--headless=new");
	            edgeOptions.addArguments("--window-size=1920,1080");
	            tlDriver.set(new EdgeDriver(edgeOptions));
	            break;
	
	        default:
	            throw new RuntimeException("Browser is not supported: " + browser);
		}
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

}
