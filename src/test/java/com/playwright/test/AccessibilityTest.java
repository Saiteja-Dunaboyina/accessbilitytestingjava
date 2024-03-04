package com.playwright.test;

import org.testng.annotations.Test;

import com.deque.html.axecore.playwright.AxeBuilder;
import com.deque.html.axecore.results.AxeResults;
import com.playwright.base.Base;

public class AccessibilityTest extends Base {

	@Test(priority = 1)
	public void scanHomePage() {
		test = extentReports.createTest("Spree Commerce Home Page").assignAuthor("Sai teja");
		page.waitForTimeout(3000);
		AxeResults axeResults = new AxeBuilder(page).analyze();
		getExtentReportViolation(axeResults);
	}

	@Test(priority = 2)
	public void scanLoginPage() {
		test = extentReports.createTest("Spree Commerce Login Page").assignAuthor("Sai teja");
		page.locator("//button[@id='account-button']").click();
		page.locator("//a[text()='LOGIN']").click();
		page.waitForTimeout(3000);
		AxeResults axeResults = new AxeBuilder(page).analyze();
		getExtentReportViolation(axeResults);
	}

	@Test(priority = 3)
	public void scanSignUpPage() {
		test = extentReports.createTest("Spree commerce SignUp Page").assignAuthor("Sai Teja");
		page.locator("//button[@id='account-button']").click();
		page.locator("//a[text()='SIGN UP']").click();
		page.waitForTimeout(3000);
		AxeResults axeResults = new AxeBuilder(page).analyze();
		getExtentReportViolation(axeResults);
	}

	@Test(priority = 4)
	public void scanMyAccountPage() {
		test = extentReports.createTest("Spree commerce My Account Page").assignAuthor("Sai Teja");
		page.locator("//button[@id='account-button']").click();
		page.locator("//a[text()='LOGIN']").click();
		page.locator("//input[@id='spree_user_email']").fill("demouser@gmail.com");
		page.locator("//input[@id='spree_user_password']").fill("Demouser@56");
		page.locator("//input[@type='submit']").click();
		page.waitForTimeout(3000);
		AxeResults axeResults = new AxeBuilder(page).analyze();
		getExtentReportViolation(axeResults);
	}

	@Test(priority = 5)
	public void scanMenClothesPage() {
		test = extentReports.createTest("Spree commerce Mens Clothes Page").assignAuthor("Sai Teja");
		page.locator("//button[@id='account-button']").click();
		page.locator("//a[text()='LOGIN']").click();
		page.locator("//input[@id='spree_user_email']").fill("demouser@gmail.com");
		page.locator("//input[@id='spree_user_password']").fill("Demouser@56");
		page.locator("//input[@type='submit']").click();
		page.locator("(//a[text()='Men'])[1]").click();
		page.waitForTimeout(3000);
		AxeResults axeResults = new AxeBuilder(page).analyze();
		getExtentReportViolation(axeResults);
	}

}
