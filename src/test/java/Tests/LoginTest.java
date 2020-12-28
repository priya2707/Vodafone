package Tests;

import java.io.IOException;

import org.testng.annotations.Test;
import org.openqa.selenium.By;

import Base.BaseClass;
import PageObjectRepository.loginHomePage;
import PageObjectRepository.loginPage;
import PageObjectRepository.mobilefunkHomepage;

public class LoginTest extends BaseClass{
	@Test
	public void LoginMitgültigenDaten() throws IOException
	{	
		loginPage lp= new loginPage(driver);
		lp.ClickMeinVodafonelogin();
		loginHomePage lh= new loginHomePage(driver);
		lh.BenutzerNameEingeben();
		lh.PasswortEingeben();
		
		driver.findElement(By.xpath("//div[@class='myvf-flyout']")).isDisplayed();
	}
	
}
