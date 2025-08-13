package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import baseUtils.BaseFunction;
import utils.WaitUtils;

public class CartPage {
	
	 private By quantity = By.xpath("//span[@class='bag__quantity']");
	 private By removeItem = By.xpath("//div[@class='shelf-item__del']"); 
	 private By cartOpen = By.cssSelector(".bag.bag--float-cart-closed");
	 private By closebutton = By.xpath("//div[@class='float-cart__close-btn']");
	 private By continueShoppingBtn = By.xpath("//div[@class='buy-btn']");
	 private By checkoutButton = By.cssSelector(".buy-btn");
	 
	 public void removeItemsFromCart() {
		 WaitUtils.LocatorClickable(removeItem);
		 List<WebElement> removeBtn = BaseFunction.driver.findElements(removeItem);
		 for(WebElement remove : removeBtn) {
			 WaitUtils.ElementClickable1(remove);
			 remove.click();
		 }
	 }
	 
	 public int getCartCount() {
		 WebElement elementQnt = WaitUtils.VisibilityofLocator(quantity);
		 String quantity = elementQnt.getText().trim();
		 System.out.println("The total number of items in the cart: " + quantity);
		 return Integer.parseInt(quantity);
	 }
	 
	 public void openCart() {
		 WebElement openCart = WaitUtils.VisibilityofLocator(cartOpen);
		 openCart.click();
	 }
	 
	 public void closeCart() {
		 WebElement closebtn = WaitUtils.VisibilityofLocator(closebutton);
			BaseFunction.JSClick(closebtn);		
			WaitUtils.ElementInvisibilityByLocator(closebutton);	
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	 }
	 
	 public void assertCartQuantity(String expected) {
	     WebElement count = WaitUtils.VisibilityofLocator(quantity);
	     Assert.assertEquals(count.getText().trim(), expected, "Cart quantity mismatch!");
	 }
	 
	public void continueShopping() {
		WebElement shoppingButton = WaitUtils.VisibilityofLocator(continueShoppingBtn);
		shoppingButton.click();	
     }
	
	public void checkoutClick() {
		WebElement ckbtn = WaitUtils.VisibilityofLocator(checkoutButton);
		String text = ckbtn.getText();
		
		Assert.assertEquals(text, "CHECKOUT","This is not a checkout button");
		ckbtn.click();
		System.out.println("User able to click on checkout");
	 }
}
