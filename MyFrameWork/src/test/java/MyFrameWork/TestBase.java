package MyFrameWork;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestBase {

	public WebDriver driver;
	FileInputStream fis;
	public Properties prop;
	String directorypath;

	public WebDriver fnNavigatetoURL() throws InterruptedException {
		try {
			directorypath = System.getProperty("user.dir");
			fnReadPropertiesFile();
			System.out.println(directorypath);
			if (prop.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						directorypath + "\\src\\test\\resources\\chromedriver_win32 (3)\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (prop.getProperty("browser").equals("edge")) {
				System.setProperty("webdriver.edge.driver",
						directorypath + "\\src\\test\\resources\\edgedriver_win64\\msedgedriver.exe");
				driver = new EdgeDriver();
			}
			driver.get(prop.getProperty("webURL"));
			Thread.sleep(5000);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		return driver;
	}

	public void fnReadPropertiesFile() {
		try {

			String filepath = directorypath + "\\src\\main\\java\\data.properties";
			prop = new Properties();
			fis = new FileInputStream(filepath);
			prop.load(fis);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
