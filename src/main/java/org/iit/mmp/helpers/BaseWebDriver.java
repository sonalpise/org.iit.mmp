package org.iit.mmp.helpers;

import java.util.concurrent.TimeUnit;

import org.iit.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseWebDriver {

	public WebDriver driver;

	/**
	 **/
	public void initiateDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// setup driver configurations
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		Logger.log("I", "Webdriver initiated");
	} // initiateDriver

	/**
	 **/
	public void shutDownDriver() {
		driver.quit();
		Logger.log("I", "Webdriver shut down");
	} // shutDownDriver

} // BaseWebDriver
