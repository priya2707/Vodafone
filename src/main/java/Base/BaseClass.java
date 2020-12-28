package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.google.common.io.Files;

public class BaseClass implements ITestListener {

	public static WebDriver driver = null;

	
	/**
	 * 
	 * @param keyname
	 * @return
	 * @throws IOException
	 */
	public static String getConfigureData(String keyname) throws IOException {
		String value = "";
		File f = new File("./Data/config.properties");
		FileInputStream fis = new FileInputStream(f);
		Properties prop = new Properties();
		prop.load(fis);
		value = prop.getProperty(keyname);

		return value;
	}
	
	public static void implicitwait()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/*********************************************************************************************/
	
	/**
	 * 
	 * @throws IOException
	 */
	@BeforeMethod(alwaysRun=true)
	public static void LaunchApp() throws IOException {

		String browser = getConfigureData("browser");
		System.out.println(browser);
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./tools/chromedriver.exe");
			driver = new ChromeDriver();
		}

		if (browser.contentEquals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./tools/geckodriver.exe");
			driver = new FirefoxDriver();

		}

		// driver.findElement(By.xpath("//input[@id='username']")).sendKeys("admin");
		// driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys("manager");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(getConfigureData("url"));
		// driver.findElement(By.xpath("//input[@id='username']")).sendKeys("admin");
		// driver.findElement(By.xpath("//input[@id='username']")).clear();
	}

	/*************************************************************************************************/
	
	/**
	 * 
	 */
//	@AfterMethod(alwaysRun=true)
//	public static void Close() {
//		driver.close();
//	}
//	
	/*************************************************************************************************/
	
	/**
	 * Title This method is to write results to a file
	 * @author sindhu
	 * @param testcasename
	 * @param status
	 * @return none
	 * @throws IOException
	 */


	public static void writeResultToFile(String testcasename, String status) throws IOException {
		File f = new File("./src/test/results/results.txt");
		FileWriter fw = new FileWriter(f, true);
		fw.write("\n" + testcasename + "----" + status);
		fw.flush();
		fw.close();
	}

	/************************************************************************************************/
	
	/**
	 * 
	 * @param FileName
	 * @throws IOException
	 */
	public static void CaptureScreenshot(String FileName) throws IOException {

		// LaunchApp("https://demo.actitime.com/login.do");
		TakesScreenshot ts = (TakesScreenshot) driver;
		// ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File src = ts.getScreenshotAs(OutputType.FILE);
		// File dest = new File("C:\\Desktop\\" + FileName + ".png");
		File dest = new File("./src/test/results/screenshots" + FileName + ".png");
		Files.copy(src, dest);
		driver.close();
	}

	/**********************************************************************************************/
	
	/**
	 * 
	 * @param FileName
	 * @param PageName
	 * @param LocatorName
	 * @return
	 * @throws IOException
	 */
	public static String getDataFromExcelFile(String FileName, String PageName, String LocatorName) throws IOException {
		String locator = null;
		File file = null;
		if (FileName.contains("locator")) {
			file = new File("./Data/locator.xlsx");
		} else if (FileName.contains("test")) {
			file = new File("./Data/testdata.xlsx");
		}

		FileInputStream str = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(str);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int rows = sheet.getLastRowNum();
		System.out.println("number of rows is " + rows);

		for (int x = 1; x <= rows; x++) {
			String page = sheet.getRow(x).getCell(0).getStringCellValue();
			String loc = sheet.getRow(x).getCell(1).getStringCellValue();
			if ((PageName.equals(page)) && (LocatorName.equals(loc))) {
				locator = sheet.getRow(x).getCell(2).getStringCellValue();
				System.out.println(locator);
			}
		}

		workbook.close();
		return locator;

	}
	/*************************************************************************************************/

	public void onTestStart(ITestResult result) {
		
		System.out.println("the test case " +result.getName()+ "is running");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("test case "+result.getName()+"is passed");
		try {
			writeResultToFile(result.getTestName(), "pass");
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("the test case"+result.getName()+"is failed");
		try {
			writeResultToFile(result.getName(), "Fail");
			CaptureScreenshot(result.getName());
			System.out.println("screenshot");
			Throwable t = result.getThrowable();
			String s =Arrays.toString(t.getStackTrace());
			s= s.replaceAll(",", "\n");
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	public void onTestSkipped(ITestResult result) {
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		
	}

	public void onStart(ITestContext context) {
		System.out.println("this will bne executed at the start of the test");
	}

	public void onFinish(ITestContext context) {
		System.out.println("this will be executed at th end");
		
	}
	
}
