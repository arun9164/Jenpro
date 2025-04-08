package arun.test;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import arun.components.Base;
import arun.components.Retry;

public class HashmapTest extends Base {

	SoftAssert softAssert = new SoftAssert();
	String expectedErrorMsg = "Incorrect email or password.11";

	@Test(dataProvider = "getData", retryAnalyzer=Retry.class)  

	void hashMapValidation(HashMap<String, String> input) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.id("userEmail")).sendKeys(input.get("email"));
		driver.findElement(By.id("userPassword")).sendKeys(input.get("pass"));
		driver.findElement(By.id("login")).click();
		  WebElement errorTx = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-error")));
//			Thread.sleep(2000);
		String EactualrrorMsg = errorTx.getText();
		System.out.println(EactualrrorMsg);
		Assert.assertEquals(EactualrrorMsg, expectedErrorMsg);
		softAssert.assertAll();

	}

	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "arunnatikar99@gmail.com");
		map.put("pass", "001122");
		

		return new Object[][] { { map }};
		
		/*HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "arunnatikar@gmail.com");
		map1.put("pass", "1234"); map1
		*/
	}

}