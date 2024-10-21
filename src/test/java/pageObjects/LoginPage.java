package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	//constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	//Element locator
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmailAddress;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	//Action
	
	public void setEmailAddress(String email) {
		txtEmailAddress.sendKeys(email);	
	}
	public void setPassword(String pass) {
	txtPassword.sendKeys(pass);
	}
	
	public void clickLoginbtn() {
		btnLogin.click();
	}

}
