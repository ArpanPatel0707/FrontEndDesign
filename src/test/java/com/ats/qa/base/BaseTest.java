package com.ats.qa.base;

import static com.ats.qa.config.ConfigProperties.CHROME;
import static com.ats.qa.config.ConfigProperties.FIREFOX;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.ats.qa.config.Config;
import com.ats.qa.listener.WebEventListener;
import com.ats.qa.util.CommonMethods;

public class BaseTest {

	protected final String strUUID;
	private final Config config;
	public WebDriver driver;

	public BaseTest() {
		config = new Config.ConfigBuilder().build();
		strUUID = CommonMethods.getUUID();
	}

	public void initDriver() throws MalformedURLException {
		if (CHROME.equals(config.getBrowser())) {
			final ChromeOptions options = new ChromeOptions();
			options.setHeadless(config.isHeadless());
			if (!config.getBrowserPath().startsWith("http")) {
				System.setProperty("webdriver.chrome.driver", config.getBrowserPath());
				options.addArguments("--no-sandbox");
				driver = new ChromeDriver(options);
			} else {
				final URL browserUrl = new URL(config.getBrowserPath());
				driver = new RemoteWebDriver(browserUrl, options);
			}

		} else if (FIREFOX.equals(config.getBrowser())) {
			final FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(config.isHeadless());
			if (!config.getBrowserPath().startsWith("http")) {
				System.setProperty("webdriver.gecko.driver", config.getBrowserPath());
				driver = new FirefoxDriver(options);
			} else {
				final URL browserUrl = new URL(config.getBrowserPath());
				driver = new RemoteWebDriver(browserUrl, options);
			}
		}

		final EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		final WebEventListener eventListener = new WebEventListener();
		eventFiringWebDriver.register(eventListener);
		eventFiringWebDriver.manage().window().setSize(new Dimension(1440, 900));
		eventFiringWebDriver.manage().window().maximize(); // setSize(new
		// Dimension(1920, 1080));
		eventFiringWebDriver.manage().deleteAllCookies();
		eventFiringWebDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		eventFiringWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		eventFiringWebDriver.get(config.getUiProjectUrl());
		driver = eventFiringWebDriver;
	}

	public String getUsername() {
		return config.getUsername();
	}

	public String getPassword() {
		return config.getPassword();
	}

}
