package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath {
   WebDriver driver;
	
	
	
  @BeforeClass
  public void beforeClass() {
	// Chrome
	//System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		//driver = new ChromeDriver();

		// Firefox
		driver = new FirefoxDriver();
		//mở app
		driver.get("http://live.guru99.com");
		//phóng to trình duyệt
		driver.manage().window().maximize();
		//wait element tìm thấy trong 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Test
	public void TC_01_CheckUrlAndTitle() {
	  driver.get("http://live.guru99.com");
		//step 02 kiểm tra title "home page"
	 String homePageTitle = driver.getTitle();
	 Assert.assertEquals(homePageTitle, "Home page");
	// Assert.assertTrue(homePageTitle.equals("Home Page"));
	//
	 ///Assert.assertTrue(homePageTitle.contains("Home page"));
	 //step 03 click vào link "MY account"
	 driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	 driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	 //back lại trang trước
	 driver.navigate().back();
	 String loginUrl=driver.getCurrentUrl();
	 Assert.assertEquals(loginUrl,"http://live.guru99.com/index.php/customer/account/login/" );
	 //forward tới trang 
	 driver.navigate().forward();
	 String createAcc = driver.getCurrentUrl();
	 Assert.assertEquals(createAcc,"http://live.guru99.com/index.php/customer/account/create/" );
	 
	}
  @Test
 	public void TC_02_EmailandPasswordEmpty() {
	  driver.get("http://live.guru99.com");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  // để trong usename  pass
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
	  //click login button
	  driver.findElement(By.id("send2")).click();
	  String emailErrorMess =driver.findElement(By.id("advice-required-entry-email")).getText();
	  Assert.assertEquals(emailErrorMess, "This is a required field.");
	  String PassErrorMess =driver.findElement(By.id("advice-required-entry-pass")).getText();
	  Assert.assertEquals(PassErrorMess, "This is a required field.");
 		
 	}
  @Test
 	public void TC_03_EmailInvalid() {
	  String emailInvalid = "123123@123.123";
	  driver.get("http://live.guru99.com");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailInvalid);
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  String errMessage = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
	  Assert.assertEquals(errMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	  
 		
 	}
  @Test
 	public void TC_04_EmailCorrectAndPassLessThanSixCharacter() {
	  String emailCorrect = "automation@gmail.com";
	  driver.get("http://live.guru99.com");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailCorrect);
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  String errMessagePass = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
	  Assert.assertEquals(errMessagePass, "Please enter 6 or more characters without leading or trailing spaces.");
 		
 	}
  @Test
 	public void TC_05_CheckUrlAndTitle() {
	  String emailCorrect = "automation@gmail.com";
	  driver.get("http://live.guru99.com");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailCorrect);
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  Assert.assertTrue( driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).isDisplayed());
 		
 	}
  @Test
	public void TC_07_CheckUrlAndTitle() {
	 
	}
  @Test
 	public void TC_06_RegisterToSystem() {
	  String firstname="selenium ", lastname="online07" , password = "123123" , email ="seleniumonline" + randomEmail() + "@gmail.com" ;
	  driver.get("http://live.guru99.com");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	  driver.findElement(By.id("firstname")).sendKeys(firstname);
	  driver.findElement(By.id("lastname")).sendKeys(lastname);
	  driver.findElement(By.name("email")).sendKeys(email);
	  driver.findElement(By.className("validate-password")).sendKeys(password);
	  driver.findElement(By.className("validate-cpassword")).sendKeys(password);
	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	 
	  Assert.assertTrue( driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
	  //logout
	  driver.findElement(By.xpath("//div[@class='page-header-container']//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	  Assert.assertTrue( driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
 	}
  //anotation của testng => chạy một lần cuối cùng cho tất cả testcase
  @AfterClass
  public void afterClass() {
	  //Tắt browser
	  driver.quit();
  }
  public int randomEmail() {
	  Random random = new Random();
	  int number = random.nextInt(9999999);
	  return number;
  }
 

}
