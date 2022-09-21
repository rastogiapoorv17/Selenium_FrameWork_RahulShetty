package apoorvrastogitraining.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceiptPage {

	WebDriver driver;

	public ReceiptPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement messageText;

	public String finalMessage() {
		String actualMessage = messageText.getText();
		return actualMessage;
	}
}
