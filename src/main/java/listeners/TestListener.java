package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_AMBER = "\u001B[33m";

	private static final Logger logger = LogManager.getLogger(TestListener.class);

	ExtentReports extent = ExtentManager.getInstance();
	ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	private String colorizeStatus(String status) {
		return switch (status) {
			case "STARTED" -> ANSI_BLUE + status + ANSI_RESET;
			case "PASSED" -> ANSI_GREEN + status + ANSI_RESET;
			case "FAILED" -> ANSI_RED + status + ANSI_RESET;
			case "SKIPPED" -> ANSI_AMBER + status + ANSI_RESET;
			default -> status;
		};
	}
	
	private void attachScreenshot(WebDriver driver, String methodName) {
		try {
			String screenshotPath = ScreenshotUtil.takeScreenshot(driver, methodName);
			test.get().addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			logger.error("Failed to capture screenshot - " + e.getMessage());
		}
		
	}
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println();
		System.out.println("TEST EXECUTION STARTED: " + context.getName());
		System.out.println("---------------------------------------------------------------------------------------------");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		logger.info("{}: {}", colorizeStatus("STARTED"), result.getMethod().getMethodName());
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		
		for(String group:result.getMethod().getGroups())
			extentTest.assignCategory(group);
		
		test.set(extentTest);
		test.get().info("Test started");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("{}: {}", colorizeStatus("PASSED"), result.getMethod().getMethodName());
		System.out.println("---------------------------------------------------------------------------------------------");
		test.get().pass("Test passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("{}: {}", colorizeStatus("FAILED"), result.getMethod().getMethodName());
		System.out.println("---------------------------------------------------------------------------------------------");
		test.get().fail(result.getThrowable());
		
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		attachScreenshot(driver, result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("{}: {}", colorizeStatus("SKIPPED"), result.getMethod().getMethodName());
		System.out.println("---------------------------------------------------------------------------------------------");
		test.get().skip("Test skipped");
		
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		attachScreenshot(driver, result.getMethod().getMethodName());
	}
	

	@Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
