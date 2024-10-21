package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home extends BasePage {
	
//Constructor
	public Home(WebDriver driver) {		
		super(driver);			
	}

// Element locator
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement linkMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")     // add login page from point no. 5
	WebElement linkLogin;

// Action
	public void clickMyAccount() {
		
		linkMyAccount.click();
	}
	
	public void clickRegister() {
		
		linkRegister.click();
	}
	 
	public void clickLogin() {        // add login page action
		linkLogin.click();
	}
	
	
}
