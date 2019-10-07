import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class RPAChallenge {

	public static Object[][] excelArray;
	public static By lastName=By.xpath("//label[contains(text(),'Last Name')]//following::input[1]");
	public static By companyName=By.xpath("//label[contains(text(),'Company Name')]//following::input[1]");
	public static By firstName=By.xpath("//label[contains(text(),'First Name')]//following::input[1]");
	public static By phoneNumber=By.xpath("//label[contains(text(),'Phone Number')]//following::input[1]");
	public static By roleInCompany=By.xpath("//label[contains(text(),'Role in Company')]//following::input[1]");
	public static By address=By.xpath("//label[contains(text(),'Address')]//following::input[1]");
	public static By email=By.xpath("//label[contains(text(),'Email')]//following::input[1]");
	
	
	public static void main(String[] args) throws InterruptedException, IOException {

System.setProperty("webdriver.chrome.driver", "E:\\Users\\mohi123\\Downloads\\chromedriver.exe");
		
		String downloadFilepath = System.getProperty("user.dir");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new ChromeDriver(cap);

		
		driver.get("https://rpachallenge.com");
		driver.manage().window().maximize();
		
		//Code to enter data on Site
		driver.findElement(By.xpath("//a[contains(text(),'Download Excel')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		 
		Thread.sleep(3000);
		excelArray=RPAChallenge.ReadFromExcel(downloadFilepath+"\\challenge.xlsx");
		
		for(int count=1;count<11;count++)
		{
			driver.findElement(firstName).sendKeys(excelArray[count][0].toString());
			Thread.sleep(1000);
			driver.findElement(lastName).sendKeys(excelArray[count][1].toString());
			Thread.sleep(1000);
			driver.findElement(companyName).sendKeys(excelArray[count][2].toString());
			Thread.sleep(1000);
			driver.findElement(roleInCompany).sendKeys(excelArray[count][3].toString());
			Thread.sleep(1000);
			driver.findElement(address).sendKeys(excelArray[count][4].toString());
			Thread.sleep(1000);
			driver.findElement(email).sendKeys(excelArray[count][5].toString());
			Thread.sleep(1000);
			driver.findElement(phoneNumber).sendKeys(new BigDecimal(excelArray[count][6].toString()).toBigInteger().toString());
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@value='Submit']")).click();
		}
		Thread.sleep(6000);
		driver.quit();
		
		File file =new File(System.getProperty("user.dir")+"\\challenge.xlsx");
		if(file.delete())
			System.out.println("File deleted");
		else
			System.out.println("Cannot delete file");
	}

	public static Object[][] ReadFromExcel(String filePath) throws IOException
	{
		File file=new File(filePath.replace("//", "////"));
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		
		Sheet sheet=workbook.getSheetAt(0);
		
		int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum()+1;
		int colCount=sheet.getRow(0).getLastCellNum();
		Object[][] excelObj=new Object[rowCount+1][colCount];
		System.out.println(rowCount);
		System.out.println(colCount);
		
		for(int row=0;row<rowCount;row++)
		{
			Row rows=sheet.getRow(row);

			for(int col=0;col<colCount;col++)
			{
				excelObj[row][col]=rows.getCell(col).toString();
				System.out.println(excelObj[row][col]);
			}
		}
		
		return excelObj;
	}
}
