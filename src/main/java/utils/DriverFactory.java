package utils;

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
