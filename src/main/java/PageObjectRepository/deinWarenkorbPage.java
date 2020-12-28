package PageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class deinWarenkorbPage {
	
	private WebDriver driver;
	
	public deinWarenkorbPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()=' Red L mit 40 GB ']")
	private WebElement NamederVertrag;
	
	public String ExpectedNamederVertrag()
	{
		String VertragName = NamederVertrag.getText();
		return VertragName;
	}
	
	
}
