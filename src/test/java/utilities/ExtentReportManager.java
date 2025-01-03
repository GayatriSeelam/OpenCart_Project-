package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
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
    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test ;
    private String repName;

    public void onStart(ITestContext testContext) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timestamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        sparkReporter.config().setDocumentTitle("open cart Automation Report");
        sparkReporter.config().setReportName("open cart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application Name", "OpenCart");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("SubModule", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));

        String os = testContext.getCurrentXmlTest().getParameter("os");
        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Operating System", os);
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
		 test=extent.createTest(result.getTestClass().getName());//create a new entry in the report
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.PASS,result.getName()+"got sucessfully execute");//update status p/f/s
		    
		  }
	 public void onTestFailure(ITestResult result)  {
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());//create a new entry in the report
		 test.log(Status.FAIL,result.getName()+"got failed");//update status p/f/s
		 
		 test.log(Status.INFO,result.getThrowable().getMessage());
		 try {
		 String imgPath=new BaseClass().captureSreen(result.getName());
		 test.addScreenCaptureFromPath(imgPath);
		 }
		 catch(Exception e1) {
			 e1.printStackTrace();
			 
		 }
		    
	  }
	
	  public void onTestSkipped(ITestResult result) {
		  test=extent.createTest(result.getTestClass().getName());//create a new entry in the report
		  test.assignCategory(result.getMethod().getGroups());//create a new entry in the report
			 test.log(Status.SKIP,result.getName()+"got skipped");//update status p/f/s
			 
			 test.log(Status.INFO,result.getThrowable().getMessage());
		  }
	  public void onFinish(ITestContext context) {
		  extent.flush();
		  String pathOfExtentReports=System.getProperty("user.dir")+"\\reports\\"+repName;
		  File extentReport=new File(pathOfExtentReports);
		  try {
			  Desktop.getDesktop().browse(extentReport.toURI());
		  }
		  catch(Exception e) {
				 e.printStackTrace();
				 
			 }
		 

        // Email the report
        //sendEmailReport(extentReportFile);
    }

//    private void sendEmailReport(File reportFile) {
//        try {
//            HtmlEmail email = new HtmlEmail();
//            email.setHostName("smtp.googlemail.com");
//            email.setSmtpPort(465);
//            email.setAuthenticator(new DefaultAuthenticator("your_email@gmail.com", "your_password"));
//            email.setSSLOnConnect(true);
//            email.setFrom("your_email@gmail.com");
//            email.setSubject("Test Automation Report");
//            email.setMsg("Please find the attached test report.");
//            email.addTo("recipient_email@gmail.com");
//            email.attach(reportFile, "Automation Test Report", "Generated Test Report");
//
//            email.send();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

