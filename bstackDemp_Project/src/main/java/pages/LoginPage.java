package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import baseUtils.BaseFunction;
import utils.ConfigReader;
import utils.WaitUtils;

public class LoginPage {	
	
	String validationMsg = "Invalid Username";
	@FindBy(id = "signin")
	private WebElement singIn;
	@FindBy(id = "react-select-2-input")
	private WebElement userName;
	@FindBy(id = "react-select-3-input")
	private WebElement password;
	@FindBy(id = "login-btn")
	private WebElement loginButton;
	@FindBy(xpath = "//h3[@class='api-error']")
	private WebElement errorMsg;
	@FindBy(id = "signin")
	private WebElement logout;
	
	public void signIn() {
		WaitUtils.VisibilityofElement(singIn);
		singIn.click();
	}
	
	public void loginWithEmptyFields() {
		WaitUtils.VisibilityofElement(userName);
		loginButton.click();
		WaitUtils.VisibilityofElement(errorMsg);
		String errMsg = errorMsg.getText();
		Assert.assertEquals(errMsg.trim(), validationMsg, "Validation message mismatch!");
	}
	
	public void loginWithInvalidData() {
		WaitUtils.VisibilityofElement(userName);
		userName.clear();
		userName.sendKeys(ConfigReader.get("Invalid_Username"));
		userName.sendKeys(Keys.ENTER);
		password.clear();
		password.sendKeys(ConfigReader.get("Invalid_Password"));
		password.sendKeys(Keys.ENTER);
		loginButton.click();
		WaitUtils.VisibilityofElement(errorMsg);
		String errMsg = errorMsg.getText();
		Assert.assertEquals(errMsg.trim()	, validationMsg, "Validation message mismatch!");
	}
	
	public void loginWithValidData() {
		WaitUtils.VisibilityofElement(userName);
		userName.clear();
		userName.sendKeys(ConfigReader.get("Username"));
		userName.sendKeys(Keys.ENTER);
		password.clear();
		password.sendKeys(ConfigReader.get("Password"));
		password.sendKeys(Keys.ENTER);
		loginButton.click();
	}
	
	public void logout() {
		WaitUtils.VisibilityofElement(logout);
		logout.click();
	}

	public void refreshPage() {
		BaseFunction.driver.navigate().refresh();
	}
}
