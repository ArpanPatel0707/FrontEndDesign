package com.ats.qa.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	static ExtentReports createInstance() {
		ExtentHtmlReporter htmlReporter;
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/target/surefire-reports/extent-report.html");

		htmlReporter.config().setDocumentTitle("Allied Technical Solutions"); // Tile of report
		htmlReporter.config().setReportName("ATS Test Automation Report"); // Name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// Passing General information
		extent.setSystemInfo("Host name", "Allied Technical Solutions");
		extent.setSystemInfo("Environemnt", "UAT");
		extent.setSystemInfo("user", "ATS QA Team");

		return extent;

	}

}
