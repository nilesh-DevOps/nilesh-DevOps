package com.required;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.utilities.Screenshot;
import com.utilities.sparklogs;

import io.github.bonigarcia.wdm.WebDriverManager;

//import com.test.Screenhoot;

public class Testing_variables {
	SoftAssert softAssert;
	static Logger log;
	sparklogs s;
	String validation;
	public Testing_variables(SoftAssert softAssert) {
		// TODO Auto-generated constructor stub
		this.softAssert=softAssert;
		s= new sparklogs(null);
	}
	public Testing_variables(SoftAssert softAssert,Logger log) {
		// TODO Auto-generated constructor stub
		s= new sparklogs(null);
		this.softAssert=softAssert;
		this.log = log;
		validation ="/parent::div/div";
	}
	public Testing_variables(SoftAssert softAssert,Logger log,ExtentTest test) {
		// TODO Auto-generated constructor stub
		s= new sparklogs(test);
		this.softAssert=softAssert;
		this.log = log;
		validation ="/parent::div/div";
	}
	public Testing_variables() {
		s= new sparklogs(null);
		// TODO Auto-generated constructor stub
	}
	
	//WebDriver driver;
	public void select(WebDriver driver, String xpath,String data) {
		s.info("selecting "+xpath+" with option "+data);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(xpath))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		//wait.until(ExpectedConditions.elementToBeSelected(By.xpath(xpath)));
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
		try {
			//wait.until(ExpectedConditions.)
			/*click(driver,xpath);
			Thread.sleep(1000);
			click(driver,xpath);*/
		new Select(driver.findElement(By.xpath(xpath))).selectByVisibleText(data);
		s.info("selected "+xpath+" with option "+data);
		}
		catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			click(driver,xpath);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			click(driver,xpath);
			try {
				new Select(driver.findElement(By.xpath(xpath))).selectByVisibleText(data);
				s.info("selected "+xpath+" with option "+data);
				//s.info("selected "+xpath+" with option "+data);
				
			}
			catch (Exception e2) {
				// TODO: handle exception
				s.fail("failed to select");
				s.fail(e);
				log.info(e.getStackTrace());
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	public void click(WebDriver driver, String xpath){
		try {
			s.info("click on path "+xpath);
			
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
		}
		catch (UnhandledAlertException e) {
			// TODO: handle exception
			s.warning("Alert appeared", new Screenshot().take_screenshoot(driver));
			System.out.println("onclick unexpected alert appeared");
		}
		catch (Exception e1) {
			// TODO: handle exception
			s.warning("Alert appeared", new Screenshot().take_screenshoot(driver));
			System.out.println("onclick unexpected alert appeared");
		}
		}
	public void send_data(WebDriver driver, String xpath,String data) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.pollingEvery(Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(xpath))));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
		//wait.until(ExpectedConditions.));
		driver.findElement(By.xpath(xpath)).sendKeys(data);
		s.pass("written data " +data +" to "+xpath);
	}
	public String getdata(WebDriver driver,String xpath) {
		//System.out.println("getdata");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		String data =driver.findElement(By.xpath(xpath)).getText();	
		//System.out.println("Actual"+data);
		return data;
	}
	String getdata(WebDriver driver,String xpath,String attri) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		String data =driver.findElement(By.xpath(xpath)).getAttribute(attri);
		return data;
	}
	
	public void dateselection(WebDriver driver,String date,String month, String year) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		try {
		select(driver,"//select[@class=\"ui-datepicker-year\"]",year);
		select(driver,"//select[@class=\"ui-datepicker-month\"]",month);
		click(driver, "//td[@data-event=\"click\"]/a[text()="+date+"]");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Incorrect date entered");
			
		}
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class=\"ui-datepicker-year\"]")));
	}
	
	public void goto_url(WebDriver driver, String url) {
		s.info("Navigate to "+url);
		driver.navigate().to(url);
		s.info("Navigated to "+url);
	}
	
	public void select_time(WebDriver driver, String time,String xpath) {
		click(driver, xpath);
		click(driver, "//div[@class=\"ui-timepicker-container ui-timepicker-standard\"]/div/ul/li/a[contains(text(),"+time+")]");
	}
	
	public void date(WebDriver driver,String xpath,String date){
		//write(driver,xpath);
		click(driver, xpath);
		String datting[] =date.split("-"); 
		
		//Actions a= new Actions(driver);
		//a.
		
		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(xpath))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
		try {
		select(driver,"//select[@class=\"ui-datepicker-year\"]",datting[2]);
		select(driver,"//select[@class=\"ui-datepicker-month\"]",datting[1]);
		click(driver, "//td[@data-event=\"click\"]/a[text()="+datting[0]+"]");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Incorrect date entered");
			
		}
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class=\"ui-datepicker-year\"]")));
	
	}
	
	
	//check for validation //Goaservices
	/*public void check_Validation(WebDriver driver, String xpath, String Expected_result) throws Exception {
		String error = null;
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		try {
			System.out.println(xpath+"/parent::div/div");
			error=driver.findElement(By.xpath(xpath+"/parent::div/div")).getText();
			//System.out.println(error);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		if (error!=null) {
			if (error.contains(Expected_result)) {
				System.out.println("correct");
			}
			else {
				softAssert.fail("Error Encountered "+"Expected "+Expected_result+" "+ "but found "+error);
				log.info("Error Encountered "+"Expected "+Expected_result+" "+ "but found "+error);
				
			}
		}
		else {
			softAssert.fail("Error Encountered "+"Element not found");
			log.info("Error Encountered "+"Element not found");
			
		}
		
	}*/
	public void check_Validation(WebDriver driver, String xpath) throws Exception {
	//	s.info("check for validation ("+xpath+")","C:\\Users\\DELL\\Documents\\eclipse Documents Files\\GelCareers\\target\\new_report\\image\\"+b+".png",b+");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		
		String validations=xpath+validation;
		System.out.println(validations);
		validations="//span[@class=\"feedbackPanelERROR\"]/../../../../.."+xpath;
		
		//System.out.println("Check validation "+driver.findElement(By.xpath(validations)).isDisplayed());
		try {
			//System.out.println(xpath+"/parent::div/div");
			driver.findElement(By.xpath(validations));
			//System.out.println(error);
			s.pass("Validation appeared "+validations);
		}
		catch (Exception e) {
			// TODO: handle exception
			s.fail("Validation not found "+validations,new Screenshot().take_screenshoot(driver));
			softAssert.fail("Error Encountered "+"Element not found "+validations);
			log.info("Error Encountered "+"Element not found "+validations);
		}
	}
		public void check_Validation_presence(WebDriver driver, String xpath) throws Exception {
			//	s.info("check for validation ("+xpath+")","C:\\Users\\DELL\\Documents\\eclipse Documents Files\\GelCareers\\target\\new_report\\image\\"+b+".png",b+");
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
				
				String validations=xpath+validation;
				System.out.println(validations);
				validations="//span[@class=\"feedbackPanelERROR\"]/../../../../.."+xpath;
				
				//System.out.println("Check validation "+driver.findElement(By.xpath(validations)).isDisplayed());
				try {
					//System.out.println(xpath+"/parent::div/div");
					driver.findElement(By.xpath(validations));
					//System.out.println(error);
					s.pass("Validation appeared "+validations);
				}
				catch (Exception e) {
					// TODO: handle exception
					s.fail("Validation not found "+validations,new Screenshot().take_screenshoot(driver));
					softAssert.fail("Error Encountered "+"Element not found "+validations);
					log.info("Error Encountered "+"Element not found "+validations);
				}
		
	}
	public void check_Validation_text(WebDriver driver, String xpath, String Expected_result) throws Exception {
		String error = null;
		s.info("check for validation text "+Expected_result);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		String validations=xpath+validation;
		try {
			//System.out.println(xpath+"/parent::div/div");
			error=driver.findElement(By.xpath(validations)).getText();
			//error+=error.toLowerCase();
			//System.out.println(error);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		if (error!=null) {
			if (error.contains(Expected_result)) {
				System.out.println(error+" "+Expected_result);
				System.out.println("correct");
				s.pass("Validation Matched");
			}
			else {
				s.warning("Validation text does not Matched. Expected out " +Expected_result+"& Actual output "+error,new Screenshot().take_screenshoot(driver));
				//softAssert.fail("Error Encountered "+"Expected "+Expected_result+" "+ "but found "+error);
				log.error("Error Encountered "+"Expected "+Expected_result+" "+ "but found "+error);
				
			}
		}
		else {
			s.fail("Validation not found "+validations,new Screenshot().take_screenshoot(driver));
			softAssert.fail("Validation not found "+"Element not found");
			log.error("Error Encountered "+"Element not found");
			
		}
		
	}
	
	
	public void check_Validation_ignore_case(WebDriver driver, String xpath, String Expected_result) throws Exception {
		String error = null;
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		try {
			System.out.println(xpath+"/parent::div/div");
			error=driver.findElement(By.xpath(xpath+"/parent::div/div")).getText();
			//System.out.println(error);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		if (error!=null) {
			if (error.equalsIgnoreCase(Expected_result)) {
				System.out.println("correct");
			}
			else {
				s.fail("Expected "+Expected_result+" "+ "but found"+error,new Screenshot().take_screenshoot(driver));
				softAssert.fail("Error Encountered "+"Expected "+Expected_result+" "+ "but found"+error);
				log.info("Error Encountered "+"Expected "+Expected_result+" "+ "but found "+error);
				
			}
		}
		else {
			//s.fail("Element not found"+validation,new Screenshot().take_screenshoot(driver));
			
			softAssert.fail("Error Encountered "+"Element not found");
			log.info("Error Encountered "+"Element not found");
			
		}
		
	}
	public void check_absence_Validation(WebDriver driver, String xpath) {
		String error = null;
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		String validations=xpath+validation;
		validations="//span[@class=\"feedbackPanelERROR\"]/../../../../.."+xpath;
		try {
			System.out.println("Check validation "+driver.findElement(By.xpath(validations)).isDisplayed());
			
			System.out.println(validations);
			error=driver.findElement(By.xpath(validations)).getText();
			//Assert.fail("Validation appeared");
			//System.out.println(error);
		}
		catch (NoSuchElementException e) {
			System.out.println("not present");
			// TODO: handle exception
			//System.out.println("In");\
			/*s.fail("Error appeared",new Screenshot().take_screenshoot(driver));
			s.fail(e);
			log.error(e);*/
		}
		if (error==null) {
			s.pass("No validation appeared");
		}else {
			s.fail("Validation Appeared",new Screenshot().take_screenshoot(driver));
			softAssert.fail("Validation appeared");
			log.info("Error Encountered "+"Validation appeared");
			
			//Assert.fail("Expected "+Expected_result+" "+ "but found"+error);
		}
		
	}
	
	
	public void check_textbox_data(WebDriver driver, String xpath, String Expected_data) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		//System.out.println(xpath);
		//System.out.println("Getting data"+getdata(driver,xpath));
		
		if ((getdata(driver,xpath,"value").toLowerCase().equals(Expected_data.toLowerCase()))) {
			s.pass("Textfield data matched");
			if (!(getdata(driver,xpath,"value").equals(Expected_data))) {
				s.warning("Expected "+Expected_data+" Actual is "+getdata(driver,xpath,"value"),new Screenshot().take_screenshoot(driver));
			}
		}
		
		else {
			s.fail("Expected "+Expected_data+" Actual is "+getdata(driver,xpath,"value"),new Screenshot().take_screenshoot(driver));
		}
		softAssert.assertEquals(getdata(driver,xpath,"value").toLowerCase(), Expected_data.toLowerCase(),"Error Encountered ");
		log.info(getdata(driver,xpath,"value")+" "+ Expected_data+" Error Encountered ");
		
	}
	
	
	public void check_attribute_data(WebDriver driver, String xpath, String Excel, String Expected_data) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		//System.out.println(xpath);
		//System.out.println("Getting data"+getdata(driver,xpath));
		
		if ((getdata(driver,xpath,Excel).toLowerCase().equals(Expected_data.toLowerCase()))) {
			s.pass("text data matched");
			if (!(getdata(driver,xpath,Excel).equals(Expected_data))) {
				s.warning("Expected "+Expected_data+" Actual is "+getdata(driver,xpath,Excel),new Screenshot().take_screenshoot(driver));
			}
		}
		
		else {
			s.fail("Expected "+Expected_data+" Actual is "+getdata(driver,xpath,Excel),new Screenshot().take_screenshoot(driver));
		}
		softAssert.assertEquals(getdata(driver,xpath,Excel).toLowerCase(), Expected_data.toLowerCase(),"Error Encountered ");
		log.info(getdata(driver,xpath,"value")+" "+ Expected_data+" Error Encountered ");
		
	}
	public void check_radio_selected(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		if(driver.findElement(By.xpath(xpath)).isSelected())
		{
		//Assert.fail("Validation appeared");
		}
		else {
			Assert.fail("Error Encountered "+"Item unselected" + xpath);
		}
	}
	
	public void check_radio_unselected(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		if(driver.findElement(By.xpath(xpath)).isSelected())
		{
		Assert.fail("Error Encountered "+"Item selected "+ xpath);
		}
		else {
			//Assert.fail("Validation appeared");
		}
	}
	
	//check for textbox empty
	public void check_textbox_empty(WebDriver driver, String xpath) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		//System.out.println(xpath);
		//System.out.println("Getting data"+getdata(driver,xpath));
		softAssert.assertEquals(getdata(driver,xpath,"value").isEmpty(), true,"Error Encountered text Not Empty "+getdata(driver,xpath,"value"));
		log.info(getdata(driver,xpath,"value").isEmpty()+"true"+" Error Encountered text Not Empty "+getdata(driver,xpath,"value"));
		if (!getdata(driver,xpath,"value").isEmpty()) {
			//s.fail("Text box not empty",new Screenshot().take_screenshoot(driver));
			//s.pass("Text found");
			s.fail("Text box not empty found "+getdata(driver,xpath,"value"),new Screenshot().take_screenshoot(driver));
			
		}
		else {
			//s.fail("Text box not empty "+getdata(driver,xpath,"value"),new Screenshot().take_screenshoot(driver));
			s.pass("Text Box Empty");
		}
		
	}
	
	public void check_data(WebDriver driver, String xpath, String Expected_data) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		//System.out.println(xpath);
		//System.out.println("Getting data"+getdata(driver,xpath));
		System.out.println("Check Data");
		//s.info("Check ");
		if(getdata(driver,xpath).toLowerCase().equals(Expected_data.toLowerCase())) {
			s.pass("Data Matched");
			if(!getdata(driver,xpath).equals(Expected_data)) {
				s.warning("Expected data "+Expected_data+ " Actual data "+getdata(driver,xpath),new Screenshot().take_screenshoot(driver));
				}
			
			
		}
		else {
			s.fail("Expected data "+Expected_data+ " Actual data "+getdata(driver,xpath),new Screenshot().take_screenshoot(driver));
			
			softAssert.assertEquals(getdata(driver,xpath), Expected_data,"Error Encountered ");

			log.info(getdata(driver,xpath)+" "+ Expected_data+" Error Encountered ");
			System.out.println("Check data"+getdata(driver,xpath)+" "+Expected_data  );
			
		}
		//System.out.println("out Check data "+Expected_data+getdata(driver,xpath).equals(Expected_data));
	}
	
	public void check_data_contains(WebDriver driver, String xpath, String Expected_data) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		//System.out.println(xpath);
		//System.out.println("Getting data"+getdata(driver,xpath));
		System.out.println("data conrains"+getdata(driver,xpath).contains(Expected_data));
		if(getdata(driver,xpath).contains(Expected_data)) {
			
		}
		else {
			softAssert.fail("Error Encountered "+"Expected "+Expected_data + " Found"+getdata(driver,xpath));
			log.info("Error Encountered "+"Expected "+Expected_data + " Found"+getdata(driver,xpath));
		}
		//Assert.assertEquals(getdata(driver,xpath), Expected_data);
	}
	public void check_selected_data(WebDriver driver, String xpath, String Expected_data) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
		//System.out.println(xpath);
		//System.out.println("Getting data"+getdata(driver,xpath));
		//String sdf= xpath+"/option[@selected=\"selected\"]";
		//System.out.println(sdf);
		softAssert.assertEquals(getdata(driver,(xpath+"/option[@selected=\"selected\"]")), Expected_data);
	
		log.info(getdata(driver,(xpath+"/option[@selected=\"selected\"]"))+" "+ Expected_data);
		if(getdata(driver,(xpath+"/option[@selected=\"selected\"]")).toLowerCase().equals(Expected_data.toLowerCase())) {
		s.pass("option selected is " +Expected_data);
		if(!getdata(driver,(xpath+"/option[@selected=\"selected\"]")).equals(Expected_data)) {
			s.warning("Expected option not selected "+Expected_data+" selected options is "+getdata(driver,(xpath+"/option[@selected=\"selected\"]")),new Screenshot().take_screenshoot(driver));
		}
	}
		else {
			s.fail("Expected option not selected "+Expected_data+" selected options is "+getdata(driver,(xpath+"/option[@selected=\"selected\"]")),new Screenshot().take_screenshoot(driver));
		}
	}
	
	
	public static boolean compareList(List ls1, List ls2){
	    return ls1.containsAll(ls2) && ls1.size() == ls2.size() ? true :false;
	     }
	
	public void check_any_validation(WebDriver driver, String xpath) throws Exception {
		List<WebElement> error1 = driver.findElements(By.xpath(xpath));
		List<String>Validation_data = new ArrayList<>();
		if (error1.size()==0) {
			System.out.println("No issue");
		}
		else {
			for (int i=0;i<error1.size();i++) {
				Validation_data.add(error1.get(i).getText());
			}
			softAssert.fail("Error Validation alert appears "+Validation_data.toString());
			log.info("Error Validation alert appears "+Validation_data.toString());
			
		}
	}
	
	
	
	public void check_options_in_select(WebDriver driver, String xpath, String Expected_data) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		List<String> myList = new ArrayList<String>(Arrays.asList(Expected_data.split("\n")));
		List<WebElement> error1 = driver.findElements(By.xpath(xpath+"/option"));
		List data2= new ArrayList();
		for (int i=0;i<error1.size();i++) {
			data2.add(error1.get(i).getText());
			//System.out.println();
		}
		//System.out.println(compareList(data2, myList));
		if(compareList(data2, myList)) {
			
		}
		else {
			s.fail("Expected "+myList.toString() +"<br>Found "+data2);
			s.fail("Option does not matched as Excpected ",new Screenshot().take_screenshoot(driver));
			
			softAssert.fail("Error Encountered "+"Expected"+myList.toString()+"But Found"+data2);
			log.info("Error Encountered "+"Expected"+myList.toString()+"But Found"+data2);
			
		}
		
		//String sdf= xpath+"/option[@selected=\"selected\"]";
		//System.out.println(sdf);
		//Assert.assertEquals(getdata(driver,(xpath+"/option[@selected=\"selected\"]")), Expected_data);
	}
	
	public String calculate_xpath(String xpath,ArrayList<String> xpath_name,ArrayList<String> range_xpath )throws Exception {
		int index =xpath_name.indexOf(xpath);
		return(range_xpath.get(index));
	}
	
	
	
	public void Check_for_presence_of_alert(WebDriver driver ) {
		try {
		s.info("check for presence of alert");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
		//driver.findElement(By.xpath(xpath_idprrof)).sendKeys(Keys.TAB);
		//Thread.sleep(5000);
		String alert_data = driver.switchTo().alert().getText();
		s.pass("Alert details "+alert_data);
		driver.switchTo().alert().accept();
		}
		catch (UnhandledAlertException e1) {
			// TODO: handle exception
			System.out.println("Unhandled da");
			String alert_data = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
		}
		catch (Exception e) {
			// TODO: handle exception
			s.fail("No alert displayed",new Screenshot().take_screenshoot(driver));
		}
		//Assert.assertEquals(alert_data, Expected_data);
		}
	
	public void Check_for_absence_of_alert(WebDriver driver,String Expected_data ) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		try {
        wait.until(ExpectedConditions.alertIsPresent());
		//driver.findElement(By.xpath(xpath_idprrof)).sendKeys(Keys.TAB);
		//Thread.sleep(5000);
		String alert_data = driver.switchTo().alert().getText();
		s.fail("alert appeared "+alert_data,new Screenshot().take_screenshoot(driver));
		driver.switchTo().alert().accept();
		softAssert.fail("Alert appeared "+alert_data);
		}
		catch (Exception e) {
			// TODO: handle exception
			s.pass("No alert");
		}
		//Assert.assertEquals(alert_data, Expected_data);
		}

	public void Check_for_presence_of_alert_check_data(WebDriver driver,String Expected_data ) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
		//driver.findElement(By.xpath(xpath_idprrof)).sendKeys(Keys.TAB);
		//Thread.sleep(5000);
		String alert_data = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Assert.assertEquals(alert_data, Expected_data);
		}
		
	public void Check_for_presence_of_Element(WebDriver driver, String xpath) {
		//s.info("Check for Presen");
		try {
			driver.findElement(By.xpath(xpath));
			s.pass("Element Present "+xpath);
		}
		catch (Exception e) {
			// TODO: handle exception
			s.fail("Element No Found "+xpath,new Screenshot().take_screenshoot(driver));
			Assert.fail("Error Encountered "+"Element not found"+xpath);
			
		}
		}	
		
	public void Check_for_absence_of_Element(WebDriver driver, String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
			s.fail("Element Found "+xpath, new Screenshot().take_screenshoot(driver));
			Assert.fail("Error Encountered "+"Element found"+xpath);
		}
		catch (Exception e) {
			// TODO: handle exception
			s.pass("Element Absent" +xpath);
		}
		}
	public void Verify_for_absence_of_Element(WebDriver driver, String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
			s.fail("Element Found "+xpath, new Screenshot().take_screenshoot(driver));
			softAssert.fail("Element Found "+xpath);
			//Assert.fail("Error Encountered "+"Element found"+xpath);
		}
		catch (Exception e) {
			// TODO: handle exception
			s.pass("Element Absent" +xpath);
		}
		}
	public void check_for_element_disabled(WebDriver driver, String xpath) throws Exception {
		
		if (!driver.findElement(By.xpath(xpath)).isEnabled()) {
			
		}
		else {
			softAssert.fail("Error Encountered "+"Element Enabled"+xpath);
			log.info("Error Encountered "+"Element Enabled"+xpath);
		
		}
		
	}
	
	public void check_validations(WebDriver driver, String xpath, String Expected_data) throws Exception {
		//String s[]=Expected_data.split("\n");
		List<String> myList = new ArrayList<String>(Arrays.asList(Expected_data.split("\n")));
		List<WebElement> error1 = driver.findElements(By.xpath(xpath));
		List<String>actual_data = new ArrayList<>();
		for (int i=0;i<error1.size();i++) {
			actual_data.add(error1.get(i).getText());
		}
		if(compareList(actual_data, myList)) {
			
		}
		else {
			softAssert.fail("Error Encountered "+"Expected"+myList.toString()+"But Found"+actual_data);
			log.info("Error Encountered "+"Expected"+myList.toString()+"But Found"+actual_data);
			
		}
		//myList.
		//System.out.println(error1.toString());
		//error1.
		//List 
		///for(int)
	}
	
	//clear textdata function
	public void Clear_textdata(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).clear();
	}
	
	
	//upload Function
	public void upload(WebDriver driver, String xpath, String Image_location) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		WebElement s= driver.findElement(By.xpath(xpath));
		Actions a = new Actions(driver);
		a.moveToElement(s).click().build().perform();
		Runtime.getRuntime().exec("E://document//Selenium_upload//TestOpen.exe"+" "+Image_location);
	}
	
	/*public void ck_editor(WebDriver driver,String xpath, String data) {
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		WebElement iframe = driver.findElement(By.xpath(xpath)); 
		driver.switchTo().frame(iframe); 
		WebElement tinymce = driver.findElement(By.tagName("body")); 
		tinymce.sendKeys(data);
		driver.switchTo().parentFrame();
	}*/
	
	//wait until visibility
	public void waituntil_visibility(WebDriver driver,String xpath,String timing) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(timing)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	//wait until presence
	public void waituntil_presence(WebDriver driver,String xpath,String timing) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(timing)));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}
	
	public WebDriver Open() {
		s.info("Open Browser");
		WebDriverManager.chromedriver().setup();
		//ChromeOptions option = new ChromeOptions();
		//option.setHeadless(true);
		//option.addArguments("--headless");
		WebDriver driver = new ChromeDriver();
		//driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println(driver);
		s.pass("Open Browser opened and Maximized");
		return driver;
	}
	public WebDriver Close_browser(WebDriver driver) {
		s.info("Browser close");
		driver.quit();
		s.pass("closed");
		return null;
	}
	public void check_selected_data_absence(WebDriver driver, String xpath, String Expected_data) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		try {
			driver.findElement(By.xpath(xpath+"/option[@selected=\"selected\"]"));
			Assert.fail("Error Encountered "+"Element found"+xpath+"/option[@selected=\"selected\"] Error");
		}
		catch (Exception e) {
			// TODO: handle exception
			//System.out.println();
			//Assert.fail("Element not found"+xpath);
		}
			
		//System.out.println(xpath);
		//System.out.println("Getting data"+getdata(driver,xpath));
		//String sdf= xpath+"/option[@selected=\"selected\"]";
		//System.out.println(sdf);
		
		//Assert.assertEquals(getdata(driver,(xpath+"/option[@selected=\"selected\"]")), Expected_data);
	}
	public void check_dropdown_reset(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		try {
			
			driver.findElement(By.xpath(xpath+"/option[@selected=\"selected\"]"));
			Assert.fail("Error Encountered "+"Option didn't get reset ,selected option is "+getdata(driver,(xpath+"/option[@selected=\"selected\"]")));
		}
		catch (Exception e) {
			
			// TODO: handle exception
			//Assert.fail("Element not found"+xpath);
		}
	}
}
