package project_test.test_components;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import project_main.resources.extent_Reports;

import java.io.IOException;

public class testNG_Listeners extends base_test implements ITestListener {
    ExtentTest extentTest;

    ExtentReports extentReports = extent_Reports.get_Reports_Object(); // TODO - Extent Method from project resources

    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();  //TODO - Thread Safe for Parallel Testing

    String file_Path = null;

    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
        threadLocal.set(extentTest); //TODO - Assigning Unique Thread ID for every Test
    }

    public void onTestSuccess(ITestResult result) {
        threadLocal.get().log(Status.PASS, "Test has Passed "); // TODO - Showing Status
    }

    public void onTestFailure(ITestResult result) {
        threadLocal.get().log(Status.FAIL, "Test has Failed "); // TODO - Showing Status
        threadLocal.get().fail(result.getThrowable()); // TODO - Shows Error Message in the Reports

        try{ //TODO - getScreenshot, Attach in the Reports
            driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            file_Path = getScreenShot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        threadLocal.get().addScreenCaptureFromPath(file_Path, result.getMethod().getMethodName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test has Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {
        System.out.println("Test has Started");
    }

    public void onFinish(ITestContext context) {
        System.out.println("Test has Completed");
        extentReports.flush(); //TODO - Monitoring will be closed
    }

}