package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Home;
import pageObjects.Registration;
import testBase.BaseClass;

public class TC001_UserRegistration extends BaseClass {
	
	@Test(groups= {"Master", "Regression"})
	public void verify_TC001_UserRegistration() throws InterruptedException  {
		logger.info("for verify_TC001_UserRegistration..");
		
		try {			
			logger.info("open registration page from Home page..");	
			
		Home hm = new Home(driver);
		hm.clickMyAccount();
		hm.clickRegister();
		
		logger.info("user details for registration ..");
		
		Registration reg=new Registration(driver);
		reg.setFirstName(randomeString().toUpperCase());
		reg.setLastName(randomeString().toUpperCase());
		reg.seteMail(randomeString()+"@gmail.com");
		reg.setPhoneNumber(randomeNumber());
		
		String pwd=randomePassword();
		reg.setPassword(pwd);
		reg.setConfirmPassword(pwd);
		reg.clickTermsPolicy();
		reg.clickContinue();
		
		logger.info("for validation of message..");
		
		String confmessage = reg.getConfirmation();
		//Assert.assertEquals(confmessage, "Your Account Has Been Created!");
		
		if(confmessage.equals("Your Account Has Been Created!"))   // add 2 !! fail test
			{
			
			Assert.assertTrue(true);
			
			}
		else {
			logger.error("Test Failed");
			logger.debug("debug logs..");
			Assert.assertTrue(false);
			
			}
		Thread.sleep(2000);
	}
		catch(Exception e){
			Assert.fail();
			}
		
		logger.info("***** Finish TC001_UserRegistration*****..");	
		
}
	
}
