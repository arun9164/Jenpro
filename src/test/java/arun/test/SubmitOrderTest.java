package arun.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SubmitOrderTest {
	WebDriver driver;
	SoftAssert softAssert = new SoftAssert();
	String productName ="ZARA COAT 3";
	@Test
	void order() {
		driver  =new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.id("userEmail")).sendKeys("arunnatikar99@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahul@1234");
		driver.findElement(By.id("login")).click();
		// Home page should display
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		WebElement  home = driver.findElement(By.cssSelector(".mb-3"));
		
		Assert.assertTrue(home.isDisplayed(),"Cards are not displayed");
		
		//fetch all products present in page
		 List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		 
		 for(WebElement product:products) {
			 System.out.println("---");
			  String po = product.findElement(By.cssSelector(".card-body b")).getText();
//			  String po = product.findElement(By.xpath("//div[@class='card-body']//b")).getText();
			  System.out.println(po);
			  System.out.println("++");
		 }
		 
//		
//			
//		}
//		
//		driver.quit();
		}
}
