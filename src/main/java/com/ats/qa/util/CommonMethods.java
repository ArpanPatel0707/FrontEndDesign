package com.ats.qa.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

	private final static String SCREENSHOTS_DIR = "target/surefire-reports/screenshots";

	static Logger logger = Logger.getLogger(CommonMethods.class);

	private CommonMethods() {
		// util classes should have static methods
	}

	public static void takeScreenshot(WebDriver driver, String type, String testName) {
		Calendar calendar = Calendar.getInstance(); // Returns instance with current date and time set
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		String timeStamp = formatter.format(calendar.getTime());
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String targetFileName = new StringBuilder(testName).append("-").append(type).append("-").append(timeStamp)
				.append(".jpg").toString();
		try {
			FileUtils.copyFile(scrFile, Paths.get(SCREENSHOTS_DIR, targetFileName).toFile());
		} catch (IOException e) {
			logger.error("Error taking screenshot", e);
		}
	}

	public static void clickElement(WebElement webElement) throws Exception {
		boolean isClicked = false;
		int attempts = 0;
		while (!isClicked && attempts < 5) {
			try {

				webElement.click();
				isClicked = true;
			} catch (StaleElementReferenceException e) {
				attempts++;
				System.out.println(
						"[DEBUG] StaleElementReference exception caught, trying to locate and click element again");
			}
		}
		if (!isClicked) {
			throw new Exception("Could not click " + webElement + ", after 5 attempts");
		}
	}

	public static void staticTimeWait(long waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String dateStamp() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String dateStamp = formatter.format(calendar.getTime());
		return dateStamp;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void deleteScreenshots() throws IOException {
		Path screenshots = Paths.get(SCREENSHOTS_DIR);
		if (Files.exists(screenshots)) {
			FileUtils.forceDelete(screenshots.toFile());
		}
	}

	public static boolean IsDispayed(By by) {
		try {
			((WebElement) by).isDisplayed();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// explicit wait Visibility Of Element
	public static void explicitTimeWaitVisibilityOfElement(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
	}

	// explicit wait Clickable Element
	public static void explicitTimeWaitClickableElement(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
	}

	// explicit wait for clickable element
	public static void explicitTimeWaitClick(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	// explicit wait for sendkey element
	public static void explicitTimeWaitSendKeys(WebDriver driver, WebElement element, int timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

}
