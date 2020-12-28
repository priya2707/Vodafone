package PageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginHomePage {
	
private WebDriver driver;
	
	public loginHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
}
	
	
	@FindBy(xpath="//input[@name='username']")
	private WebElement BenutzerName;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement Passwort;
	
	@FindBy(xpath="//button[text()='Login']")
	private WebElement login;
	
	
	

	public void BenutzerNameEingeben()
	{
		BenutzerName.sendKeys("haisinnu@gmail.com");
	}
	
	public void PasswortEingeben()
	{
		BenutzerName.sendKeys("haisinnu@gmail.com");
		Passwort.sendKeys("sindhu123");
	}
	
	public void Clicklogin()
	{
		login.click();
	}
	

}
