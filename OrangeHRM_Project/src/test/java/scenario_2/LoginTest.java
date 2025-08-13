package scenario_2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.BaseFunction;

public class LoginTest extends BaseFunction{
	
	public String url = "https://opensource-demo.orangehrmlive.com/";
	
	@BeforeTest
	public void beforeTest() {
		launchBrowser("Chrome");
		sleep(4000);
		launchURL(url);
	}
	
	@Test
	public void testLogin() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		
		LoginPage login = new LoginPage(driver);
		login.login("Admin", "admin123");
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
