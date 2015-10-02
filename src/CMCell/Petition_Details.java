package CMCell;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Data.Links;

public class Petition_Details {

	WebDriver driver;
	
	
	@BeforeClass
	public void openBrowser(){
	
	}
	
	
	@BeforeMethod
	public void login()
	{	
		driver = new FirefoxDriver();
		driver.get(Links.BaseUrl+Links.CMCellURL);
		driver.manage().window().maximize();
		driver.get(Links.BaseUrl+Links.CMCellURL);
		driver.findElement(By.id("loginId")).sendKeys("MS00N12345");
		driver.findElement(By.id("LoginSubmit_password")).sendKeys("password@12345");
		driver.findElement(By.className("button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	}

	@Test
	public void testCaseNo001(){
		driver.findElement(By.cssSelector("body > div.contentarea > form:nth-child(2) > div > div.screenlet-body > table > tbody > tr:nth-child(2) > td:nth-child(2) > center > a")).click();;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement table = driver.findElement(By.className("basic-table"));
		System.out.println(table.getText());
		System.out.println(table.getTagName());
		Assert.assertEquals(table.getText().contains("CM Petition12"), true);
				
	}
	
/*	@Test
	public void testCaseNo002(){
		System.out.println("in test case 2");
		
	}*/
	
	
	@AfterMethod(alwaysRun=true)
	  public void catchExceptions(ITestResult result){
		System.out.println("in after method");
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
