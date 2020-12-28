package PageObjectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class mobilefunkHomepage {

	private WebDriver driver;
	

	

	public mobilefunkHomepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@class='captcha']")
	private WebElement captcha;
	//@FindBy(xpath="//div[@id='dip-consent-summary']/div[3]/div/span[text()='Akzeptieren und Fortfahren'] ")
	
	@FindBy(xpath="//div[@id='dip-consent-summary']/div[3]/div[1]")
	private WebElement AkzeptierenundFortfahren;
	
	@FindBy(xpath="//span[text()='Mobilfunk']")
	private WebElement MobileFunk;

	@FindBy(xpath="//span[text()='Vertrag ohne Smartphone']")
	private WebElement VertragOhneSmartphone;
	
	@FindBy(xpath="//span[text()='Vertrag mit Smartphone']")
	private WebElement VertragMitSmartphone;
	
	//a[@id='brix-button-tiles-item-767567-768125']
	@FindBy(xpath="//span[text()='Angebote für alle unter 28']") 
	private WebElement AngeboteFürAlleUnter28;
	
	@FindBy(xpath="//span[text()='Angebote für Selbständige']") 
	private WebElement AngeboteFürSelbstStändige;
	
	@FindBy(xpath= "//span[text()='Prepaid-Tarif ohne Vertrag']")
	private WebElement PrepaidTariffOhneVertrag;
	
	
	public String Captcha()
	{
		String captchaText = captcha.getText();
		return captchaText;
	}

	public void ClickAkzeptierenundFortfahren()
	{
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", AkzeptierenundFortfahren);
		//AkzeptierenundFortfahren.click();
	}
	public void ClickMobileFunk()
	{
		MobileFunk.click();
	}
	public void ClickVertragOhneSmartphone()
	{
		VertragOhneSmartphone.click();
	}
	
	
}
