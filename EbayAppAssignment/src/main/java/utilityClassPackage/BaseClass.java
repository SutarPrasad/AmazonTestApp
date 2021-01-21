package utilityClassPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

public class BaseClass {

	public static AndroidDriver driver;
	public static DesiredCapabilities cap;
	Properties pro;

	public BaseClass() {
		cap = new DesiredCapabilities();
		pro = new Properties();
	}

	/**
	 * Description : Reusable function for Desired Capabilities and driver
	 * instantiating
	 * 
	 * Author : Prasad Sutar
	 **/
	public void capabilitiesAndInitialization(BaseReport reportStatus) {
		try {
			pro = propertyFileLoader("src\\main\\resources\\propertiesFiles\\desiredCapabilities.properties");
			cap.setCapability("deviceName", pro.getProperty("deviceName"));
			cap.setCapability("platformName", pro.getProperty("platformName"));
			cap.setCapability("udid", pro.getProperty("udid"));
			cap.setCapability("app", pro.getProperty("app"));
			cap.setCapability("appActivity", pro.getProperty("appActivity"));
			cap.setCapability("appPackage", pro.getProperty("appPackage"));
			cap.setCapability("hubUrl", pro.getProperty("hubUrl"));
			driver = new AndroidDriver<AndroidElement>(new URL(pro.getProperty("hubUrl")), cap);
			reportStatus.stepPassed("Driver Initialized");
		} catch (Exception e) {
			e.printStackTrace();
			reportStatus.stepFailed(e.getMessage());
			Assert.assertTrue(e.getMessage(), false);
		}

	}

	/**
	 * Description : Reusable method to load Property Files
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param filePath-->Provides
	 *            file path of the file to be loaded
	 */
	public Properties propertyFileLoader(String filePath) {
		try {
			FileInputStream fileIn = new FileInputStream(filePath);
			pro.load(fileIn);
		} catch (Exception e) {
			e.printStackTrace();

			Assert.assertTrue(e.getMessage(), false);
		}
		return pro;
	}

	/**
	 * Description : Reusable method for Explicit wait
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param element-->for
	 *            passing inspected element(xpath,id,class etc)
	 * @param reportStatus
	 *            -->is used to generate Extent Report.
	 */
	public void waitForElementClickable(By element, BaseReport reportStatus) {
		try {
			WebDriverWait wait2 = new WebDriverWait(driver, 50);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(element));
			reportStatus.stepPassed("Element is clickable");
		} catch (Exception e) {
			e.printStackTrace();
			reportStatus.stepFailed("Element is" + e.getMessage());
			Assert.assertTrue(e.getMessage(), false);
		}
	}

	/**
	 * Description : Reusable method for clicking element
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param element-->
	 *            for passing inspected element(xpath,id,class etc)
	 * @param reportStatus-->
	 *            is used to generate Extent Report
	 * 
	 */
	public void tap(By element, BaseReport reportStatus) {
		try {
			driver.findElement(element).click();
			reportStatus.stepPassed("Tapped on Element");
		} catch (Exception e) {
			e.printStackTrace();
			reportStatus.stepFailed(e.getMessage());
			Assert.assertTrue(e.getMessage(), false);
		}
	}

	/**
	 * Description : Reusable method for Vertical Swipe Up
	 * 
	 * Author-Prasad Sutar
	 * 
	 * @param reportStatus-->
	 *            to generate Extent Report
	 **/
	public void swipeUp(BaseReport reportStatus) {
		try {
			Thread.sleep(2000);
			Dimension scrnSize = driver.manage().window().getSize();
			int x1 = (int) (scrnSize.width * 0.5);
			int y2 = (int) (scrnSize.height * 0.9);
			int y1 = (int) (scrnSize.height * 0.2);

			(new TouchAction(driver)).press(PointOption.point(x1, y2)).moveTo(PointOption.point(x1, y1)).release()
					.perform();

		} catch (Exception e) {
			e.printStackTrace();
			reportStatus.stepFailed(e.getMessage());
			Assert.assertTrue(e.getMessage(), false);
		}
	}

	/**
	 * 
	 * Description :Reusable method for sending data to text fields
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param element-->for
	 *            passing inspected element(xpath,id,class etc)
	 * @param excelValue-->Value
	 *            of product is fetched from the excel file.
	 * @param reportStatus-->is
	 *            used to generate Extent Report
	 * 
	 */
	public void sendText(By element, String excelValue, BaseReport reportStatus) {
		try {
			driver.findElement(element).click();
			driver.findElement(element).sendKeys(excelValue);

			reportStatus.stepPassed("Value sent  " + excelValue);
		} catch (Exception e) {
			e.printStackTrace();
			reportStatus.stepFailed(e.getMessage());
			Assert.assertTrue(e.getMessage(), false);
		}
	}

	/**
	 * Description : Reusable method for random Product selection
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param listElement-->Fetches
	 *            list of elements suggested
	 * @param primaryProduct-->selects
	 *            any one of the product.
	 * @param reportStatus-->is
	 *            used to generate Extent Report
	 * 
	 */
	public void randomProductSelection(By listElement, By primaryProduct, BaseReport reportStatus) {
		try {
			List<AndroidElement> searchList = driver.findElements(listElement);
			if (searchList.isEmpty()) {
				reportStatus.stepFailed("Empty List");
			} else {
				Random rand = new Random();
				int index = rand.nextInt(searchList.size());
				searchList.get(index).findElement(primaryProduct).click();
				reportStatus.stepPassed("Random Selection Successfull");
			}
		} catch (Exception e) {
			reportStatus.stepFailed(e.getMessage());
			Assert.assertTrue(e.getMessage(), false);
		}
	}

	/**
	 * Description :Reusable method to fetch the product detail
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param element-->for
	 *            passing inspected element(xpath,id,class etc)
	 * @param reportStatus-->is
	 *            used to generate Extent Report
	 * 
	 */
	public String getProductDetails(By element, BaseReport reportStatus) {
		String textValue = "";
		try {
			textValue = driver.findElement(element).getText();
			if (textValue != null) {
				reportStatus.stepPassed("got product details");
			}
			return textValue;
		} catch (Exception e) {
			e.printStackTrace();
			reportStatus.stepFailed(e.getMessage());
			Assert.assertTrue(e.getMessage(), false);

		}
		return textValue;
	}

	/**
	 * Description :Assertion method to verify product details added to cart
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param productName-->fetches
	 *            product name from product page
	 * @param cartProductElement-->fetches
	 *            product name in cart page
	 * @param reportStatus-->is
	 *            used to generate Extent Report
	 * 
	 */
	public void verifyProductDetails(String productName, String cartProductElement, BaseReport reportStatus) {
		String message = "Verification failed";
		try {
			if (productName.contains(cartProductElement)) {
				message = "Details verified successfully";
				Assert.assertTrue(message, true);
				reportStatus.stepPassed(message);

			} else {
				Assert.assertFalse(false);
				message = "False";
				reportStatus.stepFailed(message);
			}

		} catch (Exception e) {
			e.getStackTrace();
			reportStatus.stepFailed(e.getMessage());
		}
	}

}
