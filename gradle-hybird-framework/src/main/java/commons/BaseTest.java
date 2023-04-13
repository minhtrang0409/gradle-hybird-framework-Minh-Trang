package commons;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	protected final Log log;
	String projectPath = System.getProperty("user.dir");
	
	protected BaseTest() {
		 log = LogFactory.getLog(getClass());
	}

	@BeforeSuite
	public void initBeforSuite() {
		System.out.println("---------- START delete file in folder ----------");
		deleteAllFileInFolder();
		System.out.println("---------- END delete file in folder ----------");
	}
	
	public WebDriver getDriverInstance() {
		return this.driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserName browser = BrowserName.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX: 
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case H_FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			//FirefoxOption chỉ có với selenium 3. trở lên
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-headless");
			options.addArguments("windown-size=1920x1080");
			break;
		case CHROME :
			driver = WebDriverManager.chromedriver().create();
			break;
		case H_CHROME:
			driver = WebDriverManager.chromedriver().create();
			ChromeOptions options2 = new ChromeOptions();
			options2.addArguments("-headless");
			options2.addArguments("windown-size=1920x1080");
			driver = new ChromeDriver(options2);
			break;
		case EDGE :	
			driver = WebDriverManager.edgedriver().create();
			break;
		case OPERA :	
			driver = WebDriverManager.operadriver().create();
			break;
		default:
			throw new RuntimeException("Please input with correct browser name.");
		}
		
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().maximize();
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}
	
	public WebDriver getBrowserDriver(String browserName, String appUrl) {
		BrowserName browser = BrowserName.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX: 
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case CHROME :
			driver = WebDriverManager.chromedriver().create();
			break;
		case EDGE :	
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			throw new RuntimeException("Please input with correct browser name.");
		}
		
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(appUrl);
		return driver;
	}
	
	
	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			System.out.println(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public void deleteAllFileInFolder() {
		try {
			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	
	}
}
