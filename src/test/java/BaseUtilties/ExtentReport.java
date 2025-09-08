package BaseUtilties;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static ExtentReports getReport()
	{
		String path = System.getProperty("user.dir")+"/Reports/index.html";
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
		extentSparkReporter.config().setDocumentTitle("Automation Practice");
		extentSparkReporter.config().setReportName("Automation E2E");
		
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Test", "Pankaj");
		return extentReports;
	}
}
