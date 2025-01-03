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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"sanity","regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws InterruptedException, IOException {
		//Loading properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();

//	        cap.setPlatform(Platform.WIN10); // cap.setPlatform(Platform.MAC);
//	        cap.setBrowserName("chrome"); // cap.setBrowserName("MicrosoftEdge");
	        //os
	        if(os.equalsIgnoreCase("windows")) {
	        	cap.setPlatform(Platform.WIN10);
	        	
	        	
	        }
	        else if (os.equalsIgnoreCase("mac")){
	        	cap.setPlatform(Platform.MAC);
	        }
	        else if (os.equalsIgnoreCase("linux")){
	        	cap.setPlatform(Platform.LINUX);
	        }
	        else {
	        	System.out.println("No atching os");
	        	return;
	        }
	        
	        //browser
	        switch(br.toLowerCase()) {
	        case "chrome":cap.setBrowserName("chrome");break;
	        case "firefox":cap.setBrowserName("firefox");break;
	        case "edge":cap.setBrowserName("MicrosoftEdge");break;
	        default:System.out.println("No matching browser");return;
	        }
	        
	        driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
	        
	        
	        //local
	        if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
	        	switch(br.toLowerCase()) {
//	    		case "chrome" : driver=new ChromeDriver();break;
//	    		case "edge":driver=new EdgeDriver();break;
//	    		case "firefox":driver=new FirefoxDriver();break;
//	    		default:System.out.println("Invalid browser");return;
	    		case "chrome":
	                WebDriverManager.chromedriver().setup();
	                driver = new ChromeDriver();
	                break;
	            case "edge":
	                WebDriverManager.edgedriver().setup();
	                driver = new EdgeDriver();
	                break;
	            case "firefox":
	                WebDriverManager.firefoxdriver().setup();
	                driver = new FirefoxDriver();
	                break;
	            default:
	                System.out.println("Invalid browser: " + br);
	                return;
	    		}
	        	
	        }
	        
		
		
		
		
		
		

//		switch(br.toLowerCase()) {
////		case "chrome" : driver=new ChromeDriver();break;
////		case "edge":driver=new EdgeDriver();break;
////		case "firefox":driver=new FirefoxDriver();break;
////		default:System.out.println("Invalid browser");return;
//		case "chrome":
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//            break;
//        case "edge":
//            WebDriverManager.edgedriver().setup();
//            driver = new EdgeDriver();
//            break;
//        case "firefox":
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//            break;
//        default:
//            System.out.println("Invalid browser: " + br);
//            return;
//		}
//		
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));//reading url from properties file
		//driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
	}
	@AfterClass(groups= {"sanity","regression","Master","datadriven"})
	public void tearDown() {
		driver.quit();
		
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(10);
		return generatedString;
	}
	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(10);
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return (generatedString+"@"+generatedNumber);
	}
	
	public String captureSreen(String tname) {
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		
		TakesScreenshot ts=(TakesScreenshot)driver;

        File sourcefile=ts.getScreenshotAs(OutputType.FILE);
        String targetfilepath=System.getProperty("user.dir")+"\\Screenshots\\"+tname+"_"+timestamp+".png";
         File targetFile = new File(targetfilepath);
         sourcefile.renameTo(targetFile);
         return targetfilepath;
         
	}

}
