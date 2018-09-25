package uimap;
import frameworklibrary.*;

/**
 * @author 609684083
 *
 */
public class HomePage {

	public static final Locator userName=  new Locator("xpath","//*[@name='userName']");
	public static final Locator passWord=  new Locator("xpath","//*[@name='password']");
	public static final Locator signIn=  new Locator("xpath","//*[@name='login']");

	
	
	
	
	
	
	
	
	
	

	//*********************  HelpUs Pop up  ****************************
	public static final String iFrameHep_Us = "//iframe[@id='edr_l_403438956']";

	public static final Locator iFrameHelpUs=  new Locator("xpath","//iframe[@id='edr_l_403438956']");

	public static final String helpupPopup = "//h1[contains(text(),'Help us improve')]"; // as the normal one was not working

	public static final Locator popupHelpUs = new Locator("xpath",helpupPopup);

	public static final Locator lnkCloseCross_HelpUsPopup = new Locator("xpath","//a[@class='close']");

	public static final Locator btnNoThanks_HelpUspopup = new Locator("xpath",helpupPopup+"//span[contains(text(),'No thanks')]");
	//*********************  HelpUs Pop up  ****************************

	//****************************************  First time user Popup********************************************************************//
	public static final String FTPopup = "//div[contains(@class,'firstTimePopup')]";

	public static final Locator popupFirstTimeUser = new Locator("xpath",FTPopup);

	public static final Locator lnkCloseCross_FirstTimePopup = new Locator("xpath", FTPopup + "//a[@title='Close']");
	
	public static final Locator btnClose_FirstTimePopup = new Locator("xpath",FTPopup +"//a[@class='lbutton right new_proceed_Btn']");
	
	public static final Locator drpdwnHowDoI_FirstTimePopup = new Locator("xpath",FTPopup+"//select[@class='new-select-ui']");

	public static final Locator btnProceed_FirstTimePopup = new Locator("xpath",FTPopup + "//a[contains(text(),'Proceed')]");

	public static final Locator lblPagination_FirstTimePopup  = new Locator("xpath",FTPopup + "//span[@id='first_time_visitor_carousel_count']");

	public static final Locator carouselNext_FTPopup = new Locator("xpath",FTPopup+"//p[@id='first_time_visitor_carousel_next']");

	public static final Locator lblDontShowThisAgain = new Locator("xpath",FTPopup+"//label[child::input[contains(@class,'dont_show_again_chk')]]");

	public static final Locator chkboxDontShowAg_FTPopup = new Locator("xpath",FTPopup+"//input[@class='dont_show_again_chk']");
	
	public static final Locator lbl_HelpLink = new Locator("xpath","//label[contains(text(),'You can always access from the') and following-sibling::label[@class='help_icon1']]");

	public static final Locator btnHide_FTPopup = new Locator("xpath",FTPopup + "//a[@id='hide_button']");
	//****************************************  First time user Popup********************************************************************//

	//  *********************  LoginPage *****************************

	public static final Locator txtUserName = new Locator("cssSelector","input[id=login-username]");

	public static final Locator txtpassword = new Locator("cssSelector","input#login-password");

	public static final Locator lnkRegisterNewAccount = new Locator("xpath","//a[contains(text(),'Register a new account')]");

	public static final Locator btnLogin = new Locator("cssSelector","button.btn-primary.login-submit.BTFont-Bd"); // shortened the xpath as it was not working fine in Firefox

	public static final Locator lnkCantAccessAc = new Locator("xpath","//p[contains(@class,'login-help-link')]//a[contains(text(),'Can')]");

	//************************************ LoginHelp Page or Cant access page*********************************************//
	//Page 1
	public static final Locator lblLoginHelp_loginHelpPage = new Locator("xpath","//div[contains(@class,'article-desc') and child::*[contains(text(),'Login Help')]]");

	public static final Locator lnkResetPwd = new Locator("xpath","//a[contains(text(),'Reset your password')]");

	// Reset password - Step 1 page
	public static final Locator lblForgotPwdStep1Page = new Locator("xpath","//h3[contains(text(),'Forgot Password - Step 1')]");

	public static final Locator txtUserName_Step1Page = new Locator("xpath","//input[@id='username']");

	public static final Locator txtEmail_Step1Page = new Locator("xpath","//input[@id='email']");

	public static final Locator btnContinue_Step1Page = new Locator("cssSelector","input[alt='Continue']");

	//Reset password - Step 2 page
	public static final Locator lblForgotPwdStep2Page = new Locator("xpath","//h3[contains(text(),'Forgot Password - Step 2')]");

	public static final Locator txtSecQue1_Step2Page = new Locator("cssSelector","input[name='value(securityAnswer)']");

	public static final Locator btnContine_Step2Page = btnContinue_Step1Page;

	//******************************************Reactivate Account *******************************

	public static final Locator lnkReActAc = new Locator("xpath","//a[contains(text(),'Re-activate your account')]");

	public static final Locator lblReActAc_ReActAcpage = new Locator("xpath","//h3[contains(text(),'Re-activate your account')]");

	public static final Locator txtUserName_ReActPage = new Locator("cssSelector","input#uname");

	public static final Locator btnContinue_ReActAcPage1 = new Locator("cssSelector","input[value='Continue']");

	//**************************** *************Step 2  ReAct Account *******************************
	public static final Locator lblSelfSer_ReActAcPage2 = new Locator("xpath","//p[contains(text(),'Self Service Account Reactivation - Step 2')]");

	public static final Locator txtSecQue1_ReActAcPage2 = txtSecQue1_Step2Page; // as it is the same 

	public static final Locator txtSecQue2_ReActAcPage2 = new Locator("cssSelector","input[name='value(securityAnswer2)']");

	public static final Locator txtSecQue3_ReActAcPage2 = new Locator("cssSelector","input[name='value(securityAnswer3)']");

	public static final Locator btnContinue_ReActAcPage2 = btnContinue_Step1Page;

	//***********************************************search Results page *************************************8
	public static final Locator secSearch = new Locator("xpath","//div[@class='main-search']");

	public static final Locator chkboxExactMatch = new Locator("xpath","//input[@name='isExactMatch']");

	public static final Locator lblToWidenSrch = new Locator("xpath","//span[contains(text(),'To widen your search please uncheck the exact match.')]");

	public static final Locator secPrdCtg = new Locator("xpath","//li[child::a[@name='searchProdCat']]");

	public static final Locator lnkPrdCtg_FilterOptions =  new Locator("name","searchProdCat");

	public static final Locator lnkFileType_FilterOptions =  new Locator("name","searchByFileType");

	public static final Locator chkboxAll_PrdCtgFilter = new Locator("xpath","//li[child::a[@name='searchProdCat']]//input[@value='All']");

	public static final Locator chkboxAll_FileTypeFilter = new Locator("xpath","//li[child::a[@name='searchByFileType']]//input[@value='All']");

	public static final Locator lnkClear_FileTypeFilter = new Locator("xpath","//li[child::a[@name='searchByFileType']]//span[@id='clr_file_type_by']");

	public static final Locator btnApplyFilters = new Locator("xpath","//span[contains(text(),'Apply filters')]");

	public static final Locator secSavedFilters = new Locator("cssSelector","a#SavedFilters");

	public static final Locator count_SavedFilters = new Locator("xpath","//li[child::a[@id='SavedFilters']]//span[@class='wbg']");

	public static final Locator closeCross_SavedFilter = new Locator("xpath","(//li[child::a[@id='SavedFilters']]//span[@class='wbg'])[*]");

	public static final Locator popupRemoveFilter = new Locator("xpath","//div[@class='rmv-confm' and descendant::div[contains(text(),'Are you sure?')]]");

	public static final Locator btnCfrm_popupRemoveFilter = new Locator("xpath","//div[@class='rmv-confm']//input[@value='Confirm']");

	public static final Locator popupAckCfrm = new Locator("xpath","//div[@class='rmv-confm' and descendant::div[contains(text(),'Filter removed successfully')]]");

	public static final Locator btnClose_PopupAckCfrm = new Locator("xpath","//div[@class='rmv-confm' and descendant::div[contains(text(),'Filter removed successfully')]]//input[@value='Close']");

	public static final Locator chckboxSaveFilter = new Locator("xpath",".//*[@id='save_flter_div']/label");

	public static final Locator popupSaveFilter = new Locator("xpath","//div[@id='save_search_popup' and @style]//span[contains(text(),'Save filter')]");

	public static final Locator txtFilterName = new Locator("xpath","//div[@id='save_search_popup' and @style]//input[@name='serch_save']");

	public static final Locator btnSave_PopupSaveFilter = new Locator("xpath","//div[@id='save_search_popup' and @style]//input[@id='save-search']");

	public static final Locator btnClose_PopupSaveFilter = new Locator("xpath","//div[@id='save_search_popup' and @style]//input[@value='Close']");

	public static final Locator lblSavedFilterName = new Locator("xpath","(//li[child::a[@id='SavedFilters']]//span[child::input[@type='radio']]/following-sibling::span)[count]");//replace count

	public static final Locator secResultsContainer = new Locator("xpath","//div[@class='res_container']");

	public static final Locator count_ResultsContainer = new Locator("xpath","//div[@class='res_container']//li//h2");

	public static final Locator lnkView_result1 = new Locator("xpath","(//div[@class='res_container']//li//a[contains(text(),'View')])[1]");

	public static final Locator lnkTitle_result1 = new Locator("xpath","(//div[@class='res_container']//li//h2//a)[1]"); // will change later.
	//**************************************************search Results page ***************************************

	//*************************************************** Saas Page ***************************************************
	public static final String FTPopup_Saas = "//div[@class='firstTimePopup-2' and contains(@style,'block')]";

	public static final Locator popupFTUser_SaasPage = new Locator("xpath", FTPopup_Saas);

	public static final Locator lnkClose_FTPopup_Saas = new Locator("xpath", FTPopup_Saas + "//div[@class='hideDateTime-2']");
	//*************************************************** Saas Page ***************************************************


	//***********************************  Unused Xpathz*********************************************//

	//public static final Locator txtRegisterEmail =  new Locator("xpath","//form[@id='memberRegister']//input[@name='email']");

	//public static final Locator btnRegister =  new Locator("xpath","(//input[@value='Register'])[2]");

	//public static final Locator txtSignInEmailAddress = new Locator("xpath","//form[@id='memberSignin']//input[@name='user']");

	//public static final Locator txtSignInPassword = new Locator("xpath","//form[@id='memberSignin']//input[@name='pass']");

	//public static final Locator btnSubmit = new Locator("xpath","//form[@id='memberSignin']//input[@type='submit']");

	//public static final Locator lnkMyAccount = new Locator("xpath","//*[text()='My Account']");

	//public static final Locator lnkLogout = new Locator("xpath","//*[text()='Logout']");

	//public static final String lnkCategory1 = "//li[contains(@class,'MegaMenu')]/a[contains(text(), 'Category')]";

	//public static final String lblProductCategory1 = "//div[contains(@id,'MegaMenuOverlay')]//ul/li/a[contains(text(), 'ProductCategory')]";

	//***********************************  Unused Xpathz*********************************************//

} // EOC
