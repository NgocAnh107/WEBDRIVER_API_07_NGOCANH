package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_JavaScriptExcutor {
   WebDriver driver;
	
	
	
  @BeforeClass
  public void beforeClass() {
	
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Test
	public void TC_01_CheckTitle() {
		driver.get("http://live.guru99.com/");
		String domainName = (String )executeForBrowser("return document.domain");
		Assert.assertEquals(domainName, "live.guru99.com");
		
		String homePageURL = (String )executeForBrowser("return document.URL");
		Assert.assertEquals(homePageURL, "http://live.guru99.com/");
		
		WebElement mobilePageLink = driver.findElement(By.xpath("//a[text()=\"Mobile\"]"));
		clickToElementByJS(mobilePageLink);
		
		WebElement samsungGalaButton = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class=\'actions\']/button"));
		clickToElementByJS(samsungGalaButton);
		
		String pageText = (String ) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(pageText.contains("Samsung Galaxy was added to your shopping cart."));
		
		WebElement privacyLink = driver.findElement(By.xpath("//a[text()=\"Privacy Policy\"]"));
		clickToElementByJS(privacyLink);
		
		String privacyTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(privacyTitle, "Privacy Policy");
		scrollToBottomPage();
		 WebElement wishListRow = driver.findElement(By.xpath("//th[text()=\"WISHLIST_CNT\"]/following-sibling::td[text()=\"The number of items in your Wishlist.\"]"));
		 Assert.assertTrue(wishListRow.isDisplayed());
		 navigateToUrlByJS("http://demo.guru99.com/v4/");
		 
		 String verifyDomain = (String )executeForBrowser("return document.domain");
			Assert.assertEquals(verifyDomain, "demo.guru99.com");
	}
  @Test
  public void TC_02() {
	  driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
	  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id=\"iframeResult\"]")));
	  
	  WebElement lastNameTxt = driver.findElement(By.xpath("//input[@name=\"lname\"]"));
	  WebElement firstNameTxt = driver.findElement(By.xpath("//input[@name=\"fname\"]"));
	  WebElement submit = driver.findElement(By.xpath("//input[@value=\"Submit\"]"));
	  //  Last name: <input type="text" name="lname" disabled><br>
	  removeAttributeInDOM(lastNameTxt, "disabled");
	  
	  sendkeyToElementByJS(firstNameTxt, "automation");
	  sendkeyToElementByJS(lastNameTxt, "07");
	  
	  clickToElementByJS(submit);
	  WebElement testResult = driver.findElement(By.xpath("//h2[text()='Your input was received as:']/following-sibling::div[contains(text(),\"fname\")]"));
	  Assert.assertTrue(testResult.getText().contains("automation") && testResult.getText().contains("07"));
	  
		
  }
  @Test
  public void TC_03() {
	  driver.get("http://live.guru99.com/");
	  String firstName ="Automation ";
	  String lastName ="tets 07";
	  String email = "automation" + Common.randomEmail()+ "@gmail.com";
	  String pass="123456";
	  
	  clickToElementByJS(driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()=\"My Account\"]")));
	  clickToElementByJS(driver.findElement(By.xpath("//a[@title=\"Create an Account\"]")));
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='firstname']")), firstName);
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='lastname']")), lastName);
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='email_address']")), email);
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='password']")), pass);
	   sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='confirmation']")), pass);
	   
	   clickToElementByJS(driver.findElement(By.xpath("//button[@title=\"Register\"]")));
	   
	   String pageText = (String ) executeForBrowser("return document.documentElement.innerText");
	   Assert.assertTrue(pageText.contains(firstName));
		Assert.assertTrue(pageText.contains(lastName));
	  
  }
  @AfterClass
  public void afterClass() {
	  
  }
  public void highlightElement(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].style.border='6px groove red'", element);
  }

  public Object executeForBrowser(String javaSript) {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript(javaSript);
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }

  public Object clickToElementByJS(WebElement element) {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("arguments[0].click();", element);
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }

  public Object sendkeyToElementByJS(WebElement element, String value) {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }

  public Object removeAttributeInDOM(WebElement element, String attribute) {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }

  public Object scrollToBottomPage() {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }

  public Object navigateToUrlByJS(String url) {
      try {
          JavascriptExecutor js = (JavascriptExecutor) driver;
          return js.executeScript("window.location = '" + url + "'");
      } catch (Exception e) {
          e.getMessage();
          return null;
      }
  }


}
