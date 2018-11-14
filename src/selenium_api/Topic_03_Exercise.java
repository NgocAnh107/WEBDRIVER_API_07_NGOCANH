package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Exercise {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18ByRadio = By.xpath("//input[@id='under_18']");
	By educationByTextArea = By.xpath("//textarea[@id='edu']");
	By jobRole01 = By.xpath("//select[@id='job1']");
	By interests = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonIsEnable = By.xpath("//button[@id='button-enabled']");
	//
	By password = By.xpath("//input[@id='password']");
	By ageRadioButton = By.xpath("//input[@id='radio-disabled']");
	By biography = By.xpath("//textarea[@id='bio']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By interestsCheckboxisDisabled = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonIsDisable = By.xpath("//button[@id='button-disabled']");
	
	
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
		//mở app
		driver.get(" https://daominhdam.github.io/basic-form/index.html");
		//phóng to trình duyệt
		driver.manage().window().maximize();
		//wait element tìm thấy trong 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Test
  public void TC_01_IsDisplay() {
	 /* if(driver.findElement(emailTextbox).isDisplayed()) {
		  driver.findElement(emailTextbox).sendKeys("Automation Testing");
		  
	  }
	  if(driver.findElement(educationByTextArea).isDisplayed()) {
		  driver.findElement(educationByTextArea).sendKeys("Automation Testing");
		  
	  }
	  if(driver.findElement(ageUnder18ByRadio).isDisplayed()) {
		  driver.findElement(ageUnder18ByRadio).click();
		  
	  }*/
	  if(isControlDisplay(emailTextbox)) {
		  driver.findElement(emailTextbox).sendKeys("Automation Testing");
	  }
	  if(isControlDisplay(ageUnder18ByRadio)) {
		  driver.findElement(ageUnder18ByRadio).click();
	  }
	  if(isControlDisplay(educationByTextArea)) {
		  driver.findElement(educationByTextArea).sendKeys("Automation Testing");
	  }
	  
  }
  @Test
  public void TC_02_IsEnableAndIsDisable() {
	  //enable
	  Assert.assertTrue(isControlEnable(ageUnder18ByRadio));
	  Assert.assertTrue(isControlEnable(emailTextbox));
	  Assert.assertTrue(isControlEnable(educationByTextArea));
	  Assert.assertTrue(isControlEnable(jobRole01));
	  Assert.assertTrue(isControlEnable(interests));
	  Assert.assertTrue(isControlEnable(slider01));
	  Assert.assertTrue(isControlEnable(buttonIsEnable));
	  //disable
	  Assert.assertFalse(isControlEnable(password));
	  Assert.assertFalse(isControlEnable(ageRadioButton));
	  Assert.assertFalse(isControlEnable(biography));
	  Assert.assertFalse(isControlEnable(jobRole02));
	  Assert.assertFalse(isControlEnable(interestsCheckboxisDisabled));
	  Assert.assertFalse(isControlEnable(slider02));
	  Assert.assertFalse(isControlEnable(buttonIsDisable));
	  
	  
	 
  }
  @Test
  public void TC_03_IsSelected() {
	  driver.findElement(ageUnder18ByRadio).click();
	 driver.findElement(interests).click();
	 
	  Assert.assertTrue(isControlSelected(ageUnder18ByRadio));
	  Assert.assertTrue(isControlSelected(interests));
	  //bỏ chọn checkbox
	  driver.findElement(interests).click();
	  Assert.assertFalse(isControlSelected(interests));
	  
	  if(!isControlSelected(interests)) {
		driver.findElement(interests).click();
		
	}
	
	  
	  
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public boolean isControlDisplay(By by) {
	  WebElement element = driver.findElement(by);
	  if(element.isDisplayed()) {
		  System.out.println(" Elemet " +by + " is display");
		  return true;
	  }else {
		  System.out.println(" Elemet [" +by + " is not  display");
		  return false;
	  }
  }
  public boolean isControlSelected(By by) {
	  WebElement element = driver.findElement(by);
	  if(element.isSelected()) {
		  System.out.println(" Elemet " +by + "is Selected");
		  return true;
	  }else {
		  System.out.println(" Elemet " +by + "is not  Selected");
		  return false;
	  }
  }
  public boolean isControlEnable(By by) {
	  WebElement element = driver.findElement(by);
	  if(element.isEnabled()) {
		  System.out.println(" Elemet " +by + "is Enable ");
		  return true;
	  }else {
		  System.out.println(" Elemet " +by + "is not Enable ");
		  return false;
	  }
  }

}
