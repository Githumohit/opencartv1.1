package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {
	public static WebDriver driver;   // make static due to screenshot method in extentreport manager class
	public Logger logger; //Log4j2
	public Properties p ;
	
	@BeforeTest(groups= {"Master","Regression","Sanity"}) // can also add "Datadriven"
	@Parameters({"os","browser"})   // for cross browser testing or parallel testing through xml
	public void setup(String os , String br) throws IOException 
	{	
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
	
	//execution_env=remote
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))  // get env from property file
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os fetching from xml
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("No OS is matching..");
			return;
			}
			
			//browser fetching from xml
			switch(br.toLowerCase())
			{
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : capabilities.setBrowserName("firefox"); break;
			default : System.out.println("No browser found");
			return;
			}
			
			driver=new RemoteWebDriver(new URL("http://192.168.1.48:4444/"),capabilities);  // launch the remote web browser
		}
		
		
	//execution_env=local	
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch(br.toLowerCase())
			{                          // for cross browser testing or parallel testing through xml
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver();break;
			default : System.out.println(" browser not fount"); return;		
			}
		}
		
		
		driver.manage().deleteAllCookies();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 driver.get(p.getProperty("appurl")); // get url form properties file
		 driver.manage().window().maximize();
		
	}
	
	@AfterTest(groups= {"Master","Regression","Sanity"}) // can also add "Datadriven"
	public void teardown() 
	{
		driver.quit();		
	}
	
	// random data generate functions
	public String randomeString() {
		String generatedname=RandomStringUtils.randomAlphabetic(5);
		return generatedname;		
	}
	
	public String randomeNumber() {
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;		
	}
	public String randomePassword() {
		//String generatedPassword=RandomStringUtils.randomAlphanumeric(6);
		//return generatedPassword;	
		
		 String generatedname=RandomStringUtils.randomAlphabetic(5);
		  String generatednumber=RandomStringUtils.randomNumeric(10);
		 return (generatedname+generatednumber);		 		
	}
	
// take screenshot and save
	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname+ "_"+timeStamp+ ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
		
	}
}
