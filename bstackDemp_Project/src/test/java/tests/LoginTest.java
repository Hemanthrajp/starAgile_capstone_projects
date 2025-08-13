package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baseUtils.BaseFunction;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseFunction{
	
	static LoginPage login;
	
	@Test(priority = 1)
	public void loginWithNoCredentials() {
		test = Report.createTest("Logging without credential");
		
		login.signIn();
		login.loginWithEmptyFields();
		test.pass("Login Fails");
		login.refreshPage();
	}
	
	@Test(priority = 2)
	public void loginWithInvalidCredentials() {
		test = Report.createTest("Logging with invalid credential");
		
		login.loginWithInvalidData();
		test.pass("Login Fails");
		login.refreshPage();
	}
	
	@Test(priority = 3)
	public void loginWithValidCredentials() {
		test = Report.createTest("Logging with valid credential");
		
		login.loginWithValidData();
		test.pass("Login successfull");
	}
	
	@BeforeTest
	public void beforeTest() {
		String url = ConfigReader.get("AppURL");
		BaseFunction.launchUrl(url);
		login = PageFactory.initElements(BaseFunction.driver, LoginPage.class);
	}
}
