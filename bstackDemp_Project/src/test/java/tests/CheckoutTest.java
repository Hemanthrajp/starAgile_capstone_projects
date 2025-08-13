package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baseUtils.BaseFunction;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductPage;
import utils.ConfigReader;

public class CheckoutTest extends BaseFunction{
	
	static ProductPage productPage;
	static CartPage cart;
	static CheckoutPage checkout;
	
	@Test(priority = 7)
	public void checkOutWithNoItems() {
		test = Report.createTest("Check out with no items on cart");
		
		cart.getCartCount();
		cart.assertCartQuantity("0");
		cart.continueShopping();
		productPage.verifyProductPage();
		
		test.pass("Checkout with empty cart handled successfully.");
	}
	
	@Test(priority = 8)
	public void properCheckOut() throws InterruptedException {
		test = Report.createTest("Check out with single item on cart");
		
		String product = ConfigReader.get("product8");
		productPage.productCheckout(product);
		cart.checkoutClick();
		checkout.checkoutForm();
		checkout.clickOnSubmit();
		checkout.successmessageValidation();
		
		  test.pass("Proper checkout flow completed and validated.");
	}
	
	@BeforeTest
	public void beforeTest() {
		productPage = PageFactory.initElements(BaseFunction.driver, ProductPage.class);
		cart = PageFactory.initElements(BaseFunction.driver, CartPage.class);
		checkout = PageFactory.initElements(BaseFunction.driver, CheckoutPage.class);
	}
	
	@AfterTest
	public void afterTest() {
		BaseFunction.closeBrowser();
	}
}
