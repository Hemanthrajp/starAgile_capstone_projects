package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

public class BaseFunction {
	
	public static WebDriver driver = null;
	
	public static void launchBrowser(String browserName) {
		if(browserName.equals("Chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equals("Firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("Safari")) {
			driver = new SafariDriver();
		}
		
		driver.manage().window().maximize();
	}
	
	public static void launchURL(String url) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get(url);
	}
	
	public static void sleep(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public static void takeScreenshot(String fileName) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		fileName = fileName + timestamp;
		
		try {
			String destination = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + fileName + ".png"; 	
			File sourceFileLocation = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
			
			// save screenshot
			FileHandler.copy(sourceFileLocation, new File(destination));
			System.out.println("Screenshot captured");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
