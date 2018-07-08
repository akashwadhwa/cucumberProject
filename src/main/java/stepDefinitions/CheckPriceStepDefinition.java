package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.DriverFactory;
import utils.PropertyReader;

public class CheckPriceStepDefinition extends DriverFactory{
	
	private WebDriver driver;
	String url = new PropertyReader().readProperty("url");
	
	@Before
	public void beforeScenario() {
		driver = new DriverFactory().getDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("this will run before the actual scenario");
	}

	@After
	public void afterScenario() {
		new DriverFactory().destroyDriver();
		System.out.println("this will run after scneario is finished, even if it failed");
	}

	@Given("^that Bill has decided to check available flights$")
	public void that_Bill_has_decided_to_check_available_flights() throws Throwable {
		driver.manage().window().maximize();
		driver.get(url);
	}

	@When("^he looks at a return trip from DXB to LHR leaving one week from now$")
	public void he_looks_at_a_return_trip_from_DXB_to_LHR_leaving_one_week_from_now() throws Throwable {
		WebElement departureInputField =  driver.findElement(By.xpath("//input[@name='Departure airport']"));
		WebElement arrivalInputField =  driver.findElement(By.xpath("//input[@name='Arrival airport']"));
		WebElement searchFlightDatePicker = driver.findElement(By.id("search-flight-date-picker--depart"));
		WebElement departureDateLocator = driver.findElement(By.xpath("(//a[contains(text(),'14')])[1]"));
		WebElement arrivalDateLocator = driver.findElement(By.xpath("(//a[contains(text(),'14')])[1]"));
		WebElement searchFlightsBtn = driver.findElement(By.xpath("//button//span[contains(text(),'Search flights')]"));
		WebElement banner = driver.findElement(By.xpath("//div[contains(@class,'hero__content')]"));
		WebElement continueBtn = driver.findElement(By.xpath("//span[contains(text(),'Continue')]"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].value='DXB';", departureInputField);
		js.executeScript("arguments[0].value='LHR';", arrivalInputField);
		js.executeScript("var evt = document.createEvent('HTMLEvents'); evt.initEvent('change',true,true); arguments[0].dispatchEvent(evt);",arrivalInputField);
		continueBtn.click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", departureDateLocator);
		js.executeScript("arguments[0].click();", arrivalDateLocator);
		banner.click();
		searchFlightsBtn.click();

	}

	@Then("^he should be shown the cheapest return ticket from DXB to LHR$")
	public void he_should_be_shown_the_cheapest_return_ticket_from_DXB_to_LHR() throws Throwable {
		WebElement lowestPriceText = driver.findElement(By.xpath("//h2[contains(text(),'Lowest price for all passengers')]"));
		WebElement lowestPrice = driver.findElement(By.xpath("//span[contains(@class,'summary-curr-only')]"));
		if(!lowestPriceText.isDisplayed()){
			Assert.assertEquals(true, lowestPriceText.isDisplayed());
			Assert.assertEquals(true, lowestPrice.isDisplayed());
		}
	}


}
