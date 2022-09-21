package apoorvrastogitraining.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import apoorvrastogitraining.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
    
	@FindBy(xpath = "//p/following-sibling::h3")
	List<WebElement> cartproducts;
	
	@FindBy(css = ".totalRow button")
	WebElement continueButton;
	
	/*
	 * public List<WebElement> cartProducts() {
	 * 
	 * return cartproducts; }
	 */
	
	public Boolean productsMatch(String productName) {
		
		Boolean match=cartproducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PaymentPage continueShopping() {
		continueButton.click();
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
	}
}
