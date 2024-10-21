package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.Home1;
import pageObjects.Registration1;

public class TC001_UserRegistration1 {
	public WebDriver driver;
		
		@BeforeTest
		public void setup() {
			
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
			driver.manage().window().maximize();
			
		}
			@AfterTest
			public void teardown() {
				driver.close();
			}
			
			@Test
			public void verify_UserRegistration1() {
				
				Home1 hmm= new Home1(driver);
				hmm.clickMyAccount();
				hmm.clickRegister();
						
				Registration1 regis= new Registration1(driver);
				regis.setFirstNames("john");
				regis.setLastNames("wick");
				regis.setEmails("ab@abc.com");
				regis.setPhoneNumbers("1231231231");
				regis.setPasswords("john@123");
				regis.setConfirmPasswords("john@123");
				regis.clickTermsPolicys();
				regis.clickContinues();
				
				String cmesg = regis.confmessage();
				Assert.assertEquals(cmesg, "Your Account Has Been Created!");
				
			}
	}


