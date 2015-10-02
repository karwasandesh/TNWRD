package CMCell;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Data.Links;

public class Login  {
 
	WebDriver driver ;
	
	

	@BeforeMethod
	public void beforeMethod() throws Exception{
	
		driver = new FirefoxDriver();
		//driver = new HtmlUnitDriver();
		driver.get(Links.BaseUrl+Links.CMCellURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("loginId")).clear();
		driver.findElement(By.id("LoginSubmit_password")).clear();
	}
	
	@Test
	public void TestCaseNo001(){
	
		String Expeceted = "EIMS: cmcell:";
		String Actual = driver.getTitle();
		Assert.assertEquals(Actual, Expeceted, "LoginPage should opened");
	}
	
	@Test
	public void TestCaseNo002(){
			driver.findElement(By.className("button")).click();
			String Expected = "The Following Errors Occurred:\nusername was empty reenter\npassword was empty reenter";	
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String Actual = driver.findElement(By.className("content-messages errorMessage")).getText();
			
			Assert.assertEquals(Actual, Expected);
	}
	
	@Test
	public void TestCaseNo003(){
			driver.findElement(By.id("loginId")).sendKeys("username");
			driver.findElement(By.className("button")).click();
			String Expected = "The Following Errors Occurred:\npassword was empty reenter";
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String Actual = driver.findElement(By.className("content-messages errorMessage")).getText();//driver.findElement(By.id("messages-container")).getText();
			Assert.assertEquals(Actual, Expected);
		}
	
	@Test
	public void TestCaseNo004(){
			driver.findElement(By.id("LoginSubmit_password")).sendKeys("password");
			driver.findElement(By.className("button")).click();
			String Expected = "The Following Errors Occurred:\nusername was empty reenter";		
			String Actual = driver.findElement(By.className("content-messages errorMessage")).getText();
			Assert.assertEquals(Actual, Expected);
	}
	

	@Test
	public void TestCaseNo005() throws Exception{
			String expectedError = "following error occurred during login: Password incorrect.";
			driver.findElement(By.id("loginId")).sendKeys("10000");
			driver.findElement(By.id("LoginSubmit_password")).sendKeys("Pass");
			driver.findElement(By.className("button")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assert.assertEquals(driver.findElement(By.className("content-messages errorMessage")).getText(), expectedError);
			
	}
	
	@Test
	public void TestCaseNo006() throws Exception{
		String expectedError = "following error occurred during login: User not found.";
		driver.findElement(By.id("loginId")).sendKeys("User");
		driver.findElement(By.id("LoginSubmit_password")).sendKeys("password");
		driver.findElement(By.className("button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.className("content-messages errorMessage")).getText(), expectedError);
	}
	
	@Test
	public void TestCaseNo007() throws Exception{
		String expectedError = "following error occurred during login: User not found.";
		driver.findElement(By.id("loginId")).sendKeys("User");
		driver.findElement(By.id("LoginSubmit_password")).sendKeys("Pass");
		driver.findElement(By.className("button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.className("content-messages errorMessage")).getText(), expectedError);
			
	}
	
	
	
	@Test
	public void TestCaseNo008() throws Exception{
		String expectedTitle = "EIMS: cmcell: CM Cell Petition Details";
		driver.findElement(By.id("loginId")).sendKeys("MS00N12345");
		driver.findElement(By.id("LoginSubmit_password")).sendKeys("password@12345");
		driver.findElement(By.className("button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	

	  @AfterMethod(alwaysRun=true)
	  public void catchExceptions(ITestResult result){
	      Calendar calendar = Calendar.getInstance();
	      SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	      String methodName = result.getName();
	      if(!result.isSuccess()){
	          File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	          String FileName = "D:/selenium/EIMS/ScreenShot/CMCell/"+methodName+" "+formater.format(calendar.getTime())+".png";
	          try {
	              FileUtils.copyFile(scrFile, new File(FileName));
	              
	              driver.close();
	          } catch (IOException e1) {
	              e1.printStackTrace();
	          }
	      }
	      else
	    	  driver.close();
	  }
	  
	  



	
	
}
