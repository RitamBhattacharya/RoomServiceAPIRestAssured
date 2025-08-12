package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

public class ExtentManager {
    private static ExtentReports extent; // Singleton instance of ExtentReports

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            String reportDir = "target/ExtentReports"; // Report output directory
            new File(reportDir).mkdirs(); // Create directory if missing
            String file = reportDir + File.separator + "RoomServiceApiReport.html"; // Report file path

            // Initialize and configure Spark reporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
            sparkReporter.config().setDocumentTitle("Room Service API Automation Report"); // HTML report title
            sparkReporter.config().setReportName("Room Service API Test Results"); // Report name in UI
            sparkReporter.config().setTheme(Theme.STANDARD); // Set theme

            // Attach reporter and set environment info
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
        }
        return extent; // Return single instance
    }
}
