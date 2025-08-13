package scenario_1;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utilities.BaseFunction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class OrangeHRM extends BaseFunction{
	
	String url = "https://opensource-demo.orangehrmlive.com/";
	String fpath = System.getProperty("user.dir") + File.separator + "ExcelFiles" + File.separator + "LoginData.xlsx";
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	
	ExtentSparkReporter htmlreport;
	ExtentReports report;
	ExtentTest test;
	String extentReportPath = System.getProperty("user.dir") + File.separator + "report" + File.separator + "OHRM_LoginReport.html";
	
	@BeforeTest
	public void beforeTest() throws IOException {
		File file = new File(fpath);
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);
		
		launchBrowser("Chrome");
		sleep(4000);
		launchURL(url);
		
		// extentReport setup
		htmlreport = new ExtentSparkReporter(extentReportPath);
		report =  new ExtentReports();
		report.attachReporter(htmlreport);
		
		report.setSystemInfo("Project Name", "OrangeHRM_LoginPage");
		report.setSystemInfo("Machine", "HP Pavilion");
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("User", "Hemanth");
		report.setSystemInfo("Browser", "Google Chrome");
		
		htmlreport.config().setDocumentTitle("OHRM_LoginReport");
		htmlreport.config().setReportName("OrangeHRM_LoginPage");
		htmlreport.config().setTheme(Theme.STANDARD);
		htmlreport.config().setTimeStampFormat("DD-MM-YYY");
	}
	
	@DataProvider
	public Object[][] loginData() {
		int totalrows = sheet.getPhysicalNumberOfRows();
		String[][] logindata = new String[totalrows - 1][2];
		
		for(int i = 0; i < totalrows - 1; i++) {
			row = sheet.getRow(i + 1);
			for(int j = 0; j < 2; j++) {
				cell = row.getCell(j);
				logindata[i][j] = (cell != null) ? cell.getStringCellValue() : "";
			}
		}
		
		return logindata;
	}
	
	@Test(dataProvider = "loginData")
	public void OrangeHRM_Login(String userName, String password) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		
		driver.findElement(By.name("username") ).clear();
		driver.findElement(By.name("username") ).sendKeys(userName);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		if (userName.equals("Admin") && password.equals("admin123")) {
			takeScreenshot("validLogin");
	        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
	        
	        test = report.createTest("Valid Login - " + userName + " and " + password);
            test.log(Status.PASS, MarkupHelper.createLabel("Login Passed", ExtentColor.GREEN));
	        
	        driver.findElement(By.cssSelector(".oxd-userdropdown-name")).click();
	        driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
	    } else {
	    	
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Invalid')]")));
	    	sleep(4000);
	    	
	    	takeScreenshot("invalidLogin");
	        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"));
	        
	        test = report.createTest("Invalid Login - " + userName + " and " + password);
            test.log(Status.FAIL, MarkupHelper.createLabel("Invalid Login Blocked", ExtentColor.RED));
            launchURL(url);
	    }
	}
	
	@AfterTest
	public void afterTest() throws IOException {
		wb.close();
		fis.close();
		report.flush();
		driver.quit();
	}
}