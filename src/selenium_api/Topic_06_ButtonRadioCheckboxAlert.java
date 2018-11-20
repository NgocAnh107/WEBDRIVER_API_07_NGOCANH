package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_ButtonRadioCheckboxAlert {
   WebDriver driver;
	
	
	
  @BeforeClass
  public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  //@Test
	public void TC_01_HandleButton() {
	  driver.get("http://live.guru99.com/");
	 driver.findElement(By.xpath("//div[@ class=\"footer\"]//a[@title=\"My Account\"]"));
	 Assert.assertEquals(driver.getCurrentUrl(),  "http://live.guru99.com/index.php/customer/account/login/");
	  
	 
	WebElement element = driver.findElement(By.xpath("//a[@title=\"Create an Account\"]"));
	 //click vào Create an Account
	Common.clickElementByJavascript(element, driver);
	 Assert.assertEquals(driver.getCurrentUrl(),  "http://live.guru99.com/index.php/customer/account/create/");
	}
 // @Test
  public void TC_02_Checkbox_RadioButton_Html() {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	  // click to Dual-zone air conditioning 
	WebElement dualZoneCheckbox =  driver.findElement(By.xpath("//label[text()=\"Dual-zone air conditioning\"]"));
	 dualZoneCheckbox.click();
	 Assert.assertTrue(dualZoneCheckbox.isSelected());
	 //uncheck
	 dualZoneCheckbox.click();
	 // verify uncheck
	 Assert.assertFalse(dualZoneCheckbox.isSelected());

	  
  }
 // @Test
  public void TC_03_Checkbox_Custom() throws InterruptedException {
	 driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	  // click to Dual-zone air conditioning 
	WebElement dualZoneCheckbox =  driver.findElement(By.xpath("//label[text()=\"Dual-zone air conditioning\"]/preceding-sibling::input"));
	 /* dualZoneCheckbox.click();
	 Thread.sleep(1000);
	 Assert.assertTrue(dualZoneCheckbox.isSelected());
	 //uncheck
	 dualZoneCheckbox.click();
	 // verify uncheck
	  
	 Assert.assertFalse(dualZoneCheckbox.isSelected());*/
	Common.clickElementByJavascript(dualZoneCheckbox, driver);
	 Assert.assertTrue(dualZoneCheckbox.isSelected());
	 //uncheck for checkbox 
	 Common.clickElementByJavascript(dualZoneCheckbox, driver);
	 Assert.assertFalse(dualZoneCheckbox.isSelected());

	  
  }
 // @Test
  public void TC_04_Alert() throws InterruptedException {
	  Alert alert;
	  String name ="automation Testing";
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  By resultMessage = By.xpath("//p[@id='result']");
	  driver.findElement(By.xpath("//button[text()=\"Click for JS Alert\"]")).click();
	 alert = driver.switchTo().alert();
	  Thread.sleep(1000);
	 Assert.assertEquals(alert.getText(), "I am a JS Alert");
	 alert.accept(); // accept alert
	 
	 //verify message
	 Assert.assertEquals(driver.findElement(resultMessage).getText(), "You clicked an alert successfully");
	 // pratice 02
	 driver.findElement(By.xpath("//button[text()=\"Click for JS Confirm\"]")).click();
	 alert = driver.switchTo().alert();
	  Thread.sleep(1000);
	 Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	 alert.dismiss(); // cancel alert
	 
	 //verify message
	 Assert.assertEquals(driver.findElement(resultMessage).getText(), "You clicked: Cancel");
	 
	 // pratice 03
	 driver.findElement(By.xpath("//button[text()=\"Click for JS Prompt\"]")).click();
	 alert = driver.switchTo().alert();
	  Thread.sleep(1000);
	 Assert.assertEquals(alert.getText(), "I am a JS prompt");
	 alert.sendKeys(name); // senkey
	 alert.accept(); 
	 
	 //verify message
	 Assert.assertEquals(driver.findElement(resultMessage).getText(), "You entered: " + name);
	 
	  
  }
  @Test
  public void TC_05_AuthenticationAlert() {
	  driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	  //username:pass@domain thì sẽ tự động input username và pass vào form alert
	  Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),\"Congratulations! You must have the proper credentials.\")]")).isDisplayed());
  }

  @AfterClass
  public void afterClass() {
	 // driver.quit();
  }
 

}
