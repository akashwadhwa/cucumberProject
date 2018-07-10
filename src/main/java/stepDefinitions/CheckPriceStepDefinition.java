package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.FlightsPage;
import pages.LandingPage;
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

		new LandingPage(driver).selectDepartureCity();
		Thread.sleep(10000);
		new LandingPage(driver).selectArrivalCity();
		new LandingPage(driver).selectDepartureDate();
		new LandingPage(driver).selectArrivalDate();
		new LandingPage(driver).clickSearchBtn();

	}

	@Then("^he should be shown the cheapest return ticket from DXB to LHR$")
	public void he_should_be_shown_the_cheapest_return_ticket_from_DXB_to_LHR() throws Throwable {
		new FlightsPage(driver).verifyLowestPriceText();
		new FlightsPage(driver).verifyLowestPriceValue();
	}


}
