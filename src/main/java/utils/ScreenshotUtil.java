package utils;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {
	
	public static String takeScreenshot(WebDriver driver, String testName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDateTime = CommonMethods.getCurrentDateTime("dd-MMM-yyyy_hh-mm");
		String path = System.getProperty("user.dir") + "/test-output/screenshots/" + testName + "_" + currentDateTime + ".png";
		FileHandler.copy(src, new File(path));
		return path;
	}

}
