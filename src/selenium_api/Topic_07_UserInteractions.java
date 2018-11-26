package selenium_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_UserInteractions {
   WebDriver driver;
  Actions actions ;
	//JavascriptExecutor javaExecutor ; // scroll
	
	
	
  @BeforeClass
  public void beforeClass() {
	  
		driver = new FirefoxDriver();
		actions = new Actions(driver);
		 //javaExecutor =(JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
 @Test
  public void TC_01_MoveToElement() {
	  driver.get("http://www.myntra.com/");
	  WebElement moveElement=driver.findElement(By.xpath("//div[@class=\"desktop-userIconsContainer\"]"));
	  actions.moveToElement(moveElement).perform();
	  
	  driver.findElement(By.xpath("//a[text()='login']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Login to Myntra\"]")).isDisplayed());
	  
	  
  }
 @Test
  public void TC_02_ClickAndHold() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  List<WebElement> clickAndHoldElement = driver.findElements(By.xpath("//li[@class=\"ui-state-default ui-selectee\"]"));
	  actions.clickAndHold(clickAndHoldElement.get(0)).moveToElement(clickAndHoldElement.get(3)).release().perform();
	  List<WebElement> selectedElement= driver.findElements(By.xpath("//li[@class=\"ui-state-default ui-selectee ui-selected\"]"));
	  Assert.assertEquals(selectedElement.size(), 4);
	  
	  
	  
	  
  }
   @Test
	public void TC_03_DoubleClick() {
		driver.get("http://www.seleniumlearn.com/double-click");
		
		WebElement doubleElement = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		 //javaExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleElement);
		
		actions.doubleClick(doubleElement).perform();
		Alert alert =driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		alert.accept();
		
		
	
	}
  @Test
  public void TC_04_RightClick() {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  WebElement rightClickElement = driver.findElement(By.xpath("//span[text()=\"right click me\"]"));
	  //right click
	  actions.contextClick(rightClickElement).perform();
	  //hover
	  WebElement quitBefore = driver.findElement(By.xpath("//li//span[text()=\"Quit\"]"));
	  actions.moveToElement(quitBefore).perform();
	  // verify
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']")).isDisplayed());
	  actions.click(quitBefore).perform();
	  //
	  Alert alert = driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(), "clicked: quit");
	  alert.accept();
	  
	  
  }
  @Test
  public void TC_05_DragAndDrop() {
	  driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
	  WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
	  actions.dragAndDrop(sourceElement, targetElement).build().perform();
	  Assert.assertTrue(targetElement.getText().equals("You did great!"));
  }
  
  @Test
  public void TC_06_Drag_Drop_HTML5_Xpath() throws AWTException, Exception {
		driver.get("https://html5demos.com/drag/");

		String oneXpath = "//a[@id='one']";
		String twoXpath = "//a[@id='two']";
		String threeXpath = "//a[@id='three']";
		String fourXpath = "//a[@id='four']";
		String fiveXpath = "//a[@id='five']";
		String targetXpath = "//div[@id='bin']";
		
		drag_the_and_drop_html5_by_xpath(oneXpath, targetXpath);
		Thread.sleep(2000);
		
		drag_the_and_drop_html5_by_xpath(twoXpath, targetXpath);
		Thread.sleep(2000);
		drag_the_and_drop_html5_by_xpath(threeXpath, targetXpath);
		Thread.sleep(2000);
		drag_the_and_drop_html5_by_xpath(fourXpath, targetXpath);
		Thread.sleep(2000);
		drag_the_and_drop_html5_by_xpath(fiveXpath, targetXpath);
		
		
	}
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		org.openqa.selenium.Dimension sourceSize = source.getSize();
		org.openqa.selenium.Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		org.openqa.selenium.Point sourceLocation = source.getLocation();
		org.openqa.selenium.Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 0 + xCentreSource;
		sourceLocation.y += 70 + yCentreSource;
		targetLocation.x += 0 + xCentreTarget;
		targetLocation.y += 70 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
  
  @AfterClass
  public void afterClass() {
	  
	 
  }
 

}
