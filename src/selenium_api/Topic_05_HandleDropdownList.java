package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.RenderingHints.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class Topic_05_HandleDropdownList {
	WebDriver driver;
	WebDriverWait waitExplicit ;
	JavascriptExecutor javaExecutor ; // scroll

  @BeforeClass
  public void beforeClass() {
	 
	  driver = new FirefoxDriver();
	waitExplicit = new WebDriverWait(driver, 30);
	 javaExecutor =(JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Test
  public void TC_01_HTMLDropdown() throws InterruptedException {
	  /*<select id="job1" name="user_job1">
	  <option value="automation">Automation Tester</option>
	  <option value="manual">Manual Tester</option>
	  <option value="website">Website Tester</option>
	  <option value="mobile">Mobile Tester</option>
	  <option value="disabled" disabled="disabled">Dropdown disable</option>
	  </select>*/
	  	driver.get(" https://daominhdam.github.io/basic-form/index.html");
		Select select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		//select.selectByIndex(1); không nên dùng
		//select.selectByValue("automation");
		select.selectByVisibleText("Automation Tester"); // stable
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		Thread.sleep(1000);
		//
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		Thread.sleep(1000);
		//
		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		//hỗ trợ multi-select
		//Assert.assertTrue(select.isMultiple());
		// không hỗ trợ multi-select
		Assert.assertFalse(select.isMultiple());
		//
		Assert.assertEquals(select.getOptions().size(),5 );
		
  }
  @Test
  public void TC_02_JqueryDropdown() throws InterruptedException {
	  //jquery
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	  selectItemCustomDropdown("//div[@class='demo']","//span[@id='number-button']", "//ul[@id=\"number-menu\"]//li[@class=\"ui-menu-item\"]/div", "19");
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class=\"ui-selectmenu-text\" and text()='19']")).isDisplayed());
	  Thread.sleep(1000);
	  selectItemCustomDropdown("//div[@class='demo']","//span[@id='number-button']", "//ul[@id=\"number-menu\"]//li[@class=\"ui-menu-item\"]/div", "3");
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class=\"ui-selectmenu-text\" and text()='3']")).isDisplayed());
	  Thread.sleep(1000);
	  /////kendo UI
	  driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index ");
	  selectItemCustomDropdown("//div[@id='cap-view']","//span[@aria-owns='color_listbox']", "//ul[@id=\"color_listbox\"]//li", "Orange");
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Orange']")).isDisplayed());
	  Thread.sleep(1000);
	  // angular
	  driver.get("https://material.angular.io/components/select/examples");
	
	  selectItemCustomDropdown("//div[text()='Select with reset option']","//mat-select[@placeholder=\"State\"]", "//mat-option//span", "Washington");
	
	  Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@placeholder=\"State\"]//span[text()=\"Washington\"]")).isDisplayed());
	  Thread.sleep(1000);
	  //vue js
	  driver.get("https://mikerodham.github.io/vue-dropdowns/");
	  selectItemCustomDropdown("//div[@id='app']/p", "//div[@id='app']/div/li", "//ul[@class=\"dropdown-menu\"]//li", "Second Option");
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='app']//div//li[contains(text(),\"Second Option\")]")).isDisplayed());
	  Thread.sleep(1000);
	 
	  //editable
	  driver.get("http://indrimuska.github.io/jquery-editable-select/");
	  driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("Audi");
	 driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys(Keys.TAB);
	  Thread.sleep(1000);
	  Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='default-place']/ul/li[text()=\"Audi\"]")).getAttribute("class"), "es-visible selected");
	  Thread.sleep(1000);
	  
	  
  }
  public void selectItemCustomDropdown(String scrollToXpath,String parentXpath , String childXpath , String expectedItem) {
	  /*Click vào dropdown
	  Wait để tất cả phần tử trong dropdown được hiển thị
	  Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
	  Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
	  Nếu actual text = expected text thì click vào phần tử đó và break khỏi vòng lặp*/


	  WebElement elemment = driver.findElement(By.xpath(parentXpath));
	  WebElement elemmentToXpath = driver.findElement(By.xpath(scrollToXpath));
	  //scroll tới element cha
	  javaExecutor.executeScript("arguments[0].scrollIntoView(true);", elemmentToXpath);
	  //
	  elemment.click();
	   // Lấy ra tất các item trong dropdown
	  List<WebElement> childList = driver.findElements(By.xpath(childXpath));
	// wait cho đén khi tất cả các phần tử trong dropdown được hiển thị
	  waitExplicit.until(ExpectedConditions.visibilityOfAllElements(childList));
	  
	  for(WebElement child : childList) {
		  String textItem = child.getText().trim(); // trim xóa khoảng trắng ơ dầu cuối
		  System.out.println("Text un dropdown :" + textItem);
		  if(textItem.equals(expectedItem)) {
			  //sroll tới expected item để click(vì có nhiều page k visible thì k click đc)
			  javaExecutor.executeScript("arguments[0].scrollIntoView(true);", child);
			  child.click();
			  break;
		  }
	  }
  }

  @AfterClass
  public void afterClass() {
  }

}
