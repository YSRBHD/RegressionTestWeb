package utils.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import driversFactory.DriverFactory;
import io.qameta.allure.Attachment;

public class TestListner implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("\n=================== Test Suite execution " + iTestContext.getName() + " STARTED ===================\n");
		iTestContext.setAttribute("WebDriver", DriverFactory.getDriver());
		}

	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("\n=================== Test Suite execution " + iTestContext.getName() + " FINISHED ===================\n");
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("=================== Test case execution " + getTestMethodName(iTestResult) + " STARTED ===================");
		System.out.printf("%n");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("=================== Test case execution " + getTestMethodName(iTestResult) + " SUCCEEDED ===================");
		System.out.printf("%n");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("\n=================== Test case execution  " + getTestMethodName(iTestResult) + " FAILED ===================\n");
		System.out.printf("%n");
		@SuppressWarnings("unused")
		Object testClass = iTestResult.getInstance();
		WebDriver driver = DriverFactory.getDriver();
		// Allure ScreenShot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for FAILED test case : " + getTestMethodName(iTestResult));
			System.out.printf("%n");
			saveFailureScreenShot(driver);
		}
		
		saveTextLog(getTestMethodName(iTestResult) + " FAILED and screenshot taken");	
		System.out.printf("%n");
	}
	
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("=================== Test case execution " + getTestMethodName(iTestResult) + " SKIPPED ===================");
		System.out.printf("%n");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
	
}
