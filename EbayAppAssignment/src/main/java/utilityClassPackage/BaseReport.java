package utilityClassPackage;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseReport extends BaseClass {
	public ExtentHtmlReporter extentHtmlReport;
	public ExtentReports report;
	public ExtentTest test;
	DateFormat dateFormat;

	/**
	 * Description : Report initiation and file path declaration
	 * 
	 * Author : Prasad Sutar
	 * 
	 */
	public void setUpReport() {
		extentHtmlReport = new ExtentHtmlReporter(new File(
				System.getProperty("user.dir")+"\\Reports\\EBayAppReport.html"));
		report = new ExtentReports();
		report.attachReporter(extentHtmlReport);
	}

	/**
	 * Description :Reusable method to take screenshot returns destination
	 * filepath
	 * 
	 * Author Prasad Sutar
	 * 
	 */
	public String screenShot()   {
		String filePath="";
		try{
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		String fileName = dateFormat.format(new Date()) + ".png";
		 filePath = "Reports/screenshots/s"
				+ fileName;
		FileUtils.copyFile(f,
				new File(
						filePath));}
	catch(Exception e){
		e.printStackTrace();
	}
		
		return filePath;
	}

	/**
	 * 
	 * Description : Method to log Passed Test Step adds Screenshot to report
	 * 
	 * Author Prasad Sutar
	 * 
	 * 
	 * @param value--> To pass comments as per requirement
	 */
	public void stepPassed(String value) {

		String screenShotPath = screenShot();
		try {
			test.addScreenCaptureFromPath(screenShotPath);
			this.test.pass(value);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());

		}
	}

	/**
	 * Description : Method to Log Failed Test Step adds screenshot to report
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param value--> To passing the comment as per requirement
	 */
	public void stepFailed(String value)  {
		String screenShotPath = screenShot();
		try {
			this.test.fail(value);
			test.addScreenCaptureFromPath(screenShotPath);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/**
	 * Description : Method to Log Skipped Test Step
	 * 
	 * Author Prasad Sutar
	 * 
	 * @param value--> To pass any comments as per requirement
	 */
	public void stepSkipped(String value) {
		this.test.skip(value);
	}
}
