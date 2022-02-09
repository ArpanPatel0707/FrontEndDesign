package com.ats.qa.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ats.qa.base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListeners implements ITestListener {

	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onFinish(ITestContext arg0) {
		if (extent != null) {

			extent.flush();
		}

	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {

		extentTest.get().log(Status.FAIL, "TEST CASE FAILED IS " + arg0.getName()); // to add name in extent report
		extentTest.get().log(Status.FAIL, "TEST CASE FAILED IS " + arg0.getThrowable()); // to add error/exception in
																							// extent report
		WebDriver driver = ((BaseTest) arg0.getInstance()).driver;
		String screenshotPath;

		try {
			screenshotPath = TestListeners.getScreenshot(driver, arg0.getName());
			extentTest.get().addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // adding screen shot

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		extentTest.get().log(Status.SKIP, "Test Case SKIPPED IS " + arg0.getName());

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		ExtentTest test = extent.createTest(arg0.getTestClass().getName() + " :: " + arg0.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		extentTest.get().log(Status.PASS, "Test Case PASSED IS " + arg0.getName());

	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/target/surefire-reports/Screenshots/" + screenshotName
				+ dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
