package selenium_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Wait {
   WebDriver driver;
   WebDriverWait waitExplicit;
	Date date;
	
	
  @BeforeClass
  public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new  WebDriverWait(driver, 30);
		driver.manage().window().maximize();
	
		
  }
  //@Test
	public void TC_01_ImplicitWait() {
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  WebElement startButton = driver.findElement(By.xpath("//*[@id='start']/button"));
	  startButton.click();
	  Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Hello World!\"]")).isDisplayed());
	}
  //@Test
 	public void TC_02_ExplicitWait() {
 	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
 	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
 	  // trước khi thoa tác với một element thì nên sử dụng wait explicit như 1 pre- condition
 	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='start']/button")));
 	  WebElement startButton = driver.findElement(By.xpath("//*[@id='start']/button"));
 	  startButton.click();
 	  // presence : có trong Dom -> pass
 	  waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='loading']/img")));
 	  
 	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']/img")));
 	  Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Hello World!\"]")).isDisplayed());
 	}
  
// @Test
	public void TC_03_ExplicitWait_visible() {
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  // trước khi thoa tác với một element thì nên sử dụng wait explicit như 1 pre- condition
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='start']/button")));
	  WebElement startButton = driver.findElement(By.xpath("//*[@id='start']/button"));
	  startButton.click();
	  
	  waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[text()=\"Hello World!\"]")));
	  Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Hello World!\"]")).isDisplayed());
	}
  @Test
	public void TC_04_ExplicitWait() {
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  System.out.println("--Start time check hello world invisible not in DOM----");
	  System.out.println(date = new Date());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h4[text()=\"Hello World!\"]")));
	  System.out.println("--End time check invisible not in DOM----");
	  System.out.println(date = new Date());
	  
	  
	  System.out.println("--Start time check loading invisible not in DOM----");
	  System.out.println(date = new Date());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']/img")));
	  System.out.println("--time start check invisible not in DOM----");
	  System.out.println(date = new Date());
	  
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='start']/button")));
 	  WebElement startButton = driver.findElement(By.xpath("//*[@id='start']/button"));
 	  startButton.click();
 	  
 	  
 	  System.out.println("--Start time check loading invisible in DOM----");
	  System.out.println(date = new Date());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']/img")));
	  System.out.println("--time start check invisible in DOM----");
	  System.out.println(date = new Date());
 	  
	  
	  
	/* 
	  waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[text()=\"Hello World!\"]")));
	  Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Hello World!\"]")).isDisplayed());*/
	}
  
  @AfterClass
  public void afterClass() {
	
  }
 

}
