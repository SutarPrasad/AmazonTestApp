package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import utilityClassPackage.BaseClass;
import utilityClassPackage.BaseReport;
import utilityClassPackage.ExcelFileClass;

public class ProductsPage extends BaseClass {
 	By suggestedProductList = By.xpath(
			"//*[@class='android.widget.LinearLayout']//*[@resource-id='com.amazon.mShop.android.shopping:id/list_product_linear_layout']");
	By productSelected = By.className("android.widget.LinearLayout");
	By productSpecification = By.xpath("//*[@resource-id='title_feature_div']//*[@class='android.view.View']");
	By addToCart = By.xpath("//*[@resource-id='a-autoid-2']//*[@class='android.widget.Button']");
	By openCart = By.id("com.amazon.mShop.android.shopping:id/action_bar_cart_image");

	/**
	 * Description : Method to select random Product from List of suggested
	 * products
	 * 
	 * Author : Prasad Sutar
	 * 
	 * @param reportStatus --> is used to generate Extent Report
 	 */
	public void selectionRandomProduct(BaseReport reportStatus)  {
		waitForElementClickable(suggestedProductList);
		randomProductSelection(suggestedProductList, productSelected, reportStatus);
		reportStatus.stepPassed("selectionRandomProduct Passed");
	}

	/**
	 * Description : Method to fetch product name to verify in the end
	 * 
	 * Author : Prasad Sutar
	 * 
	 * @param reportStatus --> is used to generate Extent Report
	 */
	public String fetchingProductDetails(BaseReport reportStatus)  {
		waitForElementClickable(productSpecification);
		String name = getProductDetails(productSpecification,reportStatus);
		reportStatus.stepPassed("fetchingProductDetails Passed");

		return name;

	}

	/**
	 * Description : Method to tap on "Add to Cart" Button
	 * 
	 * Author : Prasad Sutar
	 * 
	 * @param reportStatus --> is used to generate Extent Report
	 */
	public void addToCart(BaseReport reportStatus)  {
		swipeUp();
		waitForElementClickable(addToCart);
		tap(addToCart, reportStatus);

		reportStatus.stepPassed("addToCart method Passed");
	}

	/**
	 * Description : Method to tap on "Cart logo"
	 * 
	 * Author : Prasad Sutar
	 * 
	 * @param reportStatus --> is used to generate Extent Report
	 * 
	 */
	public void clickOnCartLogo(BaseReport reportStatus) {
		waitForElementClickable(openCart);
		tap(openCart, reportStatus);
		reportStatus.stepPassed("clickedOn cart logo");

	}

}