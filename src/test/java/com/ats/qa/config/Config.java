package com.ats.qa.config;

import static com.ats.qa.config.ConfigProperties.ENV_BROWSER;
import static com.ats.qa.config.ConfigProperties.ENV_BROWSER_PATH;
import static com.ats.qa.config.ConfigProperties.ENV_HEADLESS;
import static com.ats.qa.config.ConfigProperties.ENV_PASSWORD;
import static com.ats.qa.config.ConfigProperties.ENV_UI_PROJECT_URL;
import static com.ats.qa.config.ConfigProperties.ENV_USERNAME;
import static com.ats.qa.config.ConfigProperties.PROP_BROWSER;
import static com.ats.qa.config.ConfigProperties.PROP_BROWSER_PATH;
import static com.ats.qa.config.ConfigProperties.PROP_HEADLESS;
import static com.ats.qa.config.ConfigProperties.PROP_PASSWORD;
import static com.ats.qa.config.ConfigProperties.PROP_UI_PROJECT_URL;
import static com.ats.qa.config.ConfigProperties.PROP_USERNAME;
import static java.lang.System.getenv;

import java.io.IOException;
import java.util.Properties;

public class Config {

	private final String uiProjectUrl;

	private final String browser;

	private final String browserPath;

	private final String username;

	private final String password;

	private final boolean headless;

	private Config(String uiProjectUrl, String browser, String browserPath, String username,
			String password, boolean headless) {
		this.uiProjectUrl = uiProjectUrl;
		this.browser = browser;
		this.browserPath = browserPath;
		this.username = username;
		this.password = password;
		this.headless = headless;
	}

	public String getUiProjectUrl() {
		return this.uiProjectUrl;
	}

	public String getBrowser() {
		return this.browser;
	}

	public String getBrowserPath() {
		return this.browserPath;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public boolean isHeadless() {
		return headless;
	}

	public static class ConfigBuilder {

		private String uiProjectUrl;

		private String browser;

		private String browserPath;

		private String username;

		private String password;

		private boolean headless;

		public Config build() {
			Properties prop = new Properties();
			try {
				prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
			} catch (IOException e) {
				// swallowing the exception here. if no env vars are found there will be a lot
				// of null pointers in the tests. But since its a fallback, will leave as is.
				e.printStackTrace();
			}
			uiProjectUrl = envOrProp(prop, ENV_UI_PROJECT_URL, PROP_UI_PROJECT_URL);
			browser = envOrProp(prop, ENV_BROWSER, PROP_BROWSER);
			browserPath = envOrProp(prop, ENV_BROWSER_PATH, PROP_BROWSER_PATH);
			username = envOrProp(prop, ENV_USERNAME, PROP_USERNAME);
			password = envOrProp(prop, ENV_PASSWORD, PROP_PASSWORD);

			headless = false;
			String strHeadless = envOrProp(prop, ENV_HEADLESS, PROP_HEADLESS);
			if (strHeadless != null) {
				headless = true;
			}

			return new Config(uiProjectUrl, browser, browserPath, username, password, headless);
		}

		private String envOrProp(Properties prop, String envName, String propName) {
			String temp = getenv(envName);
			if (temp == null) {
				temp = prop.getProperty(propName);
			}
			return temp;
		}
	}
}