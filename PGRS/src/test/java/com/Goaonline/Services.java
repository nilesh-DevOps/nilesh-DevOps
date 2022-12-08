package com.Goaonline;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.required.Excel_Import_xpath;
import com.required.Testing_variables;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Services {
  //@Test
  public WebDriver domicile() throws Exception {
	  WebDriver driver;
	  
		Excel_Import_xpath x= new Excel_Import_xpath();
		do {
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			//ption.setHeadless(true);
			//option.addArguments("--headless");
			driver = new ChromeDriver(option);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			System.out.println(driver);
			WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get("http://10.190.83.12/edistrict/");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[contains(text(),'Login')])[2]")));
			driver.findElement(By.xpath("(//a[contains(text(),'Login')])[2]")).click();
			driver.findElement(By.xpath("//input[@placeholder=\"Enter Username/Email\"]")).sendKeys("rashmi");
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@placeholder=\"Enter Password\"]")).sendKeys("Pass@123");
			//Thread.sleep(3000);
			
			driver.findElement(By.xpath("(//a[contains(text(),'Login')])[4]")).click();
			//Thread.sleep(20000);
			//t.click(driver, x.data[3]);
			
			System.out.println("Logged IN");
			/*wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Services') and @id=\"navbarDropdownMenuLink\"]")));
			driver.findElement(By.xpath("//a[contains(text(),'Services') and @id=\"navbarDropdownMenuLink\"]")).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'All Services')]")));
			driver.findElement(By.xpath("//a[contains(text(),'All Services')]")).click();
			//Thread.sleep(5000);
			System.out.println("allservices");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Search\"]")));
			driver.findElement(By.xpath("//input[@placeholder=\"Search\"]")).sendKeys("Domicile");
			//Thread.sleep(5000);
			System.out.println("Docilcile");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Domicile')]")));
			driver.findElement(By.xpath("//a[contains(text(),'Domicile')]")).click();
			//Thread.sleep(3000);
			System.out.println("clecked");*/
			driver.navigate().to(("http://10.190.83.12/edistrict/Appln/Uil/DeptServices?__DocId=REV&__ServiceId=REV03"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Proceed to Apply')]")));
			driver.findElement(By.xpath("//a[contains(text(),'Proceed to Apply')]")).click();
			System.out.println("prceed");
			try {
				
				WebDriverWait wait1 =new WebDriverWait(driver, Duration.ofSeconds(10));
				wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@name=\"wmcApplying_:rdlType_\"])[1]")));
				break;
			}
			catch (Exception e) {
				// TODO: handle exception
				continue;
			}
			//break;
		}while(true);
		//
		 return driver;
  }
  
  public void Verify_Document(Testing_variables t,WebDriver driver ,String file) {
	  String path="//div[contains(@id,\"wmcDocumentDetails\")]/div[@id=\"docdetails\"]/section/div/table/tbody/tr/td/span[contains(text(),\""+file+"\")]/../../td[3]/a";
	  System.out.println(path);
	  t.waituntil_presence(driver, path,"10");
	  t.click(driver, path);
  }
  
  public void compliance(Testing_variables t,WebDriver driver, String Ack) throws Exception {
	 
	 WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
	 driver.get("http://10.190.83.12/edistrict/");
	 driver.navigate().refresh();
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[contains(text(),'Login')])[2]")));
	 driver.findElement(By.xpath("(//a[contains(text(),'Login')])[2]")).click();
	 driver.findElement(By.xpath("//input[@placeholder=\"Enter Username/Email\"]")).sendKeys("rashmi");
	 //Thread.sleep(3000);
	 driver.findElement(By.xpath("//input[@placeholder=\"Enter Password\"]")).sendKeys("Pass@123");
	 //Thread.sleep(3000);

	 driver.findElement(By.xpath("(//a[contains(text(),'Login')])[4]")).click();
	 //Thread.sleep(20000);
	 //t.click(driver, x.data[3]);
	 t.goto_url(driver, "http://10.190.83.12/edistrict/Appln/UIL/userHome?__UserId=&__DocId=");
	 System.out.println("Logged IN");
	  for (int l=0;l<5;l++) {
			
			//do {
		 
				try {
					
					t.waituntil_presence(driver, "//a[contains(text(),\"Switch\")]", "10");
					t.click(driver, "//a[contains(text(),\"Switch\")]");
					break;
				}
				catch (Exception e) {
					// TODO: handle exception
					driver.navigate().refresh();
					continue;
				}
			//}while(true);
			}
	  Thread.sleep(5000);
	 // for (int u=1;u<5;u++) {
		  //Thread.sleep(2000);
		  List<WebElement> Acks=driver.findElements(By.xpath("(//table[@id=\"tblMaster\"]/tbody/tr/td)[2]/div/div/div/div/div[1]/div/div/div/div/div[@class=\"row form-row\"]/div/div/table/tbody/tr/td[3]"));
	  int het =123;
		  for (int r=0;r<Acks.size();r++) {
			  
		  if(Acks.get(r).getText().equals(Ack)) {
			  System.out.println("Acks"+Acks.get(r).getText()+" "+Ack);
			  System.out.println("got");
			  het=r;
			  break;
		  }
	  }
	if (het!=123) {
		System.out.println("(//table[@id=\\\"tblMaster\\\"]/tbody/tr/td)[2]/div/div/div/div/div[1]/div/div/div/div/div[@class=\\\"row form-row\\\"]/div/div/table/tbody/tr["+(het+2)+"]/td[7]/a");
		t.click(driver, "(//table[@id=\"tblMaster\"]/tbody/tr/td)[2]/div/div/div/div/div[1]/div/div/div/div/div[@class=\"row form-row\"]/div/div/table/tbody/tr["+(het+2)+"]/td[7]/a");
	
	}
	else {
		System.out.println("Not found");
	}
		  //}
  }
  
  }
 
