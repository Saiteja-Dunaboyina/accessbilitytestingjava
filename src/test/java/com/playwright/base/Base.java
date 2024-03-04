package com.playwright.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.deque.html.axecore.results.AxeResults;
import com.deque.html.axecore.results.Rule;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Base {
	public static ExtentReports extentReports;
	public static ExtentTest test;
	public Playwright playwright;
	public Browser browser;
	public Page page;
	
	
	@BeforeTest
	public void report() {
		extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/extentreports.html");
		extentReports.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("OrangeHrm Report");
		sparkReporter.config().setReportName("Test Report");
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	
	@BeforeSuite
	public void setUp() {
		playwright = Playwright.create();
	}

	@BeforeMethod
	public void navigatePage() {
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		page = browser.newPage();
		page.navigate("https://demo.spreecommerce.org/");
		page.waitForLoadState();
	}
	
	public void getExtentReportViolation(AxeResults axeResults) {
		if (axeResults.getViolations().isEmpty()) {
			test.pass("No Accessbility violations found");
		} else {
			test.fail("Accessibility violations found");
			for (Rule violation : axeResults.getViolations()) {
				test.log(Status.INFO, "Description: " + violation.getDescription());
				test.log(Status.WARNING, "Impact: " + violation.getImpact());
				test.log(Status.FAIL, "Help: " + violation.getHelp());
				test.log(Status.FAIL, "Help URL: " + violation.getHelpUrl());
			}
		}
	}

	
	@AfterMethod
	public void closeBrowser() {
		browser.close();
	}

	@AfterTest
	public void flushReport() {
		extentReports.flush();
	}

	@AfterSuite
	public void tearDownPlaywright() {
		playwright.close();
	}

}
