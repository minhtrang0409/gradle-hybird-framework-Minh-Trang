package com.nopcommerce.users;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import commons.VerificationFailures;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.nopCommerce.portal.UserAddressPageObject;
import pageObjects.nopCommerce.portal.UserCustomerInforPageObject;
import pageObjects.nopCommerce.portal.UserHomePageObject;
import pageObjects.nopCommerce.portal.UserLoginPageObject;
import pageObjects.nopCommerce.portal.UserMyproductReviewPageObject;
import pageObjects.nopCommerce.portal.UserRegisterPageObject;
import pageObjects.nopCommerce.portal.UserRewardPointPageObject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

public class Level_14_Log_ReportNG extends BaseTest {
	String projectPath = System.getProperty("user.dir");

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName,appUrl);
		emailAddress = "Johnterry" + getRandomNumber() + "@gmail.com";
		firstName = "Minh";
		lastName = "Trang";
		validPassword = "123456";
	}

	@Test
	public void User_01_Regiter() {
		
		//1- Mở URL ra thì Khởi tạo Homepage
		log.info("Register- Step 01: Open Homepage");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Register- Step 02: Navigate to register page");		
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register- Step 03: Select Gender radio");	
		registerPage.clickToGenderMaleRadio();
		
		log.info("Register- Step 04: Enter to Firstname textbox with value is '"+ firstName + "'"  );	
		registerPage.inputToFirstNameTextbox(firstName);
		
		log.info("Register- Step 05: Enter to Lastname textbox with value is '"+ lastName + "'"  );	
		registerPage.inputToLastNameTextbox(lastName);
		
		log.info("Register- Step 06: Enter to Email textbox with value is '"+ emailAddress + "'");	
		registerPage.inputToEmailAddressTextbox(emailAddress);
		
		log.info("Register- Step 07: Enter to Company textbox with value is HANA company ");	
		registerPage.inputToCompanyTextbox("HANA company");
		
		log.info("Register- Step 08: Enter to Password textbox with value is '"+ validPassword + "'"  );	
		registerPage.inputToPasswordTextbox(validPassword);
		
		log.info("Register- Step 09: Enter to ConfirmPassword textbox with value is '"+ validPassword + "'"  );	
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		
		log.info("Register- Step 10: Click to 'Register' button");	
		registerPage.clickToRegisterButton();
		
		log.info("Register- Step 11: Verify register success message is displayed" );	
		verifyEquals(registerPage.getRegisteredSuccessMessage(), "Your ..registration completed");
		
		//3- Click logout link => Khởi tạo Homepage
		log.info("Register- Step 12: Click to 'Logout' link");	
		homePage = registerPage.clickToLogOutLink();

	}
	
	@Test
	public void User_02_Login() {
		
		log.info("Login- Step 01:Navigate to login page");	
		loginPage = homePage.openLoginPage();
		
		log.info("Login- Step 02: Enter to Email textbox with value is '"+ emailAddress + "'");	
		loginPage.inputToEmailAddressTextbox(emailAddress);
		
		log.info("Login- Step 03: Enter to Password textbox with value is '"+ validPassword + "'"  );	
		loginPage.inputToPasswordTextbox(validPassword);
		
		log.info("Login- Step 04: Click to 'Login' button");	
		homePage =loginPage.clickToLoginButton();
		
		
		log.info("Login- Step 05: My account link displayed");	
		verifyFalse(homePage.isMyAccountLinkDisplay());

		log.info("Login- Step 06: Navigate to 'Myaccount' page");	
		customerInforPage = homePage.openMyAccountPage();
		
		log.info("Login- Step 07: Verify customer infor page");	
		customerInforPage.isGenderMaleRadioSelected();
		verifyEquals(customerInforPage.getFirstNameTextboxAttribute(),"Minh");
		verifyEquals(customerInforPage.getLastNameTextboxAttribute(), "John");
		verifyEquals(customerInforPage.getDayDropDownSelectedItem(),"18");
		verifyEquals(customerInforPage.getMonthDropDownSelectedItem(),"August");
		verifyEquals(customerInforPage.getYearDropDownSelectedItem(),"1986");
		verifyEquals(customerInforPage.getEmailTextboxAttribute(),emailAddress);
		verifyEquals(customerInforPage.getCompanyTextboxAttribute(),"HANA company");
	}
@AfterClass
public void afterClass() {
	driver.quit();
}
private WebDriver driver;
WebDriverWait explicitWait;
private String firstName, lastName, emailAddress, validPassword;

private UserHomePageObject homePage;
private UserLoginPageObject loginPage;
private UserRegisterPageObject registerPage;
private UserCustomerInforPageObject customerInforPage;

}
  
/// Từ bài học Assert trở đi thì khi ứng dụng các hàm verifyTrue.False phải thêm hàm lisntener vào file XML

