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
		String screenshotsDir = System.getProperty("user.dir") + "/test-output/screenshots";
		File targetDirectory = new File(screenshotsDir);

		if (!targetDirectory.exists() && !targetDirectory.mkdirs()) {
			throw new IOException("Unable to create screenshot directory: " + targetDirectory.getAbsolutePath());
		}

		File targetFile = new File(targetDirectory, testName + "_" + currentDateTime + ".png");
		FileHandler.copy(src, targetFile);
		return targetFile.getAbsolutePath();
	}

}
