package org.iit.mmp.patientmodule.pages;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.iit.mmp.helpers.BaseWebDriver;
import org.iit.mmp.helpers.TestDataManager;
import org.iit.utils.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

public class TC_SearchSymptoms  extends BaseWebDriver {
	
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

	public void navigateSerachSymptoms() {
		Logger.log("I", "Attempting to navigate to Search Symptoms");
		driver.findElement(By.xpath("//span[contains(text(),'Search Symptoms')]")).click();
	} //navigateSerachSymptoms
	
	public void searchSymptoms(String  symptomtype ) {
		Logger.log("I", "Attempting to Search Symptoms");
		driver.findElement(By.xpath("//input[@id='search']")).clear();
		driver.findElement(By.xpath("//input[@id='search']")).sendKeys(symptomtype);
		driver.findElement(By.xpath(" //input[@name='submit']")).click();
		
		 //To locate table.
		WebElement mytable = driver.findElement(By.xpath("//table[@class='table']//tbody"));
		
		//To locate rows of table.
		List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
		
		//To calculate no of rows In table.
		  int rows_count = rows_table.size();
			System.out.println(rows_count);
		
		if(rows_count  >=  1) {
			System.out.println("Symptoms Found for "  +   driver.findElement(By.xpath("//input[@id='search']")).getAttribute("value"));
		}
		else {
			System.out.println("Symptoms Not Found" + driver.findElement(By.xpath("//input[@id='search']")).getAttribute("value"));
		}
			
	}
}//searchSymptoms
