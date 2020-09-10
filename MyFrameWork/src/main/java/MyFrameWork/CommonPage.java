package MyFrameWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CommonPage {

	public WebDriver driver;

	@FindBy(how = How.ID, using = "userNameInput")
	public WebElement username;

	@FindBy(how = How.ID, using = "passwordInput")
	public WebElement password;

	@FindBy(how = How.ID, using = "submitButton")
	public WebElement submitButton;

	@FindBy(how = How.ID, using = "SSNSearch")
	public WebElement SSNSearch;

	@FindBy(how = How.ID, using = "btn-return-search")
	public WebElement buttonsearch;

	@FindBy(how = How.ID, using = "globalaction_action_createReturnBySsn")
	public WebElement createreturn;

	@FindBy(how = How.ID, using = "Select_0")
	public WebElement createreturnselect;

	@FindBy(how = How.ID, using = "PrimaryFirstName")
	public WebElement PrimaryFirstName;

	@FindBy(how = How.ID, using = "PrimaryLastName")
	public WebElement PrimaryLastName;

	@FindBy(how = How.ID, using = "PrimaryMiddleInitial")
	public WebElement PrimaryMiddleInitial;

	@FindBy(how = How.ID, using = "PrimarySuffix")
	public WebElement PrimarySuffix;

	@FindBy(how = How.ID, using = "VerifySsn")
	public WebElement VerifySsn;

	@FindBy(how = How.ID, using = "PrimaryEmail")
	public WebElement PrimaryEmail;

	@FindBy(how = How.ID, using = "PrimaryDateOfBirth")
	public WebElement PrimaryDateOfBirth;

	@FindBy(how = How.ID, using = "PrimaryMobilePhone")
	public WebElement PrimaryMobilePhone;

	//@FindBy(how = How.ID, using = "hlp-ArePrimaryDayAndEveningPhoneSameAsMobile")
	
	@FindBy(how = How.XPATH, using = "(//i[@class='input-element-focus'])[7]")
	public WebElement hlpArePrimaryDayAndEveningPhoneSameAsMobile;

	// @FindBy(how = How.ID, using =
	// "PrimaryReceivePromotionalMessages_input_no")
	@FindBy(how = How.XPATH, using = "(//label[@class='radio'])[2]/i")
	public WebElement PrimaryReceivePromotionalMessages_input_no;

	//@FindBy(how = How.ID, using = "PrefersSpanish_input_no")
	@FindBy(how = How.XPATH, using = "(//label[@class='radio'])[4]/i")
	public WebElement PrefersSpanish_input_no;

	@FindBy(how = How.ID, using = "StreetLine1")
	public WebElement StreetLine1;

	@FindBy(how = How.ID, using = "Zip")
	public WebElement Zip;

	@FindBy(how = How.ID, using = "City")
	public WebElement City;

	@FindBy(how = How.ID, using = "State")
	public WebElement State;

	@FindBy(how = How.ID, using = "globalaction_action_Next")
	public WebElement globalaction_action_Next;

	@FindBy(how = How.ID, using = "globalaction_action_CHECKALL")
	public WebElement globalaction_action_CHECKALL;

	@FindBy(how = How.ID, using = "globalaction_action_Skip")
	public WebElement globalaction_action_Skip;

	@FindBy(how = How.ID, using = "TaxPayerSSN_1")
	public WebElement TaxPayerSSN_1;

	@FindBy(how = How.ID, using = "TaxPayerSSN_2")
	public WebElement TaxPayerSSN_2;

	@FindBy(how = How.ID, using = "TaxPayerSSN_3")
	public WebElement TaxPayerSSN_3;

	@FindBy(how = How.NAME, using = "ContinueOnSuccess")
	public WebElement ContinueOnSuccess;

	//@FindBy(how = How.ID, using = "370_N")
	@FindBy(how = How.XPATH, using = "//input[@id='370_N']/parent::node()")
	public WebElement No;

	@FindBy(how = How.XPATH, using = "//div[@id='interview-nav']/button")
	public WebElement NoNext;

	@FindBy(how = How.XPATH, using = "//input[@id='432_Y']/parent::node()")
	public WebElement Yes432;

	@FindBy(how = How.XPATH, using = "//input[@id='433_N']/parent::node()") 
	public WebElement No433;

	@FindBy(how = How.XPATH, using = "//input[@id='434_N']/parent::node()")
	public WebElement No434;

	@FindBy(how = How.XPATH, using = "//input[@id='435_N']/parent::node()")
	public WebElement No435;

	@FindBy(how = How.XPATH, using = "//input[@id='438_Y']/parent::node()")
	public WebElement No438;

	/*
	 * @FindBy(how = How.LINK_TEXT, using = "Next") public WebElement NoNext;
	 */

	@FindBy(how = How.XPATH, using = "//a[@class='add']")
	public WebElement addform;

	@FindBy(how = How.XPATH, using = "//input[@id='TS_1']/parent::node()")
	public WebElement TS_1;

	@FindBy(how = How.ID, using = "FEIN_1")
	public WebElement FEIN_1;

	@FindBy(how = How.ID, using = "FEIN_2")
	public WebElement FEIN_2;

	@FindBy(how = How.ID, using = "EmployerName")
	public WebElement EmployerName;

	@FindBy(how = How.ID, using = "EmployerAddress")
	public WebElement EmployerAddress;

	@FindBy(how = How.ID, using = "EmployerZip_1")
	public WebElement EmployerZip_1;

	@FindBy(how = How.ID, using = "EmployerCity")
	public WebElement EmployerCity;

	@FindBy(how = How.ID, using = "EmployerState")
	public WebElement EmployerState;

	@FindBy(how = How.ID, using = "Wages")
	public WebElement Wages;
	@FindBy(how = How.ID, using = "WHVerificationWages")
	public WebElement WHVerificationWages;
	@FindBy(how = How.ID, using = "FederalWH")
	public WebElement FederalWH;
	@FindBy(how = How.ID, using = "WHVerificationFedTaxWH")
	public WebElement WHVerificationFedTaxWH;

	@FindBy(how = How.ID, using = "save-button")
	public WebElement savebutton;

	public CommonPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		// this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
