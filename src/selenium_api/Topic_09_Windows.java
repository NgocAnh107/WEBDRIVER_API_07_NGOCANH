package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Windows {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Windows() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		String parentID = driver.getWindowHandle(); // lấy id cuat tab đag active
		System.out.println("parent ID :" + parentID);
		driver.findElement(By.xpath("//a[text()=\"Click Here\"]")).click();
		switchToWindowById(parentID);
		
		// ktr navigate google success
		Assert.assertEquals(driver.getTitle(), "Google");
		//
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		//
		switchToWindowByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");
	}
	@Test
	public void TC_02_Windows_Title() throws InterruptedException {
		driver.get("http://www.hdfcbank.com/");
		String parentID = driver.getWindowHandle();
		List<WebElement> notification =  driver.findElements(By.xpath("//div[@id='container-div']/img"));
		// k dùng findelement vì nếu k tìm thấy popup sẽ fail testcase
		
		if(notification.size()>0) {
			driver.switchTo().frame(notification.get(0));
			
			Common.clickElementByJavascript(driver.findElement(By.cssSelector("#div-close")), driver);
			// switch Topwindows
			driver.switchTo().defaultContent();
		}
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		
		// switch qUA AGRi tab
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		Assert.assertEquals(driver.getTitle(), "HDFC Bank Kisan Dhan Vikas e-Kendra");
		Thread.sleep(1000);
		//
		driver.findElement(By.xpath("//a[contains(., \"Account Details\")]")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		Assert.assertEquals(driver.getTitle(), "Welcome to HDFC Bank NetBanking");
		Thread.sleep(1000);
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name=\"footer\"]")));
		driver.findElement(By.xpath("//a[text()=\"Privacy Policy\"]")).click();
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		Assert.assertEquals(driver.getTitle(), "HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()=\"CSR\"]")).click();
		switchToWindowByTitle("HDFC BANK - CSR - Homepage");
		Assert.assertEquals(driver.getTitle(), "HDFC BANK - CSR - Homepage");
		closeAllWithoutParentWindows(parentID);
	}
	
// đúng trong trường hợp có duy nhất hai cửa sổ
	public void switchToWindowById(String parentID) {
		// lấy ra id các của sổ đang có
		Set<String> allWindows = driver.getWindowHandles();
		// dùng vòng lặp để kiểm tra
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break; // thoát khỏi vòng lặp k kiểm tra nữa
			}
			
		}
		
	}

	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWithoutParentWindows(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close(); // đóng tab hiện tại
			}
		}
		driver.switchTo().window(parentWindow);
			}
	@AfterClass
	public void afterClass() {
	}

}
