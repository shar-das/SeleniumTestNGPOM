package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {
	
	ExtentReports extent = ExtentManager.getInstance();
	ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println();
		System.out.println("Test Execution Started!");
		System.out.println("----------------------------------------");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " passed!");
		System.out.println("----------------------------------------");
		test.get().pass("Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " failed!");
		System.out.println("----------------------------------------");
		test.get().fail(result.getThrowable());
		
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		try {
			String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
			test.get().addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " skipped!");
		System.out.println("----------------------------------------");
		test.get().skip("Skipped");
	}
	

	@Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("Test Execution Finished!");
    }

}
