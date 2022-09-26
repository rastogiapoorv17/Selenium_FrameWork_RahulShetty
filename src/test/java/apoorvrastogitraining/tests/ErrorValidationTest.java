package apoorvrastogitraining.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import apoorvrastogitraining.TestComponents.BaseTest;
import apoorvrastogitraining.TestComponents.Retry;
import apoorvrastogitraining.pageobjects.CartPage;
import apoorvrastogitraining.pageobjects.ProductCatalogPage;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		landingPage.loginApplication("rastogiiapoorv@gmail.com", "India1234");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String productName = "adidas original";
		ProductCatalogPage productPage = landingPage.loginApplication("rastogiapoorv1@gmail.com", "India123");

		productPage.getProductList();
		productPage.addProductToCart(productName);

		// Cart Page
		CartPage cartPage = productPage.clickOncart();
		Boolean match = cartPage.productsMatch(productName);
		Assert.assertTrue(match);

	}

}
