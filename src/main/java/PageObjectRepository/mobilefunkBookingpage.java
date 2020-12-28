package PageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class mobilefunkBookingpage {
	
	private WebDriver driver;
	
	
	public mobilefunkBookingpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@id='button-778648']/button/span")
	private WebElement InDenWarenkorb;
	
	@FindBy(xpath="//p[text()='Red L mit 40 GB & Social-Pass']")
	private WebElement NamederVertrag;
	
	public void ClickInDenWarenkorb()
	{
		InDenWarenkorb.click();
	}
	
	public String ActualNamederVertrag()
	{
		String VertragName= NamederVertrag.getText();
		return VertragName;
	}
}
