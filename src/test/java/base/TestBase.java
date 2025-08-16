package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import org.testng.annotations.*;
import utils.ConfigLoader;

public class TestBase {
	protected static ExtentReports extent; // Shared ExtentReports instance
	protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>(); // Thread-safe test object

	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance(); // Initialize ExtentReports
		RestAssured.baseURI = ConfigLoader.get("Base_URL");// Set base URI for RestAssured
	}

	@AfterSuite
	public void afterSuite() {
		if (extent != null)
			extent.flush(); // Generate the report after all tests
	}

	protected ExtentTest getTest() {
		return test.get(); // Get current test instance
	}

	@BeforeMethod
	public void beforeMethod(java.lang.reflect.Method method) {
		test.set(extent.createTest(method.getName())); // Create new test in the report
	}

	@AfterMethod
	public void afterMethod(org.testng.ITestResult result) {
		if (result.getStatus() == org.testng.ITestResult.FAILURE) {
			getTest().fail(result.getThrowable()); // Log failure
		} else if (result.getStatus() == org.testng.ITestResult.SUCCESS) {
			getTest().pass("Test passed"); // Log success
		} else if (result.getStatus() == org.testng.ITestResult.SKIP) {
			getTest().skip("Test skipped"); // Log skipped
		}
	}
}
