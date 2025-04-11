package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterAccountPage extends BasePage {

	// Constructor
	public RegisterAccountPage(WebDriver driver) {
		super(driver);

	}

	// Locators
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txt_FirstNm;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txt_LasrNm;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_Email;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txt_Telephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_Password;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txt_ConPass;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement btn_Policy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btn_Cont;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msg_confirmation;

	// Action Methods
	public void setFirstName(String fname) {
		txt_FirstNm.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txt_LasrNm.sendKeys(lname);
	}

	public void setEmail(String email) {
		txt_Email.sendKeys(email);
	}

	public void setTele(String No) {
		txt_Telephone.sendKeys(No);
	}

	public void setPassword(String password) {
		txt_Password.sendKeys(password);
	}

	public void setConPass(String pass) {
		txt_ConPass.sendKeys(pass);
	}

	public void clickAgree() {
		btn_Policy.click();
	}

	public void clickConti() {
		btn_Cont.click();
	}

	public String getConfirmationMsg() {
		try {
			return (msg_confirmation.getText());
		}

		catch (Exception e) {
			return (e.getMessage());
		}
	}

}
