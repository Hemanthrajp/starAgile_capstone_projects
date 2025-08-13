package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import baseUtils.BaseFunction;
import utils.WaitUtils;

public class ProductPage {
	
	CartPage cartPage = new CartPage();
	
	private By getProductLocator(String productName) {
		String xpath = "//p[text()='" + productName + "']/ancestor::div[contains(@class,'shelf-item')]//div[text()='Add to cart']";
		return By.xpath(xpath);
	}
	
	public void addProductToCart(String productName) {
		 By locator = getProductLocator(productName);
		 WebElement product = WaitUtils.VisibilityofLocator(locator);
		 BaseFunction.scroll(product);
		 product = WaitUtils.LocatorClickable(locator);
		 BaseFunction.JSClick(product);
	}
	
	public void addMultipleProducts(String[] products) {
		for(String product : products) {
			addProductToCart(product);
			cartPage.closeCart();
		}
	}
	
	public void productCheckout(String productName) {
		addProductToCart(productName);
		System.out.println("Product in checkout");
	}
	
	public void verifyProductPage() {
		By totalProduct = By.xpath("//small[@class='products-found']");
		WebElement totalProducts = WaitUtils.VisibilityofLocator(totalProduct);
		String productCount = totalProducts.getText().split(" ")[0];
		System.out.println("Total products: " + productCount);
		System.out.println("User remains in product selection page");
	}
}
