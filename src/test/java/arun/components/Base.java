package arun.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public WebDriver driver;

	public WebDriver intializeBrowser() throws IOException {
		if (driver == null) {
			Properties prop = new Properties();
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/arun/resources/config.properties");
			prop.load(file);
//		String browserName="chrome";
//			String browserName = prop.getProperty("browser");
			 String browserName = System.getProperty("browser")!= null? System.getProperty("browser"):prop.getProperty("browser");
			if (browserName == null) {
				throw new RuntimeException("Bowser name is null here");
			}
//			switch (browserName.toLowerCase()) {
//			case "chrome":
//				driver = new ChromeDriver();
//				break;
//			case "firefox":
//				driver = new FirefoxDriver();
//				break;
//			case "safari":
//				driver = new SafariDriver();
//			default:
//				throw new RuntimeException("Invalid BrowserName present in config file.");
//
//			}
			if(browserName.contains("chrome")) {
				ChromeOptions option = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				if(browserName.contains("headless")) {
					option.addArguments("--headless");
			        option.addArguments("--window-size=1440,900"); // ✅ this line is mandatory for headless
			        option.addArguments("--disable-gpu");
			        option.addArguments("--no-sandbox");
			        option.addArguments("--disable-dev-shm-usage");

				}
				driver = new ChromeDriver(option);
				driver.manage().window().setSize(new Dimension(1440,900));
			}
		
		}
		return driver;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
	}

	@BeforeMethod
	public void setUp() throws IOException {
		driver = intializeBrowser();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://rahulshettyacademy.com/client/");

	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
