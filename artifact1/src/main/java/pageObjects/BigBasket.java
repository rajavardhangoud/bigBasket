package pageObjects;

import org.openqa.selenium.*;

public class BigBasket extends BaseObject {

	public BigBasket(WebDriver driver) {
		super(driver);
	}

	int headingCount;
	int actualCount;
	int countBefore;
	int countAfter;
	String pageEnd;
	By shopBy = By.xpath("//a[text()=' Shop by Category ']");
	By fruitsAndVegetables = By.xpath("//ul[@id='navBarMegaNav']//a[text()='Fruits & Vegetables']");
	By totalCount = By.xpath("//input[@id='snowplow_screen_view_totalcount']");
	By showmore = By.xpath("//button[contains(text(),'Show More')]");
	By pageEndText = By.xpath("//div[contains(text(),'all folks')]");
	By totalProducts = By.xpath("//div[@qa='product']");
	By cartCount = By.xpath("//span[@qa='itemsCount']");
	By beauty = By.xpath("//ul[@id='navBarMegaNav']//a[text()='Beauty & Hygiene']");
	By searchBrand = By.xpath("//input[@placeholder='Search by Brand']");
	By brandCheckBox = By.xpath("//span[text()='Lakme']");
	By lakmeProduct = By.xpath("//div[@qa='product_name']/h6[text()='Lakme']/following-sibling::a[text()='Nail Colour Remover']/following::button[1]");

	public BigBasket navigateToFruitsSection() {
		click(shopBy);
		actionClick(fruitsAndVegetables);
		return new BigBasket(driver);
	}

	public BigBasket captureHeadingCount() {
		headingCount = Integer.parseInt(getAttribute(totalCount, "value"));
		return new BigBasket(driver);

	}

	public BigBasket clickOnShowMore() {
		scrollABit(-100);
		while (isElementDisplayed(showmore) == true) {
			actionClick(showmore);
			if (isElementDisplayed(showmore) == false)
				break;
		}

		return new BigBasket(driver);
	}

	public BigBasket checkPageEnd() {
		pageEnd = getAttribute(pageEndText, "outerText");
		return new BigBasket(driver);
	}

	public BigBasket verifyCounts() {
		actualCount = totalNumberOfElements(totalProducts);
		assertCounts("Product count displayed on Heading is not equal to the products displayed", headingCount,
				actualCount);
		return new BigBasket(driver);
	}

	public BigBasket navigateToBeautySection() {
		click(shopBy);
		actionClick(beauty);
		return new BigBasket(driver);
	}

	public BigBasket filterWithBrand() throws InterruptedException {
		scrollABit(300);
		waitTime(2000);
		scrollToElement(searchBrand);
		waitTime(2000);
		actionClick(brandCheckBox);
		return new BigBasket(driver);
	}

	public BigBasket addToCart() throws InterruptedException {
			waitTime(2000);
			jsClick(lakmeProduct);	
		return new BigBasket(driver);
	}

	public BigBasket getCartCountBefore() {
		countBefore = Integer.parseInt(getAttribute(cartCount, "outerText").split(" ")[0]);
		return new BigBasket(driver);
	}

	public BigBasket verifyCartCount() throws InterruptedException {
		waitTime(2000);
		countAfter = Integer.parseInt(getAttribute(cartCount, "outerText").split(" ")[0]);
		assertCounts("Cart count not incremented by 1", countBefore + 1, countAfter);
		return new BigBasket(driver);
	}

}
