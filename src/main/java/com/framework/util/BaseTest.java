package com.framework.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {
	
	protected WebDriver driver;
	protected ExtentReports extent;
	protected ExtentTest test;

	@BeforeSuite
	public void setReport() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("report.html");
		// htmlReporter.loadXMLConfig("extent-config.xml");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("os", System.getProperty("os.name"));
	}

	@BeforeClass
	public void setup() throws Exception {
		WebDriverManager.chromedriver().setup();
		// WebDriverManager.firefoxdriver().setup();
		driver = new ChromeDriver();
		String appUrl = Configuration.getConfig("URL");
		System.out.println(appUrl);
		driver.navigate().to(appUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void methodName() {
		test = extent.createTest("MyTest", "Method");
		test.assignAuthor("Ritu");
		test.assignCategory("Method");
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreen(ITestResult result) throws IOException {
		
		if (result.isSuccess()) {
			test.log(Status.PASS, "Test Method passed");
		} else {
			test.log(Status.FAIL, "Test Method Failed");
			test.log(Status.FAIL, result.getThrowable());

			// Take base64Screenshot screenshot.
			String base64Screenshot = "data:image/png;base64,"
					+ ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

			try {
				test.fail("details", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@AfterSuite(alwaysRun = true)
	public void flushReport() {
		extent.flush();
	}
	
	
	public void takeScreenShot(String fileName) throws IOException {
		TakesScreenshot takeScreenShotObject = (TakesScreenshot) driver;
		File screenshot = takeScreenShotObject.getScreenshotAs(OutputType.FILE);

		File screenshotLocation = new File(fileName + ".png");
		FileUtils.copyFile(screenshot, screenshotLocation);
	}

}
