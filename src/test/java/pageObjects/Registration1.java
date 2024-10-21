package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Registration1 extends BasePage1 {
	
	//constructor
	public Registration1 (WebDriver driver) {
		super(driver);
	}
	//Element Locator
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txteMail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtPhoneNumber;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkboxAgree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	// for verification
			@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
			WebElement msgConfirmation;
			
	//Action
	public void setFirstNames(String fname) {
	txtFirstName.sendKeys(fname);
	}
	public void setLastNames(String lname) {
		txtLastName.sendKeys(lname);
		}
	public void setEmails(String email) {
		txteMail.sendKeys(email);
		}
	public void setPhoneNumbers(String phnum) {
		txtPhoneNumber.sendKeys(phnum);
		}
	public void setPasswords(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	public void setConfirmPasswords(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
		}
	public void clickTermsPolicys() {
		chkboxAgree.click();
	}
	public void clickContinues() {
		btnContinue.click();
	}
	
	public String confmessage() {
			try {
				return(msgConfirmation.getText());
				
			} catch (Exception e) {
				return e.getMessage();
			}
		
	}
}	
	

