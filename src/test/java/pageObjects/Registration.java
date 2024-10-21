package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Registration extends BasePage{
	
//Constructor
	public Registration(WebDriver driver) {	
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
		
		@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")  //for step 6
		WebElement linklogout;
		
		@FindBy(xpath="//a[normalize-space()='Continue']")
		WebElement btnContinueHome;
			
			
	//Action	
		public void setFirstName(String fname) {
			txtFirstName.sendKeys(fname);
		}
		public void setLastName(String lname) {
			txtLastName.sendKeys(lname);
		}
		public void seteMail(String email) {
			txteMail.sendKeys(email);
		}
		public void setPhoneNumber(String phone) {
			txtPhoneNumber.sendKeys(phone);
		}
		public void setPassword(String password) {
			txtPassword.sendKeys(password);
		}
		public void setConfirmPassword(String password) {
			txtConfirmPassword.sendKeys(password);
		}
		public void clickTermsPolicy() {
			chkboxAgree.click();
		}
		
		public void clickContinue() {
			btnContinue.click();    // for click events,there are some options:- solution1
			
		// btnContinue.submit();     // solution2
		
		//Actions act = new Actions(driver);         //solution3
		//act.moveToElement(btnContinue).perform();
			
		//	JavascriptExecutor js = (JavascriptExecutor)driver;   //solution4
		//	js.executeScript("arguments[0].click();", btnContinue);
			
		//	btnContinue.sendKeys(Keys.RETURN);    //solution5
			
		//	WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));    //solution6
		//	mywait.until(ExpectedConditions.elementToBeClickable(btnContinue));
					
		}
		
		public void clicklogout() {
			linklogout.click();
			}
		public void clickHome() {
			btnContinueHome.click();
		}
	//verification
		public String getConfirmation() {
			try {
				return(msgConfirmation.getText());
			}
			catch(Exception e) {
				return e.getMessage();
			}
			
		}

}