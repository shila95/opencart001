package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.RegisterAccountPage;
import TestBase.BaseClass;

public class TC1_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"Regression" ,"Master"})  //Add groups for grouping test
	public void verify_AccountRegistration() {

		logger.info("Starting TC1_AccountRegistrationTest...");

		try {
			HomePage hp = new HomePage(driver);

			logger.info("Clicking MyAccount...");
			hp.clickMyaccount();

			logger.info("Clicking Registration Page...");
			hp.clickRegister();

			RegisterAccountPage regAcc = new RegisterAccountPage(driver);

			logger.info("providing persolan details...");
			regAcc.setFirstName(randomString().toUpperCase());

			regAcc.setLastName(randomString().toUpperCase());

			regAcc.setEmail(randomAlphaNumber() + "@gmail.com");

			regAcc.setTele(randomNumber());

			String password = randomString();
			regAcc.setPassword(password);
			regAcc.setConPass(password);

			regAcc.clickAgree();

			logger.info("Clicking Continue button...");
			regAcc.clickConti();

			logger.info("Validating Expected massage...");
			String conMsg = regAcc.getConfirmationMsg();
			
			// Assert.assertEquals(conMsg, "Your Account Has Been Created!");
			Assert.assertEquals(conMsg, "Your Account Has Been Created!", "Confirmation message mismatch");

			logger.info("Test Passed...");
		}

		catch (Exception e) {
			logger.error("Test Failed: " + e.getMessage());
			Assert.fail("Test Failed: " + e.getMessage());
		}

		finally {
			logger.info("Finished TC1_AccountRegistrationTest...");
		}
	}

}
