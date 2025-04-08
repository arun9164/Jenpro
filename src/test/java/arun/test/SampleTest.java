package arun.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import arun.components.Base;
import arun.components.Retry;

public class SampleTest extends Base{
	SoftAssert softAssert = new SoftAssert();
	String expectedErrorMsg = "Incorrect email or password.";

	@Test(retryAnalyzer=Retry.class)
	void SampleErrorValidation() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("Arun@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("natika");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		 WebElement errorTx = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));
//			Thread.sleep(2000);
		String EactualrrorMsg = errorTx.getText();
		System.out.println(EactualrrorMsg);
		AssertJUnit.assertEquals(EactualrrorMsg, expectedErrorMsg);
		softAssert.assertAll();

	}
	
	@Test
	void faceBook() {
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("Arun@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("natika");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		System.out.println("Hello FaceBook");
	}

}
