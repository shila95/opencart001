package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.BaseClass;

public class TC2_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})  //Add groups for grouping test
	public void verify_Login()
	{
		logger.info("Starting of TC2_LoginTest...");
		
		try
		{
		//Home Page
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		logger.info("Clicked on My Account link in Home Page..");
		hp.clickLogin();
		logger.info("Clicked on Login link under MyAccount...");
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		logger.info("Providing Details...");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		logger.info("Clicked on Login link under Login Page...");
		
		//MyAccount Page
		MyAccountPage map=new MyAccountPage(driver);
		boolean statusOfPage=map.isMyAccountPageExist();
		Assert.assertEquals(statusOfPage, true, "Login failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("Finishing of TC2_LoginTest...");
		
	}

}
