package sampleTest;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Cart;
import pages.HomePage;
import pages.ProductsPage;
import pages.SignInPage;
import utilityClassPackage.BaseClass;
import utilityClassPackage.BaseReport;
import utilityClassPackage.ExcelFileClass;

public class AmazonTestApp extends BaseClass {
	BaseReport extentReport;
/**
 * Description : Constructor for instantiating class variables and parent class
 * 
 * Author : Prasad Sutar
 */
	public AmazonTestApp() {
		super();
	}

	/**
	 * Description : Executed before test by instantiation for report generation
	 * driver and desired capabilities setup
	 * 
	 * Author : Prasad Sutar
	 * 
	 */
	@BeforeTest
	public void setUp()  {
		AmazonTestApp app = new AmazonTestApp();
		extentReport = new BaseReport( );
		extentReport.setUpReport();
		extentReport.test = extentReport.report.createTest("Ebay Assignment Testcase Report");
		capabilitiesAndInitialization(extentReport);
	}

	/**
	 * Description : Test method-->POM
	 * 
	 * Author : Prasad Sutar
	 * 
	 */
	@Test
	public void productVerifyTest() {
		SignInPage s = new SignInPage();

		s.clickOnContinue(extentReport);
		ExcelFileClass readExcelFile = new ExcelFileClass();
		readExcelFile.storeExcelValue("AmazonTest");
		new HomePage().productSearch(extentReport, readExcelFile);

		new HomePage().randomProductSelection(extentReport);

		new ProductsPage().selectionRandomProduct(extentReport);
		String productName = new ProductsPage().fetchingProductDetails(extentReport);

		new ProductsPage().addToCart(extentReport);

		new ProductsPage().clickOnCartLogo(extentReport);
		String cartProductName = new Cart().fetchProductNameCart(extentReport);

		verifyProductDetails(productName, cartProductName, extentReport);

		new Cart().checkOut(extentReport);

	}

	/**
	 * Description : Close Driver and report If failed or Skipped
	 * 
	 * Author : Prasad Sutar
	 * @param result --> to fetch overall result object of ITestResult
	 */
	@AfterMethod
	public void afterMethod(ITestResult result)  {

		if (ITestResult.FAILURE == result.getStatus()) {
			extentReport.stepFailed(result.getThrowable().getMessage());
		}

		driver.quit();
		extentReport.report.flush();
	}

}
