package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class Tc001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"regression","Master"})
	public void verify_account_registration() {
		logger.info("******* Starting Tc001_AccountRegistrationTest");
		try {
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("******* Clicked on My Acount Link");
			hp.clickRegister();
			logger.info("******* Clicked on Register Link");
			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			logger.info("******* providing customer details");
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString()+"@gmail.com");//randomly generated the email
			regpage.setTelephone(randomNumber());
			
			
			String password = randomAlphaNumeric();
			
			
			
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			regpage.agreeToPolicy();
			regpage.clickContinue();
			logger.info("******* validating expected message");
			String confg = regpage.getConfirmationMessage();
			if(confg.equals("Your Account Has Been Created!")) {
				
			
			Assert.assertTrue(true);
			}
			else {
				logger.error("Test failed");
				logger.debug("debug logs");
				Assert.assertTrue(false);
				
			}
		
		
		} 
		catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("******* Finished Tc001_AccountRegistrationTest");
		}
}
		
		
		
	
	


