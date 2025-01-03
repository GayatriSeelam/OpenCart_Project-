package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass {
	
	/*
	 Data is valid --login sucess-test pass-logout
	                  login fail-test fail
	 
	 Data is Invalid --login sucess-test fail-logout
	                    login failed-test pass-logout
	 
	 */
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven")//getting dataprovider differentclass
	public void verify_LoginDDT(String email,String pwd,String exp) {
		logger.info("******* Starting TC003_LoginDataDrivenTest");
		try {
       HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExist();
		/*
		 Data is valid --login sucess-test pass-logout
		                  login fail-test fail
		 
		 Data is Invalid --login sucess-test fail-logout
		                    login failed-test pass-logout
		 
		 */
		if(exp.equalsIgnoreCase("valid")) {
			if(targetPage==true) {
				macc.logoutElement();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
				
			}
			
		}
		if(exp.equalsIgnoreCase("invalid")) {
			if(targetPage==true) {
				macc.logoutElement();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e) {
			Assert.fail();
			
		
		logger.info("******* Finished TC003_LoginDataDrivenTest");
		
		}
		}
}
