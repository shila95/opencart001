package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.BaseClass;
import Utilities.DataProviders;


public class TC3_LoginTestDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verify_loginDDT(String email, String password, String exp_res) {
		logger.info("Starting of TC3_LoginTestDDT...");

		try {
			// Home Page
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			hp.clickLogin();  //Login link under MyAccount

			// Login Page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin(); //Login button

			// MyAccount Page
			MyAccountPage map = new MyAccountPage(driver);
			boolean statusOfPage = map.isMyAccountPageExist();

			if (exp_res.equalsIgnoreCase("Valid")) 
			{
				if (statusOfPage == true)
				{
					map.clickLogout();
					Assert.assertTrue(true);
				}
				else 
				{
					Assert.assertTrue(false);
				}
			}

			if (exp_res.equalsIgnoreCase("Invalid")) 
			{
				if (statusOfPage == true) 
				{
					map.clickLogout();
					Assert.assertTrue(false);
				} 
				else 
				{
					Assert.assertTrue(true);
				}
			}
		} 
		catch (Exception e) {
			Assert.fail("An Exception Occurred: " + e.getMessage());
		}
		
		logger.info("**** Finished TC3_LoginTestDDT *****");


	}

}

/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

/*
public class TC3_LoginTestDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verify_loginDDT(String email, String password, String exp) {
		logger.info("**** Starting TC3_LoginTestDDT *****");

		try {

			// Home page
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			hp.clickLogin(); // Login link under MyAccount

			// Login page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin(); // Login button

			// My Account Page
			MyAccountPage map = new MyAccountPage(driver);
			boolean targetPage = map.isMyAccountPageExist();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					map.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					map.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e.getMessage());
		}

		logger.info("**** Finished TC3_LoginTestDDT *****");
	}

}*/
