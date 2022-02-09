package com.ats.qa.spec.testcases;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ats.qa.base.BaseTest;
import com.ats.qa.spec.pages.DashBoardPageST;
import com.ats.qa.spec.pages.LoginPageST;
import com.ats.qa.spec.pages.ManageProjectsPageST;
import com.ats.qa.spec.pages.ManageSpecsPageST;

public class ManageSpecsPageTestST extends BaseTest {

	LoginPageST loginPageST;
	ManageProjectsPageST manageProjectsPageST;
	ManageSpecsPageST manageSpecsPageST;
	DashBoardPageST dashBoardPageST;
	Logger log = Logger.getLogger(ManageProjectsPageTestST.class);

	public ManageSpecsPageTestST() {
		super();
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws MalformedURLException {
		initDriver();
		loginPageST = new LoginPageST(driver);
		dashBoardPageST = new DashBoardPageST(driver);
		manageProjectsPageST = new ManageProjectsPageST(driver);
		manageSpecsPageST = new ManageSpecsPageST(driver);
		loginPageST.login(getUsername(), getPassword());
		ManageProjectsPageST.manageProjectsTabST().click();
		ManageSpecsPageST.manageProjectsSpecificationTabST().click();
	}

	// @Test(priority = 2, groups = { "Special Request Tests" })
	/**
	 * Test that User is able to load spec list in ST
	 */

	public void recordTimeSpecificationList() {
		log.info(
				"----------------------------- Test Number -  User is able to load spec list in ST ---------------------------------------");
		manageSpecsPageST.recordTimeSpecificationList();

	}

	@Test(priority = 0, groups = { "Special Request Tests" })
	/**
	 * Test that User is able to measure performance spec list in ST
	 */

	public void specPerformanceTest() {
		log.info(
				"----------------------------- Test Number -  spec performance test in ST ---------------------------------------");
		manageSpecsPageST.submittalPrintPerformanceTesting();
		manageSpecsPageST.productBrochurePrintPerformanceTesting();
		manageSpecsPageST.schedulePrintPerformanceTesting();
		manageSpecsPageST.longSpecPrintPerformanceTesting();
		manageSpecsPageST.shortSpecPrintPerformanceTesting();
		manageSpecsPageST.manufactureSpecSheetPrintPerformanceTesting();
		manageSpecsPageST.revitPrintPerformanceTesting();
		manageSpecsPageST.cSIPrintPerformanceTesting();

	}

	@Test(priority = 1, groups = { "Spec-tool Smoke Testing" })
	/**
	 * Test that User is able to create spec and verifying of creation new spec by
	 * searching spec on spec list
	 */
	public void createSpecTestST() {
		log.info(
				"----------------------------- Test Number - 16 User is able to create spec and add product through bathtub category in ST ---------------------------------------");
		manageSpecsPageST.createSpecWT(strUUID);
	}



	@Test(priority = 3, groups = { "Spec-tool Regression Testing" }, dependsOnMethods = { "createSpecTestST" })
	/**
	 * Note series in all categories
	 */
	public void seriesInCategoriesTestST() {
		log.info(
				"----------------------------- Test Number -  User is able to Note series in all categories ---------------------------------------");
		manageSpecsPageST.seriesInCategories(strUUID);
	}

	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
