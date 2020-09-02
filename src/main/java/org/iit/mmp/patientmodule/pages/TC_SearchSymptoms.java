package org.iit.mmp.patientmodule.pages;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.EncryptedDocumentException;
import org.iit.mmp.helpers.BaseWebDriver;
import org.iit.mmp.helpers.TestDataManager;
import org.iit.utils.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

public class TC_SearchSymptoms extends BaseWebDriver {

	/**
	 **/
	protected void _openAppURL(String appURL) {
		initiateDriver();
		Logger.log("I", "Opening URL " + appURL);
		driver.get(appURL);
	} // _openAppURL

	/**
	 **/
	protected void _closeAppURL() {
		shutDownDriver();
	} // _closeAppURL

	/**
	 **/
	public void loginPatientPortal(String username, String password) {
		Logger.log("I", "Attempting to login to Patient Portal");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='submit']")).click();
	} // loginPatientPortal

	/**
	 **/
	public void logoutPatientPortal() {
		Logger.log("I", "Attempting to logout from Patient Portal");
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div/ul/li[9]/a/span")).click();
	} // logoutPatientPortal

	public void _navigateSerachSymptoms(String symptomtype) {
		Logger.log("I", "Attempting to navigate to Search Symptoms");
		driver.findElement(By.xpath("//span[contains(text(),'Search Symptoms')]")).click();
		driver.findElement(By.xpath("//input[@id='search']")).clear();
		driver.findElement(By.xpath("//input[@id='search']")).sendKeys(symptomtype);
		driver.findElement(By.xpath(" //input[@name='submit']")).click();
	} // navigateSerachSymptoms

	public void searchSymptoms(String symptomtype) {
		_navigateSerachSymptoms(symptomtype);
		try {
			// -- let page refresh with results
			Thread.sleep(2000);
			Logger.log("I", "Validating search results from search");
			
			WebElement mytable = driver.findElement(By.xpath(" //table[@class='table']"));
			List<WebElement> resultRows = mytable.findElements(By.xpath("//tbody//tr"));
			System.out.println("I have " + resultRows.size() + " record(s) for " + symptomtype);
		} catch (InterruptedException e) {
			System.out.println("Timer interrupted");
		}
	}// searchSymptoms

} // TC_SearchSymptoms
