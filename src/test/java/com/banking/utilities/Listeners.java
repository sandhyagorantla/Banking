package com.banking.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.banking.testcases.BaseClass;



public class Listeners implements ITestListener {
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		System.out.println("test success");
		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
		extenttest.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("test success");
		String logtext = "<b>Test Method " + result.getMethod().getMethodName() + " Successfull</b>";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
		extenttest.get().log(Status.PASS, m);

	}

	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extenttest.get().fail("<details><summary><b><font color=red>Exception Occured,click to see details:"
				+ "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>\n");
		WebDriver driver = ((BaseClass) result.getInstance()).driver;
		String path = takeScreenshot(driver, result.getMethod().getMethodName());
		try {
			extenttest.get().fail("<b><font color=red>" + "screenshot of failure" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {
			extenttest.get().fail("Test failed,cannot attach screenshot");
		}

		String logtext = "<b>Test Method " + methodname + " Failed</b>";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
		extenttest.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		String logtext = "<b>Test Method " + result.getMethod().getMethodName() + " Skipped</b>";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
		extenttest.get().log(Status.SKIP, m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}

	}

	public String takeScreenshot(WebDriver driver, String methodname) {
		String filename = getScreenshotName(methodname);
		String directory = System.getProperty("user.dir") + "/screenshots/";
		new File(directory).mkdirs();
		String path = directory + filename;
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			System.out.println("****************");
			System.out.println("Screenshot stored at" + path);
			System.out.println("****************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public static String getScreenshotName(String methodname) {
		Date d = new Date();
		String filename = methodname + "_" + d.toString().replace(":", "_").replace("", "_") + ".png";
		return filename;

	}

}

	

