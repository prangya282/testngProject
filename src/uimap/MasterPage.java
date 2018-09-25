package uimap;

import frameworklibrary.*;
/**
 * @author 609684083
 *
 */
public class MasterPage {


	//For BT

	// *****************************Header Search or TOP Search
	public static final Locator icnSearch = new Locator("xpath","//a[@class='cross icon-search']");

	public static final Locator txtSearch = new Locator("xpath","//div[@class='search-bar expanded']//input[@type='text']");
	
	// Top Header Links
	public static final Locator lnkCommon_TopHeader = new Locator("xpath","//div[@class='main btw-header']//a[contains(text(),'headerlink')]"); //replace headerlink
	
	public static final Locator lnkCommon_SubMenu = new Locator("xpath","//a[contains(text(),'submenulink')]"); //replace Submenulink
	
	public static final Locator lnkContactUs = new Locator("cssSelector", "a.contact");
	
	//Sections or sub-menu display
	public static final Locator secSubMenuForPrdAndSer = new Locator("xpath","//div[@class='submenu first']");
	
	public static final Locator secSubMenuForSalesAndTools = new Locator("xpath","//div[@class='submenu second' and parent::li[@class='megamenu2nd nav-sales-mark']]");
	
	//public static final Locator secSubMenuForHelpAndSupport = new Locator("xpath","//div[@class='submenu third' and parent::li[@class='prod-serv active']]");
	
	//section level 2 disp in submenu links
	public static final Locator secLevel2ForSubmenu = new Locator("xpath","(//div[@class='submenu first']//ul[@class='level2'])[1]");
	
	
	public static final Locator lnkHostedComm = new Locator("xpath","//a[contains(text(),'Hosted Communications')]");
	
	public static final Locator lnkIpVoiceSer = new Locator("xpath","//a[contains(text(),'IP Voice Services (IPVS)')]");
	
	public static final Locator lblSalesTools = new Locator("xpath","//p[contains(text(),'Sales Tools: Helping you sell more')]");
	
	public static final Locator lblHelpAndSupport = new Locator("xpath","//p[contains(text(),'Help & Support')]");
	
	// For page Hosted Communications > IPVS Voice
	
	public static final Locator pageIpVoiceSer = new Locator("xpath","//p[(text()='IP Voice Services')]");
	
	public static final Locator lnkDocCen = new Locator("xpath","//a[@name='document_centre']");
	
	// ************************  Once after Logging in ****************************
	
	public static final Locator lnkProfile_header = new Locator("xpath","//div[@class='main btw-header']//a[@id='username']");

	






}
