package scenario_2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import utilities.BaseFunction;

public class AdminPage extends BaseFunction{
	
	private WebDriver driver;

	public AdminPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By menuOptions = By.xpath("//li[@class='oxd-main-menu-item-wrapper']");
	By admin = By.xpath("//li[1]//a[1]//span[1]");
	By userName = By.xpath("//label[text()='Username']/../following-sibling::div//input");
	By searchBtn = By.xpath("//button[@type='submit']");
	By result = By.cssSelector("div.orangehrm-horizontal-padding.orangehrm-vertical-padding span.oxd-text.oxd-text--span");
	By roleDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i");
	By roleOption = By.xpath("//div[@class=\"oxd-select-option\"]/span[text()='Admin']");
	By ststusDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[2]/i");
	By statusOption1 = By.xpath("//div[@class=\"oxd-select-option\"]/span[text()='Enabled']");
	By statusOption2 = By.xpath("//div[@class=\"oxd-select-option\"]/span[text()='Disabled']");
	
	public void getMenuOptions() {
		List<WebElement> options = driver.findElements(menuOptions);
		int count = options.size();
		for (WebElement el : options) {
            System.out.println(el.getText());
        }
		System.out.println("Total Menu Options: " + count);
		driver.findElement(admin).click();
	}
	
	public void searchByUserName(String username) {
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
		
		driver.findElement(userName).sendKeys(username);
		driver.findElement(searchBtn).click();
		sleep(2000);
		String resultCount = driver.findElement(result).getText();
		System.out.println(resultCount);
		driver.navigate().refresh();
	}
	
	public void searchByRole() {
		
		sleep(2000);
		driver.findElement(roleDropdown).click();
		driver.findElement(roleOption).click();
		driver.findElement(searchBtn).click();
		sleep(2000);
		String resultCount = driver.findElement(result).getText();
		System.out.println(resultCount);
		driver.navigate().refresh();
	}
	
	public void searchByStatus(String status) {
		sleep(2000);
		driver.findElement(ststusDropdown).click();
		if(status == "Enabled") {
			driver.findElement(statusOption1).click();
		}
		else if(status == "Disabled") {
			driver.findElement(statusOption2).click();
		} else {
			System.out.println("Invalid status");
			return;
		}
		driver.findElement(searchBtn).click();
		sleep(2000);
		String resultCount = driver.findElement(result).getText();
		System.out.println(resultCount);
	}
}
