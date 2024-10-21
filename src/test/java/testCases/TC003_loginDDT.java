package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Home;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_loginDDT extends BaseClass {

@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven") //getting data from different separate class
public void verify_login(String email, String pwd, String expresult)
{
	logger.info("******************TC003_loginDDT Starts***********************");
	
	try
	{
	Home hm = new Home(driver);
	hm.clickMyAccount();
	hm.clickLogin();
	
	LoginPage lp = new LoginPage(driver);
	lp.setEmailAddress(email);
	lp.setPassword(pwd);
	lp.clickLoginbtn();
	
	MyAccountPage acc= new MyAccountPage(driver);
	boolean verifyacc = acc.isMyAccountExists();
	
/* Login data is valid--login success--test passed--logout
 * 					-- login failed -- test failed
 * Login data is Invalid-- login failed -- test passed
 * 						-- login success--test failed-- logout
 */
	
	
	if(expresult.equalsIgnoreCase("Valid"))
	{
		if(verifyacc==true)
		{
			acc.clicklogout();          
			Assert.assertTrue(true);    //login success--test passed--logout
		}
		else
		{
			Assert.assertTrue(false);	//-- login failed -- test failed
		}
	}
	if(expresult.equalsIgnoreCase("InValid"))
		{
			if(verifyacc==true)
			{
				acc.clicklogout();
				Assert.assertTrue(false);	//-- login success--test failed-- logout
			}
			else
			{
				Assert.assertTrue(true);	//login failed -- test passed
			}
		}
	
}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("******************TC003_loginDDT Finished***********************");
	
}
	
}
