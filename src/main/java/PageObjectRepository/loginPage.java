package PageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

	private WebDriver driver;
	
	public loginPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='icon icon-myvf']")
	private WebElement MeinVodafonelogin;
	
	public void ClickMeinVodafonelogin()
	{
		MeinVodafonelogin.click();	
	}
	
	
}
