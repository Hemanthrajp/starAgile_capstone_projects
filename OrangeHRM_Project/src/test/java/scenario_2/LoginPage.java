package scenario_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By login_userName = By.name("username"); 
	By login_password = By.name("password");
	By login_button = By.xpath("//button[@type='submit']");
	
	public void login(String userName, String password) {
		driver.findElement(login_userName).sendKeys(userName);
		driver.findElement(login_password).sendKeys(password);
		driver.findElement(login_button).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
