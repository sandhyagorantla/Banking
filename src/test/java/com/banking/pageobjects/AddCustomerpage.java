package com.banking.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerpage {
	WebDriver driver;

	public AddCustomerpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "New Customer")
	WebElement btnnewcustomer;

	@FindBy(id = "message")
	WebElement txtcustomername;

	@FindBy(xpath = "//input[@value='f']")
	WebElement rdGender;

	@FindBy(id = "dob")
	WebElement txtdob;

	@FindBy(name = "addr")
	WebElement txtaddress;

	@FindBy(name = "city")
	WebElement txtcity;

	@FindBy(name = "state")
	WebElement txtstate;

	@FindBy(name = "pinno")
	WebElement txtPin;

	@FindBy(name = "telephoneno")
	WebElement txtphonenumber;

	@FindBy(name = "emailid")
	WebElement txtemail;

	@FindBy(name = "password")
	WebElement txtpassword;

	@FindBy(name = "sub")
	WebElement btnsubmit;

	public void clickAddnewcustomer() {
		btnnewcustomer.click();
	}

	public void setcustomername(String cname) {
		txtcustomername.sendKeys(cname);
	}

	public void clickgender() {
		rdGender.click();
	}

	public void setdob(String mm, String yy, String dd) {
		txtdob.sendKeys(mm);
		txtdob.sendKeys(yy);
		txtdob.sendKeys(dd);
	}

	public void setaddress(String address) {
		txtaddress.sendKeys(address);
	}

	public void setcity(String city) {
		txtcity.sendKeys(city);
	}

	public void setstate(String state) {
		txtstate.sendKeys(state);
	}

	public void setpin(String pin) {
		txtPin.sendKeys(String.valueOf(pin));
	}

	public void setphonenumber(String phonenumber) {
		txtphonenumber.sendKeys(phonenumber);
	}

	public void setemail(String email) {
		txtemail.sendKeys(email);
	}

	public void setpassword(String pwd) {
		txtpassword.sendKeys(pwd);
	}

	public void clicksubmit() {
		btnsubmit.click();
	}
}
