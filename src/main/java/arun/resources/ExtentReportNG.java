package arun.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"/report1/good.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setDocumentTitle("Automation Tester");
		report.config().setReportName("Ecommerce Application");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "Arun Natikar");
		return extent;	
	}

}
