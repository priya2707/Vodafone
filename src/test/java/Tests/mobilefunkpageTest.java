package Tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Base.BaseClass;
import PageObjectRepository.deinWarenkorbPage;
import PageObjectRepository.mobilefunkBookingpage;
import PageObjectRepository.mobilefunkHomepage;
import junit.framework.Assert;


public class mobilefunkpageTest extends BaseClass{

	
	@Test
	public void BestellenVertragOhneSmartphone()
	{
	
		mobilefunkHomepage mh = new mobilefunkHomepage(driver);
		mh.ClickMobileFunk();
		BaseClass.implicitwait();		
	
		mh.ClickAkzeptierenundFortfahren();
		mh.ClickVertragOhneSmartphone();
		mobilefunkBookingpage mb = new mobilefunkBookingpage(driver);
		String s= mb.ActualNamederVertrag();
		mb.ClickInDenWarenkorb();
		
		BaseClass.implicitwait();
		deinWarenkorbPage warenkorb = new deinWarenkorbPage(driver);
		String p= warenkorb.ExpectedNamederVertrag();
		Assert.assertEquals(s, p);
	}
}
