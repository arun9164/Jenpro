package arun.components;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import arun.resources.ExtentReportNG;


public class Listeners extends Base implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		//get method name
		 test = extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test); // Unique thred id (of error message);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// no need to mention  (Optional)
		test.log( Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//must and should add it 
		test.fail(result.getThrowable());
		 try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		 
		
//		try {
//	        Field driverField = result.getTestClass().getRealClass().getDeclaredField("driver"); // Use getDeclaredField
//	        driverField.setAccessible(true); // Allow access to private/protected fields
//	        driver = (WebDriver) driverField.get(result.getInstance());
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
		 String filePath =  null;
		try {
			 filePath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
	        extentTest.get().log(Status.FAIL, "Screenshot capture failed due to: " + e.getMessage());
			e.printStackTrace();
		}
		 test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}

