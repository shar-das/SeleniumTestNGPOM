package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if(extent==null) {
			ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
			
			reporter.config().setReportName("Automation Test Report");
			reporter.config().setDocumentTitle("Test Execution Report");
			
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			
			extent.setSystemInfo("Tester", ConfigFileReader.getProperty("testerName"));
			extent.setSystemInfo("Environment", ConfigFileReader.getProperty("environment"));
			extent.setSystemInfo("Browser", ConfigFileReader.getProperty("browser"));
			extent.setSystemInfo("OS", System.getProperty("os.name"));
		}
		return extent;
	}

}
