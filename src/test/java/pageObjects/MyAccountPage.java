package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	//construnction
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	//locator
	@FindBy(xpath="//h2[text()='My Account']")   // my account page heading
	WebElement myaccountHeading;
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")  //for step 6
	WebElement linklogout;
	
	//Action
	public boolean isMyAccountExists() {   // using boolean method
		try {
			return (myaccountHeading.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clicklogout() {
		linklogout.click();
		}
}
