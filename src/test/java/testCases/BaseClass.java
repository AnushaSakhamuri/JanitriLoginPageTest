package testCases;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	
public Properties p;
	
public WebDriver driver;
	
	
	// Base class is for the common methods which will be used by many test cases @ test methods
	
	 @BeforeClass()
	@Parameters({"browser"})// we pass this from mastersuite.xml
	public void setUp(String br) throws IOException
	{
		 
		 WebDriverManager.chromedriver().setup();

		    // Set Chrome options
		    ChromeOptions options = new ChromeOptions();
		    
		 // Create a fresh temporary Chrome profile every run
	        String tempProfile = System.getProperty("java.io.tmpdir") + "chrome-profile-" + System.currentTimeMillis();
	        options.addArguments("--user-data-dir=" + tempProfile);
	        
	        

		    // 1 = allow, 2 = block
		    Map<String, Object> prefs = new HashMap<>();
		    prefs.put("profile.default_content_setting_values.notifications", 2); // 1 = allow
		    options.setExperimentalOption("prefs", prefs);

		    //driver = new ChromeDriver(options);
		
		// loading congig.properties file
		
		
				// to read the file
				FileReader file= new FileReader("./src//test//resources//config.properties");
				
				p= new Properties();
				
				p.load(file);
			
		
	    driver= new ChromeDriver(options); 
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.get(p.getProperty("appUrl"));// from config.properties
		
		driver.manage().window().maximize();
		
		// Wait up to 5s for overlay, then remove it if the overlay notification pop up present
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
		    WebElement overlay = wait.until(ExpectedConditions.presenceOfElementLocated(
		        By.cssSelector("div.MuiDialog-root.dialog-login")
		    ));

		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].remove();", overlay);

		} catch (TimeoutException e) {
		    // Overlay never appeared — nothing to remove
		}
		
	}
	
	@AfterClass()
	public void tearDown()
	{
		driver.quit();
	}
	
	
	 // to create random strings
    
	   public String randomAlphaNumeric()
	   {
		   RandomStringGenerator alphano_generator = new RandomStringGenerator.Builder()
			       .withinRange('0', 'z')
			       
			     //If you want only letters and digits, and want to remove the symbols like [ / , etc  use:
			       
			       .filteredBy(cp -> Character.isLetterOrDigit(cp))// cp means code point (a-z as in numbers 97 to122))
	                // above one is lambda expression
			       
			       .usingRandom(ThreadLocalRandom.current()::nextInt)
			       .build(); // still needed, but `usingRandom` is the key addition

			   String alphano_random =alphano_generator.generate(10);
			   
		    	
				return alphano_random;
	   }
	   

}
