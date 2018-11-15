package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_HandleTextBoxTextArea {
	WebDriver driver;
	private String newName, newDob, newAddress, newCity, newSatus, newPin, newPhone, newEmail, password;
	private String editAddress, editCity, editSatus, editPin, editPhone, editEmail , customerID;

	By nameByTextBox = By.xpath("//input[@name='name']");
	By dobByTextBox = By.xpath("//input[@id='dob']");
	By addressByTextarea = By.xpath("//textarea[@name='addr']");
	By cityByTextBox = By.xpath("//input[@name='city']");
	By stateByTextBox = By.xpath("//input[@name='state']");
	By pinnoByTextBox = By.xpath("//input[@name='pinno']");
	By telephonenoByTextBox = By.xpath("//input[@name='telephoneno']");
	By emailidByTextBox = By.xpath("//input[@name='emailid']");
	By passwordByTextBox = By.xpath("//input[@name='password']");
	By submitByButton = By.xpath("//input[@name='sub']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		newName = "automationTes";
		newDob = "2000-10-11";
		newAddress = "123 hue";
		newCity = "hue";
		newSatus = "sa pa";
		newPin = "123456";
		newPhone = "09789452325";
		newEmail = "autotest" + Common.randomEmail() + "@gmail.com";
		password = "123123";

		editAddress = "123 DN";
		editCity = "DN";
		editSatus = "Sai gon";
		editPin = "11111";
		editPhone = "0975852445";
		editEmail = "edittest" + Common.randomEmail() + "@gmail.com";
	}

	@Test
	public void TC_01_NewCustomer() {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr161493");
		
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("harErAh");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		driver.findElement(By.xpath("//a[text()=\"New Customer\"]")).click();

		driver.findElement(nameByTextBox).sendKeys(newName);
		driver.findElement(dobByTextBox).sendKeys(newDob);
		driver.findElement(addressByTextarea).sendKeys(newAddress);
		driver.findElement(cityByTextBox).sendKeys(newCity);
		driver.findElement(stateByTextBox).sendKeys(newSatus);
		driver.findElement(pinnoByTextBox).sendKeys(newPin);
		driver.findElement(telephonenoByTextBox).sendKeys(newPhone);
		driver.findElement(emailidByTextBox).sendKeys(newEmail);
		driver.findElement(passwordByTextBox).sendKeys(password);
		driver.findElement(submitByButton).click();
		//get customer ID
	customerID =	driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		// verify input data matching với output data after create new customer
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), newName);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), newDob);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newAddress);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newCity);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), newSatus);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), newPin);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), newPhone);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), newEmail);
	
	
	}
	@Test
	public void TC_02_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);;
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		// verify name / address matching vs inputdata
		Assert.assertEquals(driver.findElement(nameByTextBox).getAttribute("value"), newName);
		Assert.assertEquals(driver.findElement(addressByTextarea).getText(), newAddress);
		// edit
		driver.findElement(addressByTextarea).clear();;
		driver.findElement(addressByTextarea).sendKeys(editAddress);
		driver.findElement(cityByTextBox).clear();
		driver.findElement(cityByTextBox).sendKeys(editCity);
		driver.findElement(stateByTextBox).clear();
		driver.findElement(stateByTextBox).sendKeys(editSatus);
		driver.findElement(pinnoByTextBox).clear();
		driver.findElement(pinnoByTextBox).sendKeys(editPin);
		driver.findElement(telephonenoByTextBox).clear();
		driver.findElement(telephonenoByTextBox).sendKeys(editPhone);
		driver.findElement(emailidByTextBox).clear();
		driver.findElement(emailidByTextBox).sendKeys(editEmail);
		driver.findElement(submitByButton).click();
		//
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editSatus);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
		
	}

	@AfterClass
	public void afterClass() {
	}

}
