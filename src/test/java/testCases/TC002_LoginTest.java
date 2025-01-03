package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	@Test(groups={"sanity","Master"})
	
	public void verify_login() {
		logger.info("******* Starting TC002_LoginTest");
		try {
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		//lp.setPassword(p.getProperty("password"));//provide invalid passwoed from property file
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExist();
		Assert.assertEquals(targetPage, true,"Loginfailed");//this one correct or below one
		//Assert.assertTrue(targetPage);
		logger.info("******* Starting TC002_LoginTest");
		}catch(Exception e) {
			Assert.fail();
		}
		
	}
	

}
