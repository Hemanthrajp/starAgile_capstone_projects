package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baseUtils.BaseFunction;
import pages.CartPage;
import pages.ProductPage;
import utils.ConfigReader;

public class AddToCartTest extends BaseFunction{
	
	static CartPage cart;
	static ProductPage productPage;
	
	@Test(priority = 4)
	public void addSingleItem() {
		test = Report.createTest("Add single Item");
		
		String product = ConfigReader.get("product1");
		productPage.addProductToCart(product);
		cart.getCartCount();
		cart.assertCartQuantity("1");
		
		test.pass("Single item added to cart successfully");
		cart.closeCart();
	}
	
	@Test(priority = 5)
	public static void addMultipleItems(){
		test = Report.createTest("Add Multiple Items");
		  
		String[] products = {
				ConfigReader.get("product2"),
				ConfigReader.get("product3"),
				ConfigReader.get("product4")
			};
		productPage.addMultipleProducts(products);
		cart.getCartCount();
		cart.assertCartQuantity("4");
		  
		test.pass("Multiple items added to cart successfully");
	 }
	
	 @Test(priority = 6)
	public void removeItems() {
		test = Report.createTest("Remove Items");
		cart.openCart();
		cart.removeItemsFromCart();
	    cart.getCartCount();
		cart.assertCartQuantity("0");
		
		test.pass("Multiple items Removed from cart successfully");
	}
	 
	 @BeforeTest
	 public void beforeTest() {
		cart = PageFactory.initElements(BaseFunction.driver, CartPage.class);  
		productPage = PageFactory.initElements(BaseFunction.driver, ProductPage.class);
	}
}
