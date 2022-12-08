package com.Goaonline;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.required.Testing_variables;

public class Domicile {
  @Test
  public void PutAcknowledgement(String number, Testing_variables t, ExtentTest test,WebDriver driver,String Location) {
	  t.waituntil_presence(driver, Location, "10");
	  t.send_data(driver, Location, number);
  }
  
 //public void 
}
