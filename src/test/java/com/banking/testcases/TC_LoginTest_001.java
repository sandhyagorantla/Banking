package com.banking.testcases;


	import java.io.IOException;


	import org.testng.Assert;
	
	import org.testng.annotations.Test;

import com.banking.pageobjects.LoginPage;
import com.banking.utilities.ExcelUtils;




	public class TC_LoginTest_001 extends BaseClass {
		@Test
		public void logintest() throws IOException {
			
			
			logger.info("url is opened");
			LoginPage l = new LoginPage(driver);
			String path = "C:\\Users\\91910\\selenium\\Banking\\src\\test\\java\\com\\banking\\testdata\\LoginData.xlsx";
			String username = ExcelUtils.getCellData(path, "Sheet1", 2, 1);
			String password = ExcelUtils.getCellData(path, "Sheet1", 2, 2);
			l.setUserName(username);
			logger.info("entered name successfully");
			l.setPassword(password);
			logger.info("entered password successfully");
			l.clickSubmit();
			// System.out.println(driver.getTitle());
			if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
				Assert.assertTrue(true);
				logger.info("login test pass");
				
			} else {
				
				Assert.assertFalse(false);
				logger.info("login test fail");
			}

		}

	}

