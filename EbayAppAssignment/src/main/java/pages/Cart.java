package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import utilityClassPackage.BaseClass;
import utilityClassPackage.BaseReport;

public class Cart extends BaseClass {
	By productName = By.xpath("//*[@content-desc]//*[@class='android.widget.TextView']");
	By checkOutButton = By.xpath("//*[@class='android.widget.Button']");

	/**
	 * Description : Method to fetch Product name in Cart
	 * 
	 * Author : Prasad Sutar
	 * 
	 * 	  @param reportStatus--> is used to generate Extent Report
	 */
	public String fetchProductNameCart(BaseReport reportStatus)  {
		waitForElementClickable(productName);
		String name = getProductDetails(productName,reportStatus);
		return name;
	}

	/**
	 * Description : Method to tap on "CheckOut"
	 * 
	 * Author : Prasad Sutar
	 * 
	 * @param reportStatus --> is used to generate Extent Report
	 */
	public void checkOut(BaseReport reportStatus) {
		tap(checkOutButton, reportStatus);

	}
}
