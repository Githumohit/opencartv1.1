package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporters;   // generate UI in the report
	public ExtentReports extent;     // Generate common/general info in the test
	public ExtentTest test;         // creating test case entries in the report and add/update the status of test module.
	String repName;

    public void onStart(ITestContext testContext ) {
    /*	SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    	Date dt = new Date();
    	String currentDateTimeStamp=df.format(dt);
    */	
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	
    	repName = "Test Report-" +timeStamp + ".html";
    	
    	// specify the location of the report by after creating a report folder in the project.
    	sparkReporters = new ExtentSparkReporter(System.getProperty("user.dir") +"//reports//"+ repName );
    	
    	sparkReporters.config().setDocumentTitle("OpenCart Automation Reports");  //Title of the report
    	sparkReporters.config().setReportName("Opencart functional Testing");    // name of the report
    	sparkReporters.config().setTheme(Theme.DARK);
    	
    	extent = new ExtentReports();
    	extent.attachReporter(sparkReporters);
    	
    	extent.setSystemInfo("Project name", "Opencart");
    	extent.setSystemInfo("comp name", "local host");
    	extent.setSystemInfo("Enviornment", "alpha QA");
    	extent.setSystemInfo("Module", "Admin");
    	extent.setSystemInfo("SubModule", "Customers");
    	extent.setSystemInfo("User name", System.getProperty("user.name"));
    
    // get data from xml
    	String os=testContext.getCurrentXmlTest().getParameter("os");
    	extent.setSystemInfo("operating system", os);
    	
    	String br= testContext.getCurrentXmlTest().getParameter("browser");
    	extent.setSystemInfo("Browser name", br);
    
    // fetch group name from xml	
    	List<String> includedGroups= testContext.getCurrentXmlTest().getIncludedGroups();
    	if(!includedGroups.isEmpty())
    	{
    		extent.setSystemInfo("Groups", includedGroups.toString()); 
    	}   	
    }
 
		 public void onTestSuccess(ITestResult result ) {
		 	
			 test =extent.createTest(result.getTestClass().getName());    // create new entry in the report
		 	test.assignCategory(result.getMethod().getGroups());          // to display groups in report
			 test.log(Status.PASS, "This is passed test.." + result.getName() );  // update the status in report..Pass
		 	
		 }
		    
		 public void onTestFailure(ITestResult result ) {
		 	test = extent.createTest(result.getTestClass().getName());
		 	test.assignCategory(result.getMethod().getGroups()); 
		 	
		 	test.log(Status.FAIL, "This is failed test.." + result.getName());
		 	test.log(Status.FAIL, result.getThrowable().getMessage());	 //throw the defined error message	and write in the report
		
		//call baseclass for take and store the screenshot 	
		 	try {
		 		String imgPath = new BaseClass().captureScreen(result.getName()); // make static webdriver in baseclass for this new baseclass object
		 		test.addScreenCaptureFromPath(imgPath);		 		
		 	}
		 	catch (IOException e) {
		 		e.printStackTrace();
		 	}
		 }
		 
		 
		 public void onTestSkipped(ITestResult result ) {
		 	
			 test = extent.createTest(result.getTestClass().getName());  //creating file
			 test.assignCategory(result.getMethod().getGroups());        // getting group name
			 
		 	test.log(Status.SKIP, "This is Skipped test.." + result.getName());   //adding log with function name
		 	test.log(Status.SKIP, result.getThrowable().getMessage());				// add throw error message
		 	
		 }
		 
		 public void onFinish(ITestContext context ) {    // mandatory
		 	
		 	extent.flush();
//--optional	 	
	 //To open report after test execution
		 	String pathOfExtentReport = System.getProperty("user.dir") +"//reports//"+ repName ;
		 	File extentReport = new File(pathOfExtentReport);
		
	// if report not available that time then throw exception
		 	try {
		 		Desktop.getDesktop().browse(extentReport.toURI());
		 	}
		 	catch (IOException e) {
		 		e.printStackTrace();
		 	}
		/* 	
		 	try { URL url = new URL("file:///"+System.getProperty("user.dir") +"//reports//"+ repName);
		 // creating email message
		 // add dependencies for email
		 	ImageHtmlEmail email= new ImageHtmlEmail();
		 	email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 	email.setHostName("smtp.googlemail.com");
		 	email.setSmtpPort(465);   // for gmail only
		 	email.setAuthenticator(new DefaultAuthenticator("Sender@gmail.com", "password"));
		 	email.setSSLOnConnect(true);
		 	email.setFrom("Sender@gmail.com");
		 	email.setSubject("Test Result Report");
		 	email.setMsg("Please find the test report below-");
		 	email.addTo("reciever@gmail.com");
		 	email.attach(url,"extent report", "Please check the Test reult report");
		 	email.send(); 		//send email	
		 		
		 	}
		 	catch(Exception e) {
		 		e.getStackTrace();
		 	}
		 	*/
		 	
		 	
		 }
		 
	}
