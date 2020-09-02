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
import org.openqa.selenium.support.ui.Select;


public class TC_PayFees  extends BaseWebDriver{
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

	public void navigateFees() {
		Logger.log("I", "Attempting to navigate to Fees");
		driver.findElement(By.xpath("//span[contains(text(),'Fees')]")).click();
	} //navigateFees
	
	public void payFees() {
		
		Logger.log("I", "Attempting to pay  Fees");
		
		driver.findElement(By.xpath("//button[contains(text(),'Pay Now')]")).click();
		
		int selection = 1;
		Select payamt = new Select(driver.findElement(By.id("amount")));
		payamt.selectByIndex(selection);
		
	    driver.findElement(By.xpath("//div[@class='panel-body nopadding']//form//p//input")).click();
	    
	//    if(driver.findElement(By.xpath("//p[contains(text(),'$50')]")))
	    driver.findElement(By.xpath(" //input[@id='name']")).sendKeys("First  Last");
	    
		Select card = new Select(driver.findElement(By.id("card_name")));
		card.selectByIndex(1);
		
		driver.findElement(By.xpath("//input[@id='cid']")).sendKeys("4222222222222");
		
		Select month = new Select(driver.findElement(By.xpath("//select[@id='cardMonth']")));
		month.selectByIndex(1);
		
		Select year = new Select(driver.findElement(By.xpath("//select[@id='cardYear']")));
		year.selectByIndex(3);
		
		driver.findElement(By.id("cvv")).sendKeys("233");
		
		driver.findElement(By.xpath("//form[@id='myform']//p//input")).click();
		
		System.out.println("Fees Paid Successfully");
		
	}// payFees
	
	
	
} // TC_PayFees
