package com.banking.testcases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.banking.pageobjects.LoginPage;
import com.banking.utilities.ExcelUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider="LoginData")
	public void loginDDT() throws InterruptedException
	{
		LoginPage l = new LoginPage(driver);
		l.setUserName(username);
		logger.info("user name provided");
		l.setPassword(password);
		logger.info("password provided");
		l.clickSubmit();
		Thread.sleep(3000);
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();//navigate to normal window
			Assert.assertFalse(false);
			logger.warn("login failed");
		}else
		{
			Assert.assertTrue(true);
			logger.info("login passed");
			l.clicklogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();//navigate to normal window
			
		}
	}
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}catch(Exception e ) {
			return false;
		}
	}

@DataProvider(name="LoginData")
   public String[][] getdata() throws IOException{
	String path = System.getProperty("user.dir")+"/src/test/java/com/banking/testdata/LoginData.xlsx";
       int rownum = ExcelUtils.getRowCount(path, "Sheet1");
       int colnum = ExcelUtils.getCellCount(path,"Sheet1", 1);
       String logindata[][]=new String[rownum][colnum];
       for(int i=1;i<=rownum;i++) 
       {
	      for(int j=0;j<colnum;j++) 
	      {
		   logindata[i-1][j]=ExcelUtils.getCellData(path, "Sheet1", i, j);
	      }
       }
       return logindata;
       }
}
