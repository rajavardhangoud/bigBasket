package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class BaseObject {

	public WebDriver driver;
	Actions action;
	JavascriptExecutor js;

	// Constructor
	public BaseObject(WebDriver driver) {
		this.driver = driver;
		this.action = new Actions(driver);
		this.js = (JavascriptExecutor) driver;
	}

	// Click Method
	public void click(By element) {
		try{
			driver.findElement(element).click();
		}
		catch(NoSuchElementException e){
			System.out.println(element + " not found");
		}		
	}

	public void actionClick(By element) {
		try{
			action.moveToElement(driver.findElement(element)).click().build().perform();
		}
		catch(NoSuchElementException e){
			System.out.println(element + " not found");
		}	
	}

	public void jsClick(By element) {
		try{
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(element));
		}
		catch(NoSuchElementException e){
			System.out.println(element + " not found");
		}	
	}



	public void scrollToPageEnd() throws InterruptedException {
		long lastHeight = ((Number) js.executeScript("return document.body.scrollHeight")).longValue();
		while (true) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(2000);

			long newHeight = ((Number) js.executeScript("return document.body.scrollHeight")).longValue();
			if (newHeight == lastHeight) {
				break;
			}
			lastHeight = newHeight;
		}
	}

	public void scrollABit(long i) {
		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + i + ")");

	}

	public void scrollToElement(By element) {
		try{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
		}
		catch(NoSuchElementException e){
			System.out.println("Element not found");
		}
	}

	public String getAttribute(By element, String value) {
		return driver.findElement(element).getAttribute(value).toString();
	}

	public boolean isElementDisplayed(By element) {
		return driver.findElement(element).isDisplayed();
	}

	public int totalNumberOfElements(By element) {
		return driver.findElements(By.xpath("//div[@qa='product']")).size();

	}

	public void waitTime(long sec) throws InterruptedException {
		Thread.sleep(sec);
	}

	public void assertCounts(String message, int expected, int actual) {
		Assert.assertEquals(message, expected, actual);
	}
}
