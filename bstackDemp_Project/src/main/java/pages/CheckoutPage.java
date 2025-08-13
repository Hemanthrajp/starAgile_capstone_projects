package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utils.ConfigReader;
import utils.WaitUtils;

public class CheckoutPage {
	
	private By FirstName = By.id("firstNameInput");
	private By LastName = By.id("lastNameInput");
	private By Address = By.id("addressLine1Input");
	private By State = By.id("provinceInput");
	private By pinCode = By.id("postCodeInput");
	private By submit = By.id("checkout-shipping-continue");
	private By successmessage = By.id("confirmation-message");
	private By orderNumber = By.xpath("//strong[normalize-space(text())]");	
	
	public void checkoutForm() {
		WebElement firstName = WaitUtils.VisibilityofLocator(FirstName);
		firstName.sendKeys(ConfigReader.get("firstname"));	
		
		WebElement lastName = WaitUtils.VisibilityofLocator(LastName);
		lastName.sendKeys(ConfigReader.get("lastname"));	
		
		WebElement addressfield = WaitUtils.VisibilityofLocator(Address);
		addressfield.sendKeys(ConfigReader.get("address"));	
		
		WebElement statefield = WaitUtils.VisibilityofLocator(State);
		statefield.sendKeys(ConfigReader.get("state"));		
		
		WebElement postalfield = WaitUtils.VisibilityofLocator(pinCode);
		postalfield.sendKeys(ConfigReader.get("pincode"));	
		
	}
	
	public void clickOnSubmit() {
		WebElement submitButton = WaitUtils.VisibilityofLocator(submit);
		submitButton.click();
		System.out.println("User is able to checkout successfully");
	}
	
	public void successmessageValidation() {
		WebElement successMessage = WaitUtils.VisibilityofLocator(successmessage);
		String Text = successMessage.getText().trim();
		System.out.println("The Order Confirmation Message is: " + Text);
		
		Assert.assertEquals(Text, "Your Order has been successfully placed.","Your order is not successful");		
		WebElement order = WaitUtils.VisibilityofLocator(orderNumber);
		String orderNum = order.getText().trim();
		System.out.println("The order number generated after success is: "  + orderNum);		
	}
}
