package arun.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import arun.components.Base;
import arun.components.Retry;

public class ErrorValidationTest extends Base{

public ErrorValidationTest() {
	super();
}
	@Test(dataProvider = "getCredentials", retryAnalyzer=Retry.class)
	void erroValidate(String email, String pass) {
//		driver = new ChromeDriver();
//		driver.get("https://rahulshettyacademy.com/client/");
		System.out.println("+++");
		System.out.println(driver+" hello world");
		driver.get("https://rahulshettyacademy.com/client/");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(pass);
		driver.findElement(By.id("login")).click();
		WebElement errorTx = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));
		String errorMsg = errorTx.getText();
		System.out.println(errorMsg);

	}

	@DataProvider
	public Object[][] getCredentials() {
		return new Object[][] { { "arunnatikar9@gmail.com", "123456" }, { "arun@gmail.com", "1234" } };
	}

}
