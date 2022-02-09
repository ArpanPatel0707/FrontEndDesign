package com.ats.qa.spec.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ats.qa.util.CommonMethods;

public class LoginPageST extends AbstractPageST {
	// Page Factory - OR:
	@FindBy(xpath = "//input[@name='username']")
	WebElement username;

	@FindBy(xpath = "//input[@name='password']")
	WebElement password;

	@FindBy(xpath = "//span[text()='Log In']/ancestor::button[@type='submit']") // (//span[text()='Log In'])[2]
	WebElement loginBtn;

	@FindBy(xpath = "//span[text()='Sign Up']/ancestor::button")
	WebElement signUpTab;

	@FindBy(xpath = "//label[text()='Email: *']/ancestor::div[1]/div/input")
	WebElement emailSignUp;

	@FindBy(xpath = "//label[text()='First Name: *']/ancestor::div[1]/div/input")
	WebElement firstNameSignUp;

	@FindBy(xpath = "//label[text()='Last Name: *']/ancestor::div[1]/div/input")
	WebElement lastNameSignUp;

	@FindBy(xpath = "//label[text()='Password: *']/ancestor::div[1]/div/input")
	WebElement passwordSignUp;

	@FindBy(xpath = "//label[text()='Confirm Password: *']/ancestor::div[1]/div/input")
	WebElement confirmPasswordSignUp;

	@FindBy(xpath = "//label[text()='Company Name *']/ancestor::div[1]/div/input")
	WebElement companyNameSignUp;

	@FindBy(xpath = "//label[text()='Company Address *']/ancestor::div[1]/div/input")
	WebElement companyAddressSignUp;

	@FindBy(xpath = "//label[text()='Country *']/ancestor::div[1]/div/div/input")
	WebElement countrySignUp;

	@FindBy(xpath = "//label[text()='State/Province *']/ancestor::div[1]/div/div/input")
	WebElement provinceSignUp;

	@FindBy(xpath = "//label[text()='City *']/ancestor::div[1]/div/div/input")
	WebElement citySignUp;

	@FindBy(xpath = "//label[text()='Zip/Postal Code *']/ancestor::div[1]/div/input")
	WebElement postalCodeSignUp;

	@FindBy(xpath = "//label[text()='Organization Type *']/ancestor::div[1]/div/div")
	WebElement organizationTypeOptionSignUp;

	@FindBy(xpath = "//li[text()='Engineering Firm']")
	WebElement EngineeringFirmOrganizationSignUp;

	@FindBy(xpath = "//span[text()='sign up']/ancestor::button")
	WebElement signUpButton;

	@FindBy(xpath = "//span[text()='continue']/ancestor::button")
	WebElement continueSignUp;

	@FindBy(xpath = "//span[text()='Next']/ancestor::button")
	WebElement nextSignUp;

	@FindBy(xpath = "//a[text()='return to login page']/ancestor::div[1]/h2")
	WebElement thanksForSigningUpText;

	@FindBy(xpath = "//a[text()='Forget password?']")
	WebElement forgetPassword;

	@FindBy(xpath = "//input[@name=\"email\"]")
	WebElement enterEmail;

	@FindBy(xpath = "//span[text()='Reset Password']/ancestor::button")
	WebElement resetPassword;

	@FindBy(xpath = "//h4[text()='Check Your Email!']/ancestor::div[2]/div[2]/p")
	WebElement resultResetPassword;

	// Initializing the Page Objects:
	public LoginPageST(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(driver, this);
	}

	public String login(String un, String pwd) {
	
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		return DashBoardPageST.getTextDashBoardST().getText();
	}

	
}
