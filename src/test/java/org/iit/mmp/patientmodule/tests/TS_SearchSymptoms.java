package org.iit.mmp.patientmodule.tests;

import org.testng.annotations.Test;
import org.iit.mmp.patientmodule.pages.TC_SearchSymptoms;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TS_SearchSymptoms extends TC_SearchSymptoms {

	/**
	 * Static declarations
	 **/
	private static String appURL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
	private static String username = "ria1";
	private static String password = "Ria12345";
	

	/* ```````````````````````````````````````````````````````````````` */
	@Test(priority = 1)
	public void login() {
		loginPatientPortal(username, password);
	} // login

	@Test(priority = 4)
	public void logout() {
		logoutPatientPortal();
	} // logout

	@Test(priority = 2)
	public void searchSymptoms() {
		searchSymptoms("fever");
		searchSymptoms("cough");
	} // searchSymptoms
	

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

} // TS_SearchSymptoms
