package apoorvrastogitraining.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import apoorvrastogitraining.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);     //sending driver to parent class
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement submit;
	
	//.ng-tns-c4-23.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogPage loginApplication(String email, String password) {
		// TODO Auto-generated method stub
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		submit.click();
		ProductCatalogPage productPage = new ProductCatalogPage(driver);
		return productPage;
       
	}
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);;
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://www.rahulshettyacademy.com/client");
	}
}
