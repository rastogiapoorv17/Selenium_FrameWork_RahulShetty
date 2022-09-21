package apoorvrastogitraining.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import apoorvrastogitraining.AbstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents {
	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryList;

	@FindBy(css = ".ta-item:last-of-type")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	By results = By.cssSelector(".ta-results");
	By submitButton = By.cssSelector(".action__submit.ng-star-inserted");

	public void countryList() {
		Actions act = new Actions(driver);
		act.sendKeys(countryList, "India").build().perform();
		waitForElementToAppear(results);
		country.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}

	public ReceiptPage submitButton() {

		waitForElementToAppear(submitButton);
		submit.click();
		ReceiptPage receiptPage = new ReceiptPage(driver);
		return receiptPage;
	}

}
