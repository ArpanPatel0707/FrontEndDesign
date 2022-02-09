package com.ats.qa.spec.testcases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ats.qa.base.BaseTest;
import com.ats.qa.spec.pages.EmailVerificationOnGmailST;
import com.ats.qa.spec.pages.LoginPageST;
import com.ats.qa.util.CommonMethods;

public class LoginPageTestST extends BaseTest {

	LoginPageST loginPageST;
	EmailVerificationOnGmailST emailVerificationOnGmailST;
	Logger log = Logger.getLogger(LoginPageTestST.class);

	public LoginPageTestST() {
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		initDriver();
		loginPageST = new LoginPageST(driver);
		CommonMethods.deleteScreenshots();
	}

	@Test(priority = 1, groups = { "Always Run Smoke Testing" })
	/**
	 * Test that User is able to login in SpecTool by verifying "DashBoard" text on
	 * DashBoard page after successful login
	 */
	public void loginTestST() throws Exception {
		log.info(
				"----------------------------- ST Test Number - 1 User is able to login in SpecTool ---------------------------------------");
		String actualResultLoginTest = loginPageST.login(getUsername(), getPassword());
		String expectedResultLoginTest = "Dashboard";
		Assert.assertEquals(actualResultLoginTest, expectedResultLoginTest);
	}

	@Test(priority = 2, groups = { "Always Run Smoke Testing" })
	/**
	 * Test that User is able to sign in SpecTool by verifying "Thanks for signing
	 * up" text after successful registration + Manual Effort to check email on
	 * testatsspectool@zohomail.com and register user
	 */
	public void signUpTestST() throws IOException {
		log.info(
				"----------------------------- ST Test Number - 2 User is able to sign up in SpecTool---------------------------------------");
		String actualResultLoginTest = loginPageST.signUp("newcreateduser09", strUUID);
		// String expectedResultLoginTest = "testatsspectool" + "+" + strUUID;
		String expectedResultLoginTest = "Check Your Email";
		Assert.assertEquals(actualResultLoginTest, expectedResultLoginTest);
	}

	@Test(priority = 3, groups = { "Always Run Smoke Testing" })
	/**
	 * Test that User is able to reset password in Spec tool by verifying text of
	 * "Make sure you click the link in the email to reset your password."
	 */
	public void forgetPasswordTestST() throws IOException {
		log.info(
				"----------------------------- ST Test Number - 15 User is able to reset password in ST---------------------------------------");
		String actualResult = loginPageST.resetPassword();
		String expectedResult = "Make sure you click the link in the email to reset your password.";
		actualResult.contains(expectedResult);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
