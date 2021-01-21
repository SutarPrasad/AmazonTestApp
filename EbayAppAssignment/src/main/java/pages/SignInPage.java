package pages;

import org.openqa.selenium.By;

import com.aventstack.extentreports.model.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import utilityClassPackage.BaseClass;
import utilityClassPackage.BaseReport;

public class SignInPage extends BaseClass {

	By continueButton = By.id("com.amazon.mShop.android.shopping:id/sso_continue");
	By skipSignIn = By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button");

	/**
	 * Description : Method to Sign in into Application
	 * 
	 * Author : Prasad Sutar
	 * 
	 * @param reportStatus -->is used to generate Extent Report
	 * 
	 * Note : [ While mobile app was suggesting PreLogged in Account. So Clicked
	 * on CONTINUE instead of creating Account]
	 */
	public void clickOnContinue(BaseReport reportStatus)  {

		BaseClass clas = new BaseClass();
		clas.tap(continueButton, reportStatus);
		reportStatus.stepPassed("clickOnContinue method Passed");

	}
}
