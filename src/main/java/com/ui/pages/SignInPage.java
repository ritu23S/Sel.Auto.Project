package com.ui.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class SignInPage {
	
	protected WebDriver driver;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void signButton() {

	WebElement signButton= driver.findElement(By.xpath("	//a[@href=\"http://automationpractice.com/index.php?controller=my-account\"]"));
	signButton.click();
}
	
	public void signDetails(String userEmail, String password) {
		driver.findElement(By.cssSelector("[id='email']")).sendKeys(userEmail);
		driver.findElement(By.cssSelector("[id='passwd']")).sendKeys(password);
		driver.findElement(By.cssSelector("[id='SubmitLogin']")).click();
	}
	
	
	



		
	}
	
	


