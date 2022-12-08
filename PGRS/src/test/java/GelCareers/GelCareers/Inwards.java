package GelCareers.GelCareers;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Goaonline.Domicile;
import com.Goaonline.Services;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.required.Excel_Import_data_revised;
import com.required.Excel_Import_xpath;
import com.required.Excel_write;

import com.required.Testing_variables;
import com.required.Validatiion_matrix;
import com.utilities.Screenshot;
import com.utilities.difference;
import com.utilities.sparklogs;

public class Inwards {
	static Logger log = Logger.getLogger(Inwards.class);
	public WebDriver driver;
	private String new_xpath;
	private Excel_write e;
	public DateTimeFormatter dtf;
	public String Start_time;
	public String end_date;
	public String Start_class;
	public String end_class;
	public String Aknow;
	public String Testcase_number;
	//public Upload_Section upload_files;
	@SuppressWarnings("rawtypes")
	public List upload_file;
	SoftAssert softAssert;
	public String Description;
	public ExtentTest test;
	
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("target/new_report/Spark.html");
	@BeforeTest
	  public void beforeTest() {
		Aknow= null;
		  extent.attachReporter(spark);
	  }
	 @AfterTest
	  public void afterTest() {
		  extent.flush();
	  }
	@BeforeClass
	void beforeclass() throws Exception {
		
		PropertyConfigurator.configure("src\\test\\java\\com\\utilities\\log.properties");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		//System.setProperty("webdriver.chrome.driver", "E:\\document\\selenium components\\chrome_1\\chromedriver.exe");
		e = new Excel_write();
		e.write_header();
		LocalDateTime now = LocalDateTime.now();
		Start_class= dtf.format(now);
		//Goa_Services n =new Goa_Services();
		//driver =n.goto_login();
	}
	/*@BeforeTest
	void before_test() {
		System.out.println("Before Test");
	}
	@AfterTest
	void After_test() {
		System.out.println("After Test");
	}*/
	
	@AfterClass
	void Afterclass() {
		//Close driver
		if (driver!=null) {
		//driver.quit();
		}
		LocalDateTime now = LocalDateTime.now();
		end_class= dtf.format(now);
		System.out.println(difference.findDifference(Start_class, end_class));
		e.total_time(difference.findDifference(Start_class, end_class));
		e.write_file();
	}
	
	
	@BeforeMethod
	void BeforeMethod() {
		LocalDateTime now = LocalDateTime.now();
		Start_time= dtf.format(now);
		
	}
	@AfterMethod
	void AfterMethod(Method m,ITestResult i) {
		System.out.println(driver);
		//System.out.println("sdsf"+i.getSkipCausedBy());
		if (i.isSuccess()){
			test.pass("Testcase Passed");
		}
		else {
			test.fail("Testcase Failed");
			//new sparklogs(test).fail("Testcase Failed ",new Screenshot().take_screenshoot(driver));
			/*if (driver!=null) {
			log.info("Browser Closed ");
			driver.quit();
		}*/
		}
		if (i.getParameters()!=null) {
			
			if (i.getParameters().length!=0) {
				
				e.write_parameter(i);
				
			}
			else {
				
			}
			
			if (i.getThrowable()!=null) {
				test.fail(i.getThrowable().getMessage());
				
				new sparklogs(test).fail("Failed", new Screenshot().take_screenshoot(driver));
				log.error(i.getThrowable().getMessage());
				Object test_scenario =(List) i.getParameters()[0];
				List header = (List) ((List) test_scenario).get(0);
				//System.out.println("not null");
				
			}
			else
			{
				System.out.println("null");
			}
		}		
			/*if (driver!=null) {
				log.info("Browser Closed ");
				//driver.quit();
			}*/
		LocalDateTime now = LocalDateTime.now();
		end_date= dtf.format(now);
		difference datee = new difference();
		e.write_time(difference.findDifference(Start_time, end_date));
		e.write_file();
		//i.
		/*if (i.isSuccess()){
			//test.pass("Testcase Passed");
			if (driver!=null) {
				//test
				log.info("Browser Closed ");
				driver.quit();
				
		}
		else {
			System.out.println(driver);
		}
		}	
		else {
			if (driver!=null) {
				//test
				log.info("Browser Closed ");
				driver.quit();
		}
			else {
				System.out.println(driver);
			}
		}
		*/
		//System.out.println();
	}
	
	
	
	
	@DataProvider(name = "Extract_testcases")
    public Object[][] getData() throws Exception{
		Excel_Import_data_revised e = new Excel_Import_data_revised();
		//System.out::println
	//	System.out.println(e.testData(0,5)+ "getting data");
		return e.testData(0);
	}
	
	
	
	
	
	
	@Test(dataProvider ="Extract_testcases",priority =1)
	void test1(List<Object> data) throws Exception {
		Object testcase = (List)data.get(0);
		System.out.println("Testcase"+(String)((List<Object>) testcase).get(1));
		test = extent.createTest("Testcase "+(String)((List<Object>) testcase).get(1)+" "+(String)((List<Object>) testcase).get(3));
		//		System.out.println("Extracct cases"+(String)((List<Object>) testcase).get(4));
		softAssert = new SoftAssert();
		Testing_variables t = new Testing_variables(softAssert,log,test);
		//Grid_Goaservices gs1 = new Grid_Goaservices(softAssert,log);
		Excel_Import_xpath x= new Excel_Import_xpath();
		x.xpath_Data();
		for (int i=0;i<data.size();i++) {
			//System.out.println(data.get(i));
			Object c= (List)data.get(i);
			System.out.println(c.toString());
			log.info("Steps Executions "+c.toString()+"Step Number " +i);
			//System.out.println(((List<Object>) c).get(0));
			//SObject dteps
			Object d=((List<Object>) c).get(0);
			
			//xpath from excel
			String xpath_D=(String)((List<Object>) c).get(1);	
			//xpath from excel
			
			String Execution=(String)((List<Object>) c).get(4);
			
			
			Description =(String)((List<Object>) c).get(3);
			//test = extent.createTest(Description);
			//Data from Excel
			String Excel_Data=(String)((List<Object>) c).get(2);
			
			//Expected data drom Excel
			String Excel_Expected_Data=(String)((List<Object>) c).get(3);
			String steps= (String)d;
			
			switch (steps) {
			case "click":
				//System.out.println("click");
				test.info("click on "+xpath_D);
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before Click "+xpath_D+" "+new_xpath);
				t.click(driver, new_xpath);
				log.info("Item Clicked Successfully "+xpath_D+" "+new_xpath);
				//Reporter.log("clicked successfully"+x.xpath_name, 2);
				//System.out.println(new_xpath);
				Thread.sleep(1000);
				break;
				
			case "select_dropdown":
				//System.out.println("select");
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before Select "+xpath_D+" "+new_xpath);
				t.select(driver, new_xpath, Excel_Data);
				log.info("Item Selected Successfully "+xpath_D+" "+Excel_Data+ " "+new_xpath);
				/*Screenhoot s= new Screenhoot();
				s.take_screenshoot(driver,Testcase_number+" "+xpath_D +" "+Excel_Data);
				Thread.sleep(1000);*/
				break;
				
			/*case "check_Validation":
				//System.out.println("check_Validation");
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				//System.out.println(new_xpath);
				log.info("Before Validation check "+Excel_Expected_Data+" "+new_xpath);
				t.check_Validation(driver, new_xpath);
				log.info("Validation checked "+xpath_D+" "+Excel_Expected_Data+ " "+new_xpath);
				break;*/
			case "check Presence of Validation":
				test.info("Check for presence of validation "+xpath_D);
				//System.out.println("check_Validation");
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				//System.out.println(new_xpath);
				log.info("Before Validation check "+Excel_Expected_Data+" "+new_xpath);
				t.check_Validation_presence(driver, new_xpath);
				log.info("Validation checked "+xpath_D+" "+Excel_Expected_Data+ " "+new_xpath);
				break;
				
			/*case "check_Validation":
				test.info("Check for presence of validation "+xpath_D);
				//System.out.println("check_Validation");
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				//System.out.println(new_xpath);
				log.info("Before Validation check "+Excel_Expected_Data+" "+new_xpath);
				t.check_Validation(driver, new_xpath);
				log.info("Validation checked "+xpath_D+" "+Excel_Expected_Data+ " "+new_xpath);
				break;*/
				//check_Validation_text
			
			case "check_Validation_text":
				//System.out.println("check_Validation");
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				//System.out.println(new_xpath);
				log.info("Before Validation check "+Excel_Expected_Data+" "+new_xpath);
				t.check_Validation_text(driver, new_xpath,Excel_Expected_Data);
				log.info("Validation checked "+xpath_D+" "+Excel_Expected_Data+ " "+new_xpath);
				break;
				
			case "check_absence_Validation":
				//System.out.println("check_Validation");
				test.info("Check for absence of validation of "+ xpath_D);
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before Validation check "+xpath_D+" "+new_xpath);
				//System.out.println(new_xpath);
				t.check_absence_Validation(driver, new_xpath);
				log.info("Validation absent "+xpath_D+" "+ " "+new_xpath);
				break;
				
			case "Clear_text_box_data":
				//System.out.println("clear data");
				log.info("Before Clearing "+xpath_D+" "+new_xpath);
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				//System.out.println(new_xpath);
				t.Clear_textdata(driver, new_xpath);
				log.info("Cleared successfully "+xpath_D+" "+Excel_Expected_Data+ " "+new_xpath);
				break;
				
			case "wait":
				//System.out.println("wait");
				//System.out.println(Excel_Data);
				log.info("Before waiting "+Excel_Data+" ");
				int waittime=Integer.parseInt(Excel_Data);  
				
				//System.out.println(waittime);
				try {
					
					Thread.sleep(waittime);
				}
				catch (Exception e) {
					
				}
				break;
				
			case "write":
				test.info("writing <span style=\"color:blue;\">"+Excel_Data+ "</span> to "+xpath_D);
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before Writing "+xpath_D+" "+new_xpath);
				t.send_data(driver, new_xpath, Excel_Data);
				log.info("Written Succesfully "+xpath_D+" "+Excel_Data+" "+new_xpath);
				//Thread.sleep(3000);
				break;
			
			case "check_textbox_data":
				test.info("check for "+Excel_Expected_Data+" in field "+xpath_D);
				//System.out.println(t.getdata(driver, new_xpath));
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before checking textbox "+xpath_D+" "+new_xpath);
				t.check_textbox_data(driver, new_xpath, Excel_Expected_Data);
				log.info("Checked textbox/date Successfully "+xpath_D+" "+Excel_Expected_Data+" "+new_xpath);
				break;
			case "Go to Domicile":
				Services s= new Services();
				driver=s.domicile();
				break;
			
			case "check_textbox_empty":
				test.info("Check "+xpath_D+" Text box Empty" );
				//System.out.println(t.getdata(driver, new_xpath));
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before checking empty "+xpath_D+" "+new_xpath);
				//System.out.println(new_xpath+"new ");
				t.check_textbox_empty(driver, new_xpath);
				log.info("Checked Empty Successfully "+xpath_D+" "+new_xpath);
				break;
				
			case "check_data":
				//System.out.println(t.getdata(driver, new_xpath));
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				//System.out.println(new_xpath+"new ");
				test.info("check "+Excel_Expected_Data+" in field "+xpath_D);
				t.check_data(driver, new_xpath, Excel_Expected_Data);
				log.info("Checked Data Successfully "+xpath_D+" "+Excel_Expected_Data+" "+new_xpath);
				break;
			case "check_data_contains":
				//System.out.println(t.getdata(driver, new_xpath));
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				//System.out.println(new_xpath+"new ");
				t.check_data_contains(driver, new_xpath, Excel_Expected_Data);
				log.info("Checked contains Data Successfully "+xpath_D+" "+Excel_Expected_Data+" "+new_xpath);
				break;
				
			case "Test_scenario_number":
				log.info("Test_scenario_number "+xpath_D);
				//String Execution=(String)((List<Object>) c).get(3);
				Testcase_number=xpath_D;
				log.info("Test_scenario_desciption "+Description);
				break;
				
			case "Check_for_presence_of_alert":
				log.info("Checking Alert ");
				t.Check_for_presence_of_alert(driver);	
				log.info("Alert present ");
				
				
				break;
				
			case "Check_for_presence_of_alert_check_data":
				log.info("Checking Alert "+Excel_Expected_Data);
				t.Check_for_presence_of_alert_check_data(driver,Excel_Expected_Data);
				log.info("Alert present data "+Excel_Expected_Data);
				break;
				
			case "Check_for_absence_of_alert":	
				test.info("Check for Absence of alert");
				t.Check_for_absence_of_alert(driver,Excel_Expected_Data);
				log.info("Alert absent "+xpath_D+" "+new_xpath);
				break;
				
			case "Check_for_presence_of_Element":
				
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				test.info("Check for presence of element "+xpath_D+"path "+new_xpath);
				log.info("Check for Element present "+xpath_D+" "+new_xpath);
				t.Check_for_presence_of_Element(driver, new_xpath);
				log.info("Element present "+xpath_D+" "+new_xpath);
				break;
				
			case "Check_for_absence_of_Element":
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				test.info("Check for absence of element "+xpath_D+"path "+new_xpath);
				log.info("Check for Element absent "+xpath_D+" "+new_xpath);
				
				t.Check_for_absence_of_Element(driver, new_xpath);
				log.info("Element absent "+xpath_D+" "+new_xpath);
				break;
			
			case "Verify_for_absence_of_Element":
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				test.info("Check for absence of element "+xpath_D+"path "+new_xpath);
				log.info("Check for Element absent "+xpath_D+" "+new_xpath);
				
				t.Verify_for_absence_of_Element(driver, new_xpath);
				log.info("Element absent "+xpath_D+" "+new_xpath);
				break;
			case "check_selected_data":
				test.info("check dropdown options selected for "+xpath_D +" is "+Excel_Expected_Data);
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("selected option cheking "+xpath_D+" "+Excel_Expected_Data+" "+new_xpath);
				t.check_selected_data(driver, new_xpath,Excel_Expected_Data );
				log.info("selected option checked successfully "+xpath_D+" "+Excel_Expected_Data+" "+new_xpath);
				break;
				
			case "check_options_in_select":
				
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				test.info("check for options in select "+xpath_D+" "+new_xpath);
				log.info("options Before validation "+xpath_D+" "+Excel_Expected_Data+" "+new_xpath);
				t.check_options_in_select(driver, new_xpath,Excel_Expected_Data );
				log.info("options validated successfully "+xpath_D+" "+Excel_Expected_Data+" "+new_xpath);
				break;
			
			case "check_radio_selected":
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				
				t.check_radio_selected(driver, new_xpath);
				log.info("Radio/checkbox checked/selected successfully checked "+xpath_D+" "+new_xpath);
				break;
				
			case "check_radio_unselected":
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				
				t.check_radio_unselected(driver, new_xpath);
				log.info("Radio/checkbox unchecked successfully checked "+xpath_D+" "+new_xpath);
				break;
				
			case "check_for_element_disabled":
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before Checking disabled "+xpath_D+" "+new_xpath);
				t.check_for_element_disabled(driver,new_xpath);
				log.info("Date Selected Succesfully "+xpath_D+" "+new_xpath);
				break;
				
			case "date":
				//System.out.println("date");
				
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before Entering date "+xpath_D+" "+new_xpath);
				t.date(driver, new_xpath, Excel_Data);
				log.info("After Entering date "+xpath_D+" "+new_xpath);
				break;
			case "time":
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before time "+xpath_D+" "+new_xpath+" "+Excel_Data);
				t.select_time(driver, Excel_Data,new_xpath );
				log.info("After time "+xpath_D+" "+new_xpath+" "+Excel_Data);
				break;
			case "check_validations":
				//test.info("")
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);	
				log.info("Before checking validations "+xpath_D+" "+new_xpath);
				t.check_validations(driver, new_xpath,Excel_Expected_Data);
				log.info("Validation chcked succesfully "+xpath_D+" "+new_xpath);
				break;
				
			case "upload":
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before Uploading "+xpath_D+" "+new_xpath);
				t.upload(driver, new_xpath, Excel_Data);
				log.info("uploading "+xpath_D+" "+new_xpath);
				break;
				
			case "close_browser":
				test.info("close browser");
				driver.quit();
				test.pass("Browser closed");
				break;
			
			case "Check_Acknowledgment":
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before getting Acknowledgment "+xpath_D+" "+new_xpath);
				
				String Aknow1 = t.getdata(driver, new_xpath);
				Aknow =Aknow1;
				//sysoutAknow1.substring(beginIndex, endIndex)
				System.out.println("Akclo" +Aknow1);
				//log.info("After getting Acknowledgment "+xpath_D+" "+Aknow1+" "+Aknow+" "+ new_xpath);
				//Assert.assertEquals(Aknow1, Aknow);
				//log.info("After getting Acknowledgment "+xpath_D+" "+Aknow1+" "+ new_xpath);
				////navigation_to_Dashboard new_das= new navigation_to_Dashboard();
				//driver=new_das.goto_login();
				break;
			
			case "get_Acknowledgment":
				test.info("Get Ackowledment number");
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before getting Acknowledgment "+xpath_D+" "+new_xpath);
				
				String Aknow2 = t.getdata(driver, new_xpath);
				test.info("Ackowledment number is "+Aknow2);
				Aknow =Aknow2;
				
				//sysoutAknow1.substring(beginIndex, endIndex)
				System.out.println("Akclo" +Aknow2);
				//log.info("After getting Acknowledgment "+xpath_D+" "+Aknow1+" "+Aknow+" "+ new_xpath);
				//Assert.assertEquals(Aknow1, Aknow);
				//log.info("After getting Acknowledgment "+xpath_D+" "+Aknow1+" "+ new_xpath);
				////navigation_to_Dashboard new_das= new navigation_to_Dashboard();
				//driver=new_das.goto_login();
				break;
			
			case "put_Acknowledgment":
				
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				test.info("inserting in"+ xpath_D +" textbox " +new_xpath);
				log.info("Before getting Acknowledgment "+xpath_D+" "+new_xpath);
				System.out.println(Aknow);
				Domicile da = new Domicile();
				da.PutAcknowledgement(Aknow, t, test, driver, new_xpath);
				//sysoutAknow1.substring(beginIndex, endIndex)
				//System.out.println("Akclo" +Aknow2);
				//log.info("After getting Acknowledgment "+xpath_D+" "+Aknow1+" "+Aknow+" "+ new_xpath);
				//Assert.assertEquals(Aknow1, Aknow);
				//log.info("After getting Acknowledgment "+xpath_D+" "+Aknow1+" "+ new_xpath);
				////navigation_to_Dashboard new_das= new navigation_to_Dashboard();
				//driver=new_das.goto_login();
				break;
			
			case "Page load":
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				for (int l=0;l<5;l++) {
				
				//do {
					try {
						
						t.waituntil_presence(driver, new_xpath, Excel_Data);
						break;
					}
					catch (Exception e) {
						// TODO: handle exception
						driver.navigate().refresh();
						continue;
					}
				//}while(true);
				}
				break;
			case "go to compliance":
				test.info("Navigating to Goaonline for compliance");
				new Services().compliance(t, driver, Excel_Data);
				break;
				
			case "check Attribute Data":
				log.info("Check Attribute text");
				test.info("Check for text in attribute");
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				t.check_attribute_data(driver, new_xpath, Excel_Data, Excel_Expected_Data);
				log.info("checked attribute");
				break;
				
			case "Verify Docs":
				test.info("Verify docs");
				log.info("Docs");
				new Services().Verify_Document(t, driver, Excel_Data);
				break;
			case "goto":
				log.info("Before Navigating "+Excel_Data);
				t.goto_url(driver, Excel_Data);
				log.info("After Navigating ");
				break;
						
			case "Open":
				log.info("Before opening");
				driver=t.Open();
				System.out.println(driver);
				//test.pass(MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\DELL\\Documents\\files_to_upload\\1.jpg").build());
				//test.addScreenCaptureFromPath("C:\\Users\\DELL\\Documents\\files_to_upload\\1.jpg");
				log.info("After opening");
				break;
				
			case "wait until visibility of element":
				test.info("Checking for visibility of "+xpath_D);
				log.info("Before visibility " + Excel_Data);
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before visibility "+new_xpath+ Excel_Data);
				t.waituntil_visibility(driver, new_xpath, Excel_Data);;
				log.info("After visibility "+new_xpath + Excel_Data);
				test.pass("Element visible "+xpath_D+" "+new_xpath);
				break;
			
			case "wait until presence of element":
				test.info("Checking for presence of "+xpath_D);
				log.info("Before presence " + Excel_Data);
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				log.info("Before presence "+new_xpath+ Excel_Data);
				t.waituntil_presence(driver, new_xpath, Excel_Data);;
				log.info("After presence "+new_xpath + Excel_Data);
				test.pass("Element present "+xpath_D+" "+new_xpath);
				break;	
			
			case "refresh":
				log.info("Before Refresh");
				driver.navigate().refresh();
				log.info("After Refresh");
				break;
			case "check_dropdown_reset":
				log.info("Before dropdown reset");
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);
				t.check_dropdown_reset(driver, new_xpath);
				log.info("After dropdown reset checking");
				break;
			
			case "navigate":
				//System.out.println("navigate"+Excel_Data);
				test.info("Navigate to "+Excel_Data);
				log.info("Before Navigating "+Excel_Data);
				this.driver.navigate().to(Excel_Data);
				log.info("After Navigating ");
				test.pass("Navigated to "+Excel_Data);
				
				break;
				
			case "check_any_validation":
				log.info("Before checking validations "+xpath_D+" "+new_xpath);
				t.check_any_validation(driver, new_xpath);
				log.info("No such Validation appears"+xpath_D+" "+new_xpath);
				break;
				
			case "check_Validation_ignore_case":
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);	
				log.info("Before checking validations "+xpath_D+" "+new_xpath);
				t.check_Validation_ignore_case(driver, new_xpath,Excel_Expected_Data);
				log.info("Validation appears"+xpath_D+" "+new_xpath);
				break;
			
			case "check validation rejection":
				test.info("Check for Rejection caracters in "+xpath_D);
				new_xpath=t.calculate_xpath(xpath_D, x.xpath_name, x.range_xpath);	
				log.info("Before checking validations "+xpath_D+" "+new_xpath);
				new Validatiion_matrix().validation_rejection_matrix(Excel_Data, new_xpath, new_xpath, t, driver, test);
			//	t.check_Validation_ignore_case(driver, new_xpath,Excel_Expected_Data);
				//log.info("Validation appears"+xpath_D+" "+new_xpath);
				break;
				
			case "Assert_all":
				log.info("Before Assertion");
				softAssert.assertAll();
				log.info("After Assertion");
				break;
				
			default:
				break;
			}	
		}
		}
}
