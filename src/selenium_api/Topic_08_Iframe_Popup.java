package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Iframe_Popup {
   WebDriver driver;
	
	
	
  @BeforeClass
  public void beforeClass() {
	
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Test
	public void TC_01_IFrame() {
	  driver.get("http://www.hdfcbank.com/");
	  // selenium chir thao tác với những element được hiển thị (Visible)
	List<WebElement> notification =  driver.findElements(By.xpath("//div[@id='container-div']/img"));
	// k dùng findelement vì nếu k tìm thấy popup sẽ fail testcase
	
	if(notification.size()>0) {
		driver.switchTo().frame(notification.get(0));
		
		Common.clickElementByJavascript(driver.findElement(By.cssSelector("#div-close")), driver);
		// switch Topwindows
		driver.switchTo().defaultContent();
	}
	WebElement lookingForIframe =driver.findElement(By.xpath("//div[@class=\"flipBannerWrap\"]//iframe"));
	driver.switchTo().frame(lookingForIframe);
	
	String messageText =driver.findElement(By.xpath("//span[@id='messageText']")).getText();
	Assert.assertEquals(messageText, "What are you looking for?");
	driver.switchTo().defaultContent();
	//
	
	WebElement slideIframe =driver.findElement(By.xpath("//div[@class=\"slidingbanners\"]//iframe"));
	driver.switchTo().frame(slideIframe);
	List<WebElement> slide =  driver.findElements(By.xpath("//img[@class=\"bannerimage\"]"));
	
	Assert.assertEquals(slide.size(), 6);
	//
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"flipBanner\"]")).isDisplayed());
	//
	List<WebElement> img =  driver.findElements(By.xpath("//div[@class=\"flipBanner\"]//img[@class=\"front icon\"]"));
	Assert.assertEquals(img.size(), 8);
	
	for(WebElement m : img) {
		System.out.println(m.isDisplayed());
		Assert.assertTrue(m.isDisplayed());
	}

	}
  @AfterClass
  public void afterClass() {
	  
  }
 

}
