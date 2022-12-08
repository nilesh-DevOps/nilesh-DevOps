package com.required;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class Validatiion_matrix {
  public void validation_rejection_matrix(String acceptance_String, String Validation_errors_path, String location , Testing_variables t,WebDriver driver, ExtentTest test) throws Exception {
	  String s="`~!@#$%^&*()_+-=[]{};:',.?><|\"\\/a1";
	 // String s="`~!@#$%^";
		//String f="()@%&\"";
		String rejection[]=s.split("");
		//String errors[]=Validation_errors.split(",");
		String w[]=acceptance_String.split("");
		int flog=0;
		List<String>Validation_data = new ArrayList<>();
		for (int i=0;i<rejection.length;i++) {
			if (!acceptance_String.contains(rejection[i])) {
				Validation_data.add(rejection[i]);
			}
			
		}
		System.out.println(Validation_data.toString());
		for (int i=0;i<Validation_data.size();i++) {
			//System.out.println(s.contains(w[i]))
			Thread.sleep(1000);
			t.Clear_textdata(driver, location);
			test.info("send "+Validation_data.get(i)+ " to "+location);
			
			t.send_data(driver, location, Validation_data.get(i));
			t.click(driver, "//input[@name=\"btnSavenForward_:confirmButton\"]");
			//t.waituntil_presence(driver, xpath, timing);
			t.check_Validation(driver,Validation_errors_path);
			
		}
  }
}
