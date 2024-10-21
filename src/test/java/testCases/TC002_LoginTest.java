package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Home;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups= {"Master","Sanity"})
	public void loginTest() throws InterruptedException {
		
		logger.info("*** TC002 Login Starting****");
		try {
		Home hp = new Home(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAddress(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLoginbtn();
		
		//verification
		MyAccountPage map= new MyAccountPage(driver);
		boolean targetpage = map.isMyAccountExists();
		
		Assert.assertTrue(targetpage); //Assert.assertEquals(targetpage, true, "Test passed");
		
		map.clicklogout();
		
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*** TC002 Login Finished****");
		Thread.sleep(2000);
	}
	
}
