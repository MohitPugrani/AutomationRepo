import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CurrentDirectory {

	public static void main(String[] args) {

		System.out.println(System.getProperty("user.dir").replace("\\", "\\\\"));
	}

}

/*
 
 System.setProperty("webdriver.chrome.driver", "E:\\Users\\mohi123\\Downloads\\chromedriver.exe");
		
		String downloadFilepath = System.getProperty("user.dir").replace("\\", "\\\\");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new ChromeDriver(cap);
		
		driver.navigate().to("https://rpachallenge.com");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.quit();
 
 */