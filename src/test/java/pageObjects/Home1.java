package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home1 extends BasePage1 {

//constructor call	
	public Home1(WebDriver driver) {		
		super(driver);
	}
		//locator
		@FindBy(xpath="//span[normalize-space()='My Account']")
		WebElement linkMyAccount;
		
		@FindBy(xpath="//a[normalize-space()='Register']")
		WebElement linkRegister;
		
		//action
		public void clickMyAccount() {
		linkMyAccount.click();
		}
		public void clickRegister() {
		linkRegister.click();
		}
	}
		
