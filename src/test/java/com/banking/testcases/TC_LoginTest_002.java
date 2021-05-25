package com.banking.testcases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.banking.pageobjects.AddCustomerpage;
import com.banking.pageobjects.LoginPage;
import com.banking.utilities.ExcelUtils;

public class TC_LoginTest_002 extends BaseClass {

	@Test
	public void addnewcustomer() throws IOException {
		
		 LoginPage l = new LoginPage(driver); 
		// String path = "C:\\Users\\91910\\selenium\\Banking\\src\\test\\java\\com\\banking\\testdata\\LoginData.xlsx";
		 //String username = ExcelUtils.getCellData(path, "Sheet1", 3, 1); 
		 //String password = ExcelUtils.getCellData(path, "Sheet1", 3, 2);
		 l.setUserName(username); 
		 logger.info("entered name successfully");
		 l.setPassword(password); 
		 logger.info("entered password successfully");
		 l.clickSubmit();
		 
		

		AddCustomerpage addcust = new AddCustomerpage(driver);
		addcust.clickAddnewcustomer();
		addcust.setcustomername("sandhya");
		addcust.clickgender();
		addcust.setdob("12", "21", "12");
		addcust.setaddress("address");
		addcust.setcity("city");
		addcust.setstate("state");
		addcust.setpin("123445");
		addcust.setphonenumber("1234567812");
		String email = randomString() + "@gmail.com";
		addcust.setemail(email);
		addcust.setpassword("123456");
		addcust.clicksubmit();
	}

	public String randomString() {
		String genstring = RandomStringUtils.randomAlphabetic(5);
		return genstring;

	}
}
