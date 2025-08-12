package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            String reportDir = "target/ExtentReports";
            new File(reportDir).mkdirs();
            String file = reportDir + File.separator + "RoomServiceApiReport.html";

            // Use ExtentSparkReporter in ExtentReports 5+
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
            sparkReporter.config().setDocumentTitle("Room Service API Automation Report");
            sparkReporter.config().setReportName("Room Service API Test Results");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
