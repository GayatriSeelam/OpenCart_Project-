package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") 
	WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']") 
	WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") 
	WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") 
	WebElement txtpassword;
	@FindBy(xpath="//input[@id='input-confirm']") 
	WebElement txtConfirmpassword;
	@FindBy(xpath="//input[@name='agree']") 
	WebElement chkpolicy;
	@FindBy(xpath="//input[@value='Continue']") 
	WebElement btncontinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")//am testing for failure xml am given here accoun run grou.xml
	WebElement msgConfirmation;
	
	// Methods for interacting with WebElements

	public void setFirstName(String firstName) {
	    txtFirstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
	    txtLastName.sendKeys(lastName);
	}

	public void setEmail(String email) {
	    txtEmail.sendKeys(email);
	}

	public void setTelephone(String telephone) {
	    txtTelephone.sendKeys(telephone);
	}

	public void setPassword(String password) {
	    txtpassword.sendKeys(password);
	}

	public void setConfirmPassword(String confirmPassword) {
	    txtConfirmpassword.sendKeys(confirmPassword);
	}

	public void agreeToPolicy() {
	    
	       chkpolicy.click();
	    }
	

	public void clickContinue() {
	    btncontinue.click();
	}
	
	public String getConfirmationMessage() {
		try {
			 return msgConfirmation.getText();
			
		}
		catch(Exception e) {
			return(e.getMessage());
		}
	    
	}

}
