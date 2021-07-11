package com.test.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.framework.util.BaseTest;
import com.ui.pages.SignInPage;



public class SignInTest extends BaseTest{
@Test
	public void testSignInWithUser() {

	SignInPage signPage = new SignInPage(driver);
	signPage.signDetails("userEmail","password");
	signPage.signButton();



	}
}
