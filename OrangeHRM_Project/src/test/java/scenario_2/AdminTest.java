package scenario_2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.BaseFunction;

public class AdminTest extends BaseFunction{
	
	public String url = "https://opensource-demo.orangehrmlive.com/";
	
	@BeforeTest
	public void beforeTest() {
		launchBrowser("Chrome");
		sleep(4000);
		launchURL(url);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		
		LoginPage login = new LoginPage(driver);
		login.login("Admin", "admin123");
	}
	
	@Test
	public void searchEmployee() {
		AdminPage search = new AdminPage(driver);
		search.getMenuOptions();
		search.searchByUserName("Admin");
		search.searchByRole();
		search.searchByStatus("Enabled");
		//search.searchByStatus("Disabled");
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
