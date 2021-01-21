
package pages;

import java.io.FileInputStream;
import java.util.List;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import utilityClassPackage.BaseClass;
import utilityClassPackage.BaseReport;
import utilityClassPackage.ExcelFileClass;
import utilityClassPackage.*;
public class HomePage extends BaseClass {

	By searchBox = By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text");
	By searchListOptions = By.xpath(
			"//*[@class='android.widget.ListView']//*[@resource-id='com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_suggestions']");
	By primaryProduct = By.className("android.widget.LinearLayout");

	/**
	 * Description : Method to search product by Tapping on Search Box
	 * 
	 * Author : Prasad Sutar
	 *
	 * @param reportStatus --> is used to generate Extent Report
	 * @param readExcelFile --> used for Excel Parameterization --> FileLocation-->src\main\resources\ExcelFile\ProductData.xlsx
	 */
	public void productSearch(BaseReport reportStatus,ExcelFileClass readExcelFile) {
		waitForElementClickable(searchBox);
		tap(searchBox, reportStatus);
		  readExcelFile.storeExcelValue();
		  String cellValue = readExcelFile.productNameList();

		sendText(searchBox, cellValue, reportStatus);
		reportStatus.stepPassed("Product Search method Passed");
	}

	/**
	 * Description : Method to select random Product from List of suggested
	 * options
	 * 
	 * Author : Prasad Sutar
	 * 
	 * @param reportStatus --> is used to generate Extent Report
	 *
	 */
	public void randomProductSelection(BaseReport reportStatus)   {
		waitForElementClickable(searchListOptions);
		randomProductSelection(searchListOptions, primaryProduct, reportStatus);

		reportStatus.stepPassed("Random Product Selection Passed");
	}

}
