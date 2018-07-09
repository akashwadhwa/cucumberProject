package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.DriverFactory;

public class FlightsPage extends DriverFactory{

	WebDriver driver;

	@FindBy(xpath = "//h2[contains(text(),'Lowest price for all passengers')]")
	private WebElement lowestPriceText;

	@FindBy(xpath = "//span[contains(@class,'summary-curr-only')]")
	private WebElement lowestPrice;

	public FlightsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyLowestPriceText(){
		Assert.assertEquals(true, lowestPriceText.isDisplayed());
	}
	
	public void verifyLowestPriceValue(){
		Assert.assertEquals(true, lowestPrice.isDisplayed());
	}

}
