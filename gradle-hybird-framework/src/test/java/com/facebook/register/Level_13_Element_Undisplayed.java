package com.facebook.register;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.facebook.LoginPageObject;
import pageObject.facebook.PageGeneratorManager;


public class Level_13_Element_Undisplayed extends BaseTest {
	private LoginPageObject loginPage;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPageObject(driver);
	}

	@Test
	public void TC_01_Verify_Element_Display() {
		loginPage.clickToCreateNewAccountButton();
		verifyTrue(loginPage.isEmailTextBoxDisplayed());
		
		//Verify mong đợi Confirm Email displayed(true)
		// Nesu 1 cái hàm chỉ mong đượi để verify element displayed thôi thì kết hợp cả wait+ isDisplayed
		//waitForElementVisible
		//isElementDisplayed,xsl.mxlsjdldjfokrjf
		
	}
	
	@Test
	public void TC_02_Verify_Element_UnDisplay_IN_DOM() {	
		//Nếu mình mong đợi 1 hạm vừa verify displayed/undisplayed thì k được kết hợp wait( waitElementDisplayed) 
		
		//VerifyTrue - mong đợi confirm email displayed
		loginPage.entertoEmailAddressTextbox("minhtrang@gmail.com");
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		
		//VerifyFalse - mong đợi confirm email undisplayed
		loginPage.entertoEmailAddressTextbox("");
		loginPage.sleepInSecond(3);
		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
	}
	@Test
	public void TC_03_Verify_Element_UnDisplay_Not_IN_DOM() {
		
		loginPage.clickCloseIconAtResgisterForm();
		loginPage.sleepInSecond(3);
		//Khi đóng popup thì không còn element ở trong DOM nữa
		//Nên hàm findElement sẽ bị fail vì không tìm thấy element
		//1. Nó sẽ chờ hết timeour của implicit:30s
		//2. Nó sẽ đánh fai test case tại step này
		//3. K chạy các step còn lại nữa
		
		//hàm isDisplayed: bản chất không kiểm tra 1 element không có trong DOM được
		
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
