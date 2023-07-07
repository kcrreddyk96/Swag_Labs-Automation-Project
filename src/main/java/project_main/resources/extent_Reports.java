package project_main.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extent_Reports {
    static String path = System.getProperty("user.dir") + "//src//test//java//project_test//test_reports//reports//testReport.html";

    static ExtentSparkReporter reporter = new ExtentSparkReporter(path);

    static ExtentReports extentreports = new ExtentReports();
    public static ExtentReports get_Reports_Object(){
        //TODO - Reports Formats and UI Changes
        reporter.config().setReportName("Web Application Automation Testing Reports");
        reporter.config().setDocumentTitle("Automation Reports");
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        reporter.config().setTheme(Theme.DARK);

        //TODO - Adding Tester details in the report
        extentreports.attachReporter(reporter);
        extentreports.setSystemInfo("Host","Local_Host");
        extentreports.setSystemInfo("Application Type","Web Application");
        extentreports.setSystemInfo("Application Name", "Swag Labs");
        extentreports.setSystemInfo("System_Environment","Windows 11");
        extentreports.setSystemInfo("Test_Environment","QAF");
        extentreports.setSystemInfo("Tester_Name","KC_Reddy");
        return extentreports;
    }
}
