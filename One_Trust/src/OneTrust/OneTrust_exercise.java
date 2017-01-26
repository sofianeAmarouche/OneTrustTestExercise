package OneTrust;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OneTrust_exercise {

	WebDriver driver;
	@BeforeTest
	public void delete_cookies(){
		//using firefox browser
		// for selenium 3.0 and up we need to invoke gecko driver to invoke firefox
		System.setProperty("webdriver.gecko.driver","C:/Selenium2/Browsers-Drivers/geckodriver.exe");
		driver = new FirefoxDriver();

		String url="https://onetrust.com/";
		driver.get(url);
		
		//maximizing the window
		driver.manage().window().maximize();
		
		//This method will delete all the cookies before initiating the test
		driver.manage().deleteAllCookies();
	}
	
	 @Test(enabled=true)
	 public void testing(){
		
		// for the purpose of this exercise i will just use  by.name methods for simplicity
		//otherwise Xpath and cssSelector are mostly used for their consistency 
		WebElement contact_us = driver.findElement(By.linkText("Contact"));
		//Clicking on the contact button
		contact_us.click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.titleContains("OneTrust | Contact"));
		
		//Creating the web elements
		WebElement First_Name = driver.findElement(By.xpath(".input//[@id='input-1']"));
		WebElement Last_Name = driver.findElement(By.xpath(".//input[@id='input-2']"));
		WebElement Email = driver.findElement(By.xpath(".//input[@id='input-3']"));
		WebElement Company = driver.findElement(By.xpath(".//input[@id='input-5']"));
		WebElement Phone = driver.findElement(By.xpath(".//input[@id='input-4']"));
		WebElement Comments= driver.findElement(By.xpath(".//textarea[@id='00N3600000LNxBv']"));
		WebElement Submit= driver.findElement(By.name("submit"));
		
		
		
		//Sending value to the text fields
		First_Name.sendKeys("Sofiane");
		Last_Name.sendKeys("Amarouche");
		Email.sendKeys("azroubar@gmail.com");
		Company.sendKeys("OnetrustAutomation");
		Phone.sendKeys("404-503-5957");
		Comments.sendKeys("This a test");
		
		// To submit the form we can also use the submit() method on any of the webelement that belong to 
		//the same form than the submit button
		Submit.click();
		}
	 
	 @AfterMethod
	 public void TakingScreenShot() throws IOException{
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screenshot into a folder named ScreenShot under C drive with the file name "screenshot.png"
            FileUtils.copyFile(scrFile, new File("C:\\ScreenShot\\Test_screenshot.png")); 
		 
	 }
	
	// This method will close the current active windows
	@AfterClass
	public void close_browser(){
		driver.quit();
		
	}
}
