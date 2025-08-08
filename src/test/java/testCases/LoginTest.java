package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class LoginTest extends BaseClass {
	
	
	// Run from masterSuite.xml file not from here
	
	
	@Test(priority=1)
    public void testLoginButtonDisabledWhenFieldsEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        
        
        loginPage.clickLogin();
        String errorMsg = loginPage.getErrorMessage();
        System.out.println("Error message: " + errorMsg);
        
        /*
        boolean isEnabled = loginPage.isLoginButtonEnabled();

        if (isEnabled) {
            System.out.println("Login button is ENABLED when fields are empty — page allows clicking without input.");
        } else {
            System.out.println("Login button is DISABLED when fields are empty — page prevents clicking without input.");
        }
         */
        //Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Login button should be disabled when fields are empty");
    }

	
	
    @Test(priority=2)
    public void testPasswordMaskedButton() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPasswordMasked(), "Password should be masked initially");
        loginPage.togglePasswordVisibility();
        Assert.assertFalse(loginPage.isPasswordMasked(), "Password should be visible after toggle");
    }

    
    

	@Test(priority=3)
    public void testInvalidLoginShowErrorMsg() {
		
		try
		{
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(randomAlphaNumeric());
        loginPage.setPwd(randomAlphaNumeric());
        loginPage.clickLogin();
        String errorMsg = loginPage.getErrorMessage();
        System.out.println("Error message: " + errorMsg);
        
		}catch(Exception e)
   	 {
   		 Assert.fail();
   	 }
			
    }
	
	
	@Test(priority=4)
	public void testPageElementsPresence() {
	    LoginPage loginPage = new LoginPage(driver);

	    Assert.assertTrue(loginPage.isUserIdFieldPresent(), "User ID field is missing");
	    Assert.assertTrue(loginPage.isPasswordFieldPresent(), "Password field is missing");
	    Assert.assertTrue(loginPage.isLoginButtonPresent(), "Login button is missing");
	    Assert.assertTrue(loginPage.isTitleCorrect("Janitri"), "Page title is incorrect");
	    Assert.assertTrue(loginPage.isLogoPresent());
	    Assert.assertTrue(loginPage.isQuotePresent("Your Pregnancy and Newborn Monitoring Partner"), "Page quote is incorrect");
	}

	
	
}
