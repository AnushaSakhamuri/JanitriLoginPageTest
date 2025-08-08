package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BaseTest{
	
	// 1.constructor
	
		public LoginPage(WebDriver driver)
		{
			super(driver);
		}
		
		//2. locators
		
		
		@FindBy(xpath="//input[@id='formEmail']") 
		WebElement username;
		
		@FindBy(xpath="//input[@id='formPassword']")
		WebElement password;
		
		
		@FindBy(xpath="//button[@type='submit']")
		WebElement bt_login;
		
		@FindBy(xpath="//img[@alt=\"Password Not Visible\"]")
		WebElement eye_icon;
		
		@FindBy(xpath="//p[text()=\"Invalid Credentials\"]")
		WebElement error_msg;
		
		@FindBy(xpath="//img[@class=\"login-janitri-logo\"]")
		WebElement page_logo;
		
		@FindBy(xpath="//p[text()=\"Your Pregnancy and Newborn Monitoring Partner\"]")
		WebElement page_quote;
		
		
		
		
		// 3.action methods
		
		public void setEmail(String email)
		{
			username.sendKeys(email);
		}
		
		
		public void setPwd(String pwd)
		{
			password.sendKeys(pwd);
		}
		
		public void clickLogin()
		{
			bt_login.click();
		}
		
		public boolean isPasswordMasked() {
			String type=password.getAttribute("type");
	        return type.equals("password");
	    }

	    public void togglePasswordVisibility() {
	    	
	    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	    wait.until(ExpectedConditions.elementToBeClickable(eye_icon));
	    	    eye_icon.click();
	    	}

	    	
	    

	    public String getErrorMessage() {
	        try {
	            return error_msg.getText();
	        } catch (Exception e) {
	            return "No error message found";
	        }
	    }
	    
	    public boolean isLoginButtonEnabled() {
	        return bt_login.isEnabled();
	    }
	    
	    public boolean isUserIdFieldPresent() {
	        return username.isDisplayed();
	    }

	    public boolean isPasswordFieldPresent() {
	        return password.isDisplayed();
	    }

	    public boolean isLoginButtonPresent() {
	        return bt_login.isDisplayed();
	    }

	    public boolean isEyeIconPresent() {
	        return eye_icon.isDisplayed();
	    }

	    public boolean isTitleCorrect(String expectedTitle) {
	        return driver.getTitle().equals(expectedTitle);
	        
	    }
	    
	    public boolean isLogoPresent() {
	        return page_logo.isDisplayed();
	    }

	    
	    public boolean isQuotePresent(String expectedQuote) {
	        return page_quote.getText().trim().equalsIgnoreCase(expectedQuote);
	    }

	
		
	    


}
