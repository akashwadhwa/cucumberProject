package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.DriverFactory;

public class LandingPage extends DriverFactory{

	WebDriver driver;

	@FindBy(xpath = "(//div[@class='dropdown__input-container js-dropdown-open'])[1]")
	private WebElement departureInputField;

	@FindBy(xpath = "//input[@name='Departure airport']")
	private WebElement departureInputFieldBox;

	@FindBy(xpath = "//input[@name='Arrival airport']")
	private WebElement arrivalInputFieldBox;

	@FindBy(xpath = "(//div[@class='dropdown__input-container js-dropdown-open'])[2]")
	private WebElement arrivalInputField;

	@FindBy(xpath = "(//a[contains(text(),'14')])[1]")
	private WebElement departureDateLocator;

	@FindBy(xpath = "(//a[contains(text(),'14')])[1]")
	private WebElement arrivalDateLocator;

	@FindBy(xpath = "//button//span[contains(text(),'Search flights')]")
	private WebElement searchFlightsBtn;

	@FindBy(xpath = "//p[contains(text(),'DXB')]")
	private WebElement departureAirportValue;

	@FindBy(xpath = "//p[contains(text(),'LHR')]")
	private WebElement arrivalAirportValue;


	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectDepartureCity() throws InterruptedException{
		Actions act = new Actions(driver); 
		departureInputField.click();
		departureInputFieldBox.sendKeys("DXB");
		Thread.sleep(2000);
		WebElement departureAirportValue = driver.findElement(By.xpath("//p[contains(text(),'DXB')]"));
		act.moveToElement(departureAirportValue).click().build().perform();
	}
	
	public void selectArrivalCity() throws InterruptedException{
		Actions act = new Actions(driver); 
		arrivalInputField.click();
		arrivalInputFieldBox.sendKeys("LHR");
		Thread.sleep(2000);
		WebElement arrivalAirportValue = driver.findElement(By.xpath("//p[contains(text(),'LHR')]"));
		act.moveToElement(arrivalAirportValue).click().build().perform();
	}
	
	public void selectDepartureDate() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click();", departureDateLocator);
	}
	
	public void selectArrivalDate() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click();", arrivalDateLocator);
	}
	
	public FlightsPage clickSearchBtn(){
		searchFlightsBtn.click();
		return new FlightsPage(driver);
	}

}
