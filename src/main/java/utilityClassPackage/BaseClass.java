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
	public void capabilitiesAndInitialization(BaseReport reportStatus) throws Exception {
		try {
			pro = propertyFileLoader("C:\\Users\\PRASAD\\eclipse-workspace\\EbayAppAssignment\\src\\main\\java\\propertiesFiles\\desiredCapabilities.properties");
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
	 * @param filePath
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
	 * @param element
	 */
	public void waitForElementClickable(By element) {
		try {
			WebDriverWait wait2 = new WebDriverWait(driver, 50);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(element));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
		}
	}

	/**
	 * Description : Reusable method for clicking element
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param element
	 * @param reportStatus
	 * @throws Exception
	 */
	public void tap(By element, BaseReport reportStatus) throws Exception {
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
	 * @throws Exception 
	 **/
	public void swipeUp() throws Exception {
		try {
			TouchAction swipe = new TouchAction(driver).press(PointOption.point(834, 1604))

					.moveTo(PointOption.point(923, 687)).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
		}
	}

	/**
	 * 
	 * Description :Reusable method for sending data to text fields
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param element
	 * @param excelValue
	 * @param reportStatus
	 * @throws Exception
	 */
	public void sendText(By element, String excelValue, BaseReport reportStatus) throws Exception {
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
	 * @param listElement
	 * @param primaryProduct
	 * @param reportStatus
	 * @throws Exception
	 */
	public void randomProductSelection(By listElement, By primaryProduct, BaseReport reportStatus) throws Exception {
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
	 * @param element
	 * @param reportStatus
	 * @return
	 * @throws Exception
	 */
	public String getProductDetails(By element) throws Exception {
		String textValue = "";
		try {
			textValue = driver.findElement(element).getText();

			return textValue;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);

		}
		return textValue;
	}

	/**
	 * Description :Assertion method to verify product details added to cart
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param name
	 * @param cartProductElement
	 * @param reportStatus
	 * @throws Exception
	 */
	public void verifyProductDetails(String productName, String cartProductElement, BaseReport reportStatus)
			throws Exception {
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
