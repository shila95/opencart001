package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[normalize-space()='My Account']") // MyAccount Page heading
	WebElement titl_MyAccount;

	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement titl_Logout;

	public boolean isMyAccountPageExist() // MyAccount Page heading display status
	{
		try {
			return (titl_MyAccount.isDisplayed());
		} 
		catch (Exception e) {
			return false;
		}
	}

	public void clickLogout() {
		titl_Logout.click();
	}
}
