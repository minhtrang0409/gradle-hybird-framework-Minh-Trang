package pageObjects.nopCommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	WebDriver driver;
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToGenderMaleRadio() {
		waitForElementClickable(driver, RegisterPageUI.GENDER_MALE_RADIO);
		checkToCheckboxRadio(driver, RegisterPageUI.GENDER_MALE_RADIO);
	}

	@Step("Enter to Fistname textbox with value is {0}")
	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	@Step("Enter to Lastname textbox with value is {0}")
	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastName);
		
	}

	@Step("Enter to Day dropdown with value is {0}")
	public void selectBirthDayDropdown(String day) {
		waitForElementClickable(driver, RegisterPageUI.DAY_DROPDOWN);
		selectItemInDefaultDropdown(driver, RegisterPageUI.DAY_DROPDOWN, day);
	}

	@Step("Enter to Month dropdown with value is {0}")
	public void selectBirthMonthDropdown(String month) {
		waitForElementClickable(driver, RegisterPageUI.MONTH_DROPDOWN);
		selectItemInDefaultDropdown(driver, RegisterPageUI.MONTH_DROPDOWN, month);
		
	}
	
	@Step("Enter to Year dropdown with value is {0}")
	public void selectBirthYearDropdown(String year) {
		waitForElementClickable(driver, RegisterPageUI.YEAR_DROPDOWN);
		selectItemInDefaultDropdown(driver, RegisterPageUI.YEAR_DROPDOWN, year);
		
	}

	@Step("Enter to EmailAddress textbox with value is {0}")
	public void inputToEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
		
	}

	public void inputToCompanyTextbox(String company) {
		waitForElementVisible(driver, RegisterPageUI.COMPANY_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.COMPANY_TEXTBOX, company);
		
	}
	
	@Step("Enter to Password textbox with value is {0}")
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
		
	}
	@Step("Enter to ConfirmPassword textbox with value is {0}")
	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
		
	}

	@Step("Click to register button")
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	@Step("Verify success message is displayed")
	public String getRegisteredSuccessMessage() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public UserHomePageObject clickToLogOutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
//		2. return new HomePageObject(driver);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public UserLoginPageObject clickToLogInLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGIN_LINK);
		clickToElement(driver, RegisterPageUI.LOGIN_LINK);	
		return PageGeneratorManager.getUserLoginPage(driver);
	}



}
