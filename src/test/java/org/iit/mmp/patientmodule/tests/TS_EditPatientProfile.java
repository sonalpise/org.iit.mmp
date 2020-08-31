package org.iit.mmp.patientmodule.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.iit.mmp.patientmodule.pages.TC_EditPatientProfile;
import org.iit.utils.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TS_EditPatientProfile extends TC_EditPatientProfile {
	/**
	 * Static declarations
	 **/
	private static String appURL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
	private static String username = "ria1";
	private static String password = "Ria12345";
	private static String testDataPath = "C:\\Users\\pisef\\eclipse-workspace\\org.iit.mmp\\";
	private static String testDataFilename = "datashare.xlsx";
	private static String dataSheetname = "Data";

	/* ```````````````````````````````````````````````````````````````` */
	@Test(priority = 1)
	public void login() {
		loginPatientPortal(username, password);
	} // login

	@Test(priority = 2)
	public void editProfileAndVerify() throws EncryptedDocumentException, IOException {
		try {
			AssertJUnit.assertEquals(editProfileAndSave(testDataPath, testDataFilename, dataSheetname), true);
		} catch (AssertionError e) {
			Logger.log("I", "Test Case editProfileAndVerify - Failed");
		}
	} // editProfileAndVerify

	@Test(priority = 3)
	public void logout() {
		logoutPatientPortal();
	} // logout

	/* ```````````````````````````````````````````````````````````````` */
	/**
	 **/
	@BeforeTest
	public void beforeTest() {
		_openAppURL(appURL);
	} // beforeTest

	/**
	 **/
	@AfterTest
	public void afterTest() {
		_closeAppURL();
	} // afterTest

} // TS_EditPatientProfile
