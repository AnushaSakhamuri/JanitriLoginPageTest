package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseTest {
	
WebDriver driver; // make it class/ global level variablr easy to acess
	
	public  BaseTest(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}



}
