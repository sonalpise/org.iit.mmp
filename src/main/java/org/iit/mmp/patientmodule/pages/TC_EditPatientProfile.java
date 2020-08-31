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
import org.openqa.selenium.NoAlertPresentException;

public class TC_EditPatientProfile extends BaseWebDriver {

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

	/**
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 **/
	public boolean editProfileAndSave(String testDataPath, String testDataFilename, String dataSheetname)
			throws EncryptedDocumentException, IOException {
		Logger.log("I", "Attempting to edit Patient profile and save");
		driver.findElement(By.xpath("//a[@href='profile.php']")).click();

		// -- read test data from file
		HashMap<String, List<String>> testData = TestDataManager
				.readDataFromExcel(new File(testDataPath + testDataFilename), dataSheetname);

		// -- updating new values
		int numRecords = testData.get("SSN").size();
		Logger.log("I", "Dataset contain " + numRecords + " records");

		Boolean allRecordsUpdated = true;
		for (int i = 0; i < numRecords; i++) {
			Logger.log("I", " ... working on record # " + i);

			// -- save current state of data values
			HashMap<String, String> currentData = new HashMap<String, String>();
			currentData.put("fname", driver.findElement(By.id("fname")).getAttribute("value"));
			currentData.put("lname", driver.findElement(By.id("lname")).getAttribute("value"));
			currentData.put("licn", driver.findElement(By.id("licn")).getAttribute("value"));
			currentData.put("ssn", driver.findElement(By.id("ssn")).getAttribute("value"));
			currentData.put("addr", driver.findElement(By.id("addr")).getAttribute("value"));
			currentData.put("age", driver.findElement(By.id("age")).getAttribute("value"));
			currentData.put("weight", driver.findElement(By.id("weight")).getAttribute("value"));
			currentData.put("height", driver.findElement(By.id("height")).getAttribute("value"));
			currentData.put("city", driver.findElement(By.id("city")).getAttribute("value"));
			currentData.put("state", driver.findElement(By.id("state")).getAttribute("value"));
			currentData.put("zip", driver.findElement(By.id("zip")).getAttribute("value"));
			currentData.put("proinfo", driver.findElement(By.id("proinfo")).getAttribute("value"));
			currentData.put("Insinfo", driver.findElement(By.id("Insinfo")).getAttribute("value"));

			// -- enable edit mode
			driver.findElement(By.id("Ebtn")).click();

			// -- change values from test data
			driver.findElement(By.id("fname")).clear();
			driver.findElement(By.id("fname")).sendKeys(testData.get("Firstname").toArray()[i].toString());
			driver.findElement(By.id("lname")).clear();
			driver.findElement(By.id("lname")).sendKeys(testData.get("Lastname").toArray()[i].toString());
			driver.findElement(By.id("licn")).clear();
			driver.findElement(By.id("licn")).sendKeys(testData.get("License").toArray()[i].toString());
			driver.findElement(By.id("ssn")).clear();
			driver.findElement(By.id("ssn")).sendKeys(testData.get("SSN").toArray()[i].toString());
			driver.findElement(By.id("addr")).clear();
			driver.findElement(By.id("addr")).sendKeys(testData.get("Address").toArray()[i].toString());
			driver.findElement(By.id("age")).clear();
			driver.findElement(By.id("age")).sendKeys(testData.get("Age").toArray()[i].toString());
			driver.findElement(By.id("weight")).clear();
			driver.findElement(By.id("weight")).sendKeys(testData.get("Weight").toArray()[i].toString());
			driver.findElement(By.id("height")).clear();
			driver.findElement(By.id("height")).sendKeys(testData.get("Height").toArray()[i].toString());
			driver.findElement(By.id("city")).clear();
			driver.findElement(By.id("city")).sendKeys(testData.get("City").toArray()[i].toString());
			driver.findElement(By.id("state")).clear();
			driver.findElement(By.id("state")).sendKeys(testData.get("State").toArray()[i].toString());
			driver.findElement(By.id("zip")).clear();
			driver.findElement(By.id("zip")).sendKeys(testData.get("Zipcode").toArray()[i].toString());
			driver.findElement(By.id("proinfo")).clear();
			driver.findElement(By.id("proinfo")).sendKeys(testData.get("Proinfo").toArray()[i].toString());
			driver.findElement(By.id("Insinfo")).clear();
			driver.findElement(By.id("Insinfo")).sendKeys(testData.get("Issuranceinfo").toArray()[i].toString());

			// -- save
			driver.findElement(By.id("Sbtn")).click();

			try {
				// -- validate alert message
				Alert alert = driver.switchTo().alert();
				if (alert.getText().contains("has been updated")) {
					alert.accept();

					// -- validate data on form with previous values
					Boolean valuesUpdated = false;
					for (String key : currentData.keySet()) {
						if (!currentData.get(key).equals(driver.findElement(By.id(key)).getAttribute("value"))) {
							valuesUpdated = true;
						}
					} // loop over all current data elements
					if (!valuesUpdated) {
						allRecordsUpdated = false;
						Logger.log("W", "Record # " + i + " was not updated.");
					}
				} else {
					Logger.log("W", "Alert message box says: " + alert.getText());
					return false;
				}
			} catch (NoAlertPresentException e) {
				Logger.log("W", "Form has errors, cannot save");
				return false;
			}
		} // loop all records

		return allRecordsUpdated;
	} // editProfileAndSave

} // TC_EditPatientProfile
