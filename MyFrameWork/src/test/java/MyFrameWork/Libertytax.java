package MyFrameWork;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class Libertytax {

	public WebDriver driver;
	CommonPage pageObj;
	CommonLib comLib;
	TestBase base;
	public String datadirectorypath;
	int rowindex = 2;
	WebDriverWait wait;
	String ssn;
	public String address;
	public String zip;
	public String city;
	public String state1;
	public final Logger log = LoggerFactory.getLogger(Libertytax.class);
	JavascriptExecutor js;

	@BeforeTest
	public void fnNavigatetoURL() throws InterruptedException {
		base = new TestBase();
		driver = base.fnNavigatetoURL();
		comLib = new CommonLib(driver);
		wait = new WebDriverWait(driver, 100);
		pageObj = new CommonPage(driver);
		datadirectorypath = System.getProperty("user.dir");
		datadirectorypath = datadirectorypath + "\\src\\main\\java\\TestData.xlsx";
		comLib.fnreadSheet(datadirectorypath, "CommonIntel");
		System.out.println(datadirectorypath);
		 js = (JavascriptExecutor) driver;

	}

	@Title("Verify Login is working correctly")
	@Description("<p>Step 1: Navigate to application URL </br> Step 2:Login with valid Credentials</br>"
			+ "Step 3: Check for navigation DashboardScreen</p>")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 1)
	public void fnLogin() {
		try {
			log.info("********************************************************************************");
			log.info("URL Navigatied and Going to log into application");
			log.info("********************************************************************************");
			String fnname = Thread.currentThread().getStackTrace()[1].getMethodName();
			rowindex = CommonLib.fnFindRow(fnname);
			comLib.fnEnterValueInTextbox(pageObj.username, comLib.fnGetCelValue(rowindex, 1));
			comLib.fnEnterValueInTextbox(pageObj.password, comLib.fnGetCelValue(rowindex, 2));
			pageObj.submitButton.click();
			wait.until(ExpectedConditions.elementToBeClickable(pageObj.SSNSearch));
			Thread.sleep(3000);
			log.info("********************************************************************************");
			log.info("User successfully logged into application");
			Assert.assertTrue(driver.getCurrentUrl().contains("ClientReturns"));
			log.info("User cab able to see the dashboard");
			log.info("********************************************************************************");
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Title("Verify User able to add tax payer information for their SSN")
	@Description("<p>Step 1: Enter the SSN </br> Step 2:Perform Search</br>"
			+ "Step 3: Create new and select office to save value</p>")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void fnCreateLiveReturnForSSN() {
		try {
			String fnname = Thread.currentThread().getStackTrace()[1].getMethodName();
			rowindex = CommonLib.fnFindRow(fnname);

			/******* Search SSN is working fine ********/
			log.info("********************************************************************************");
			WebElement ssnparent = pageObj.SSNSearch;
			js.executeScript("document.getElementById('SSNSearch').focus();");
			js.executeScript("arguments[0].value='" + comLib.fnGetCelValue(rowindex, 3) + "';", ssnparent);
			ssn = comLib.fnGetCelValue(rowindex, 3);
			Thread.sleep(2000);
			 
			wait.until(ExpectedConditions.elementToBeClickable(pageObj.buttonsearch));
			pageObj.buttonsearch.click();
			log.info("There is no record against to this SSN");

			wait.until(ExpectedConditions.elementToBeClickable(pageObj.createreturn));
			pageObj.createreturn.click();
			wait.until(ExpectedConditions.elementToBeClickable(pageObj.createreturnselect));
			pageObj.createreturnselect.click();

			log.info("User has select the office");
			log.info("********************************************************************************");
			wait.until(ExpectedConditions.visibilityOf(pageObj.PrimaryFirstName));
			Assert.assertTrue(pageObj.PrimaryFirstName.isDisplayed());
			
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Title("User able to submit TaxPayer Information")
	@Description("<p>Step 1: Navigate to TaxPayer Information </br> Step 2:Enter Name information</br>"
			+ "Step 3: Enter Phone number and custom preference</p>")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void fnCreateTaxPayerInformation() {
		try {
			String fnname = Thread.currentThread().getStackTrace()[1].getMethodName();
			rowindex = CommonLib.fnFindRow(fnname);
			log.info("********************************************************************************");
			comLib.fnEnterValueInTextbox(pageObj.PrimaryFirstName, comLib.fnGetCelValue(rowindex, 4));
			comLib.fnEnterValueInTextbox(pageObj.PrimaryLastName, comLib.fnGetCelValue(rowindex, 5));
			comLib.fnEnterValueInTextbox(pageObj.PrimaryMiddleInitial, comLib.fnGetCelValue(rowindex, 6));
			Select PrimarySuffix = new Select(pageObj.PrimarySuffix);
			PrimarySuffix.selectByVisibleText(comLib.fnGetCelValue(rowindex, 7));

			js.executeScript("document.getElementById('VerifySsn').focus();");
			js.executeScript("arguments[0].value='" + ssn + "';", pageObj.VerifySsn);

			js.executeScript("document.getElementById('PrimaryDateOfBirth').focus();");
			js.executeScript("arguments[0].value='" + comLib.fnGetCelValue(rowindex, 9) + "';", pageObj.PrimaryDateOfBirth);

			wait.until(ExpectedConditions.elementToBeClickable(pageObj.PrimaryMobilePhone));

			js.executeScript("document.getElementById('PrimaryMobilePhone').focus();");
			js.executeScript("arguments[0].value='" + comLib.fnGetCelValue(rowindex, 10) + "';", pageObj.PrimaryMobilePhone);
			
			JavascriptExecutor jse2 = (JavascriptExecutor) driver;
			jse2.executeScript("document.getElementById('ArePrimaryDayAndEveningPhoneSameAsMobile').focus();");
			
			pageObj.hlpArePrimaryDayAndEveningPhoneSameAsMobile.click();
			jse2.executeScript("document.getElementById('PrimaryReceivePromotionalMessages_input_no').focus();");
			pageObj.PrimaryReceivePromotionalMessages_input_no.click();
			
			jse2.executeScript("document.getElementById('PrefersSpanish_input_no').focus();");
			pageObj.PrefersSpanish_input_no.click();

			log.info("User has entered Taxpayer Information");
			log.info("********************************************************************************");

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Title("User able to submit Address Information")
	@Description("<p>Step 1: Navigate to TaxPayer Information </br> Step 2:Enter Name information</br>"
			+ "Step 3: Enter Phone number and custom preference</p>")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void fnCreateTaxPayerAddress() {
		try {
			String fnname = Thread.currentThread().getStackTrace()[1].getMethodName();
			rowindex = CommonLib.fnFindRow(fnname);
			address = comLib.fnGetCelValue(rowindex, 11);
			comLib.fnEnterValueInTextbox(pageObj.StreetLine1, address);
			
			zip = comLib.fnGetCelValue(rowindex, 12);
			js.executeScript("document.getElementById('Zip').focus();");
			js.executeScript("arguments[0].value='" + zip + "';", pageObj.Zip);
			js.executeScript("document.getElementById('City').focus();");

			log.info("********************************************************************************");
			log.info("User has entered Address Information");
			
			WebElement element = pageObj.globalaction_action_Next;
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			
			pageObj.globalaction_action_Next.click();

			wait.until(ExpectedConditions.visibilityOf(pageObj.globalaction_action_CHECKALL));
			Assert.assertTrue(pageObj.globalaction_action_CHECKALL.isDisplayed());

			log.info("********************************************************************************");
		}

		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Title("User able to select all the disclosure")
	@Description("<p>Step 1: Click on Select All </br> Step 2:Verify all the checbox are selected</br>"
			+ "Step 3: Click on Add Form</p>")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 5)
	public void fnSubmitDisclosure() {
		try {

			log.info("********************************************************************************");
			pageObj.globalaction_action_CHECKALL.click();
			wait.until(ExpectedConditions.elementToBeClickable(pageObj.globalaction_action_Skip));

			WebElement element = pageObj.globalaction_action_Skip;
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			
			
			pageObj.globalaction_action_Skip.click();
			wait.until(ExpectedConditions.elementToBeClickable(pageObj.ContinueOnSuccess));
			
			Assert.assertTrue(pageObj.ContinueOnSuccess.isDisplayed());

			log.info("User has submitted their disclosure");

			log.info("********************************************************************************");

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Title("User able to submit their income information")
	@Description("<p>Step 1: Select Dependent option </br> Step 2:Select Income option</br>"
			+ "Step 3: Click on Skip</p>")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 6)
	public void fnSubmitIncomeOption() {
		try {
			log.info("********************************************************************************");
			pageObj.ContinueOnSuccess.click();
			Thread.sleep(3000);
			
			pageObj.No.click();
			pageObj.NoNext.click();
			wait.until(ExpectedConditions.elementToBeClickable(pageObj.Yes432));

			pageObj.Yes432.click();
			pageObj.No433.click();
			pageObj.No434.click();
			pageObj.No435.click();
			pageObj.No438.click();

			pageObj.NoNext.click();
			wait.until(ExpectedConditions.elementToBeClickable(pageObj.addform));
			pageObj.addform.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(pageObj.TS_1));
			Assert.assertTrue(pageObj.TS_1.isDisplayed());

			log.info("Form is loaded successfully");
			log.info("********************************************************************************");

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Title("User able to submit the Form")
	@Description("<p>Step 1: Enter FEIN number</br> Step 2:Enter Emploer Address</br>"
			+ "Step 3: Select the Wages and Click on save</p>")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 7)
	public void fnFormSave() {
		try {
			String fnname = Thread.currentThread().getStackTrace()[1].getMethodName();
			rowindex = CommonLib.fnFindRow(fnname);
			log.info("********************************************************************************");
			pageObj.TS_1.click();
			comLib.fnEnterValueInTextbox(pageObj.FEIN_1, comLib.fnGetCelValue(rowindex, 15));
			comLib.fnEnterValueInTextbox(pageObj.FEIN_2, comLib.fnGetCelValue(rowindex, 16));
			log.info("User has entered FEIN and Employer Name");
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('EmployerCity').focus();");
			Thread.sleep(1000);
			log.info("User has entered Employee Address");
			comLib.fnEnterValueInTextbox(pageObj.Wages, comLib.fnGetCelValue(rowindex, 18));
			comLib.fnEnterValueInTextbox(pageObj.WHVerificationWages, comLib.fnGetCelValue(rowindex, 19));
			comLib.fnEnterValueInTextbox(pageObj.FederalWH, comLib.fnGetCelValue(rowindex, 20));
			comLib.fnEnterValueInTextbox(pageObj.WHVerificationFedTaxWH, comLib.fnGetCelValue(rowindex, 21));
			log.info("User has entered Wages details");
			pageObj.savebutton.click();
			wait.until(ExpectedConditions.elementToBeClickable(pageObj.addform));
			Assert.assertTrue(pageObj.addform.isDisplayed());
			log.info("********************************************************************************");

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@AfterTest
	public void fnClose() {
		log.info("********************************************************************************");
		driver.quit();
		log.info("Browser has been closed subbcessfully after executing all the testcases");
		log.info("********************************************************************************");

	}

}
