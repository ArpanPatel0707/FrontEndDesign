package com.ats.qa.spec.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPageST {

	protected static WebDriver driver;

	protected AbstractPageST(WebDriver webDriver) {
		driver = webDriver;
	}

	protected static WebDriverWait webDriverWait() {
		return new WebDriverWait(driver, 15);
	}

}
