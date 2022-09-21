package apoorvrastogitraining.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import apoorvrastogitraining.TestComponents.BaseTest;
import apoorvrastogitraining.TestComponents.Retry;
import apoorvrastogitraining.pageobjects.CartPage;
import apoorvrastogitraining.pageobjects.OrderPage;
import apoorvrastogitraining.pageobjects.PaymentPage;
import apoorvrastogitraining.pageobjects.ProductCatalogPage;
import apoorvrastogitraining.pageobjects.ReceiptPage;

public class SubmitOrderTest extends BaseTest {
	// String productName = "adidas original";

	@Test(dataProvider = "getData", groups = { "Purchase" },retryAnalyzer=Retry.class)
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		ProductCatalogPage productPage = landingPage.loginApplication(input.get("email"), input.get("password"));

		productPage.getProductList();
		productPage.addProductToCart(input.get("product"));

		// Cart Page
		CartPage cartPage = productPage.clickOncart();
		Boolean match = cartPage.productsMatch(input.get("product"));
		Assert.assertTrue(match);

		// Payment Page
		PaymentPage paymentPage = cartPage.continueShopping();
		paymentPage.countryList();

		// ReceiptPage
		ReceiptPage receiptPage = paymentPage.submitButton();
		String actualMessage = receiptPage.finalMessage();
		Assert.assertTrue(actualMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData")
	public void orderHistoryTest(HashMap<String, String> input) {

		ProductCatalogPage productPage = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage ordersPage = productPage.clickOnOrder();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(input.get("product")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "//src//test//java//apoorvrastogitraining//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	

}

/*
 * @DataProvider public Object[][] getData() {
 * 
 * return new Object[][] { { "rastogiapoorv@gmail.com", "India123",
 * "adidas original" }, { "rastogiapoorv1@gmail.com", "India123",
 * "iphone 13 pro" } }; }
 */

/*
 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
 * "rastogiapoorv@gmail.com"); map.put("password", "India123");
 * map.put("product", "adidas original");
 * 
 * HashMap<String, String> map1 = new HashMap<String, String>();
 * map1.put("email", "rastogiapoorv1@gmail.com"); map1.put("password",
 * "India123"); map1.put("product", "iphone 13 pro");
 */