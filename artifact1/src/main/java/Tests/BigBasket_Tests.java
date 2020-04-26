package Tests;

import org.junit.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjects.BigBasket;

public class BigBasket_Tests {
	WebDriver driver;

	BigBasket page;
	// Declare a test URL variable
	public String testURL = "https://www.bigbasket.com/";

	@Before
	public void setupTest() {
	
		//set the path of the chrome driver in below method 
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
		// Create a new ChromeDriver
		driver = new ChromeDriver();
		driver.navigate().to(testURL);
		driver.manage().window().maximize();
	}

	@After
	public void teardownTest() {
		// Close browser and end the session
		driver.quit();
	}

	@Test
	public void fruitsAndVegetablesCountVerification() throws InterruptedException {
		page = new BigBasket(driver);
		page.navigateToFruitsSection();
		page.captureHeadingCount();
		page.scrollToPageEnd();
		page.clickOnShowMore();
		page.checkPageEnd();
		page.verifyCounts();
	}

	@Test
	public void addBeautyItemtoCart() throws InterruptedException {
		page = new BigBasket(driver);
		page.getCartCountBefore();
		page.navigateToBeautySection();
		page.filterWithBrand();
		page.addToCart();
		page.verifyCartCount();
	}

}
