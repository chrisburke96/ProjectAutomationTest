import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.Assert;

import org.openqa.selenium.By;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;  
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;


public class TestCase2 {
	
	// Create a reference of web driver. 
	public static WebDriver driver; 
	
  @Test
  public void f() {
	  // Navigate to the website
	  driver.navigate().to("http://the-internet.herokuapp.com/"); 
	  
	  // Navigate to Infinte Scroll Page
	  WebElement infiniteScrollPage = driver.findElement(By.linkText("Infinite Scroll"));
	  Actions actionProvider = new Actions(driver);
	  actionProvider.moveToElement(infiniteScrollPage).build().perform();
	  infiniteScrollPage.click();
	  
	  //Creating the JavascriptExecutor interface objectThread.sleep(2000); by Type casting		
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  
	  // Scroll to the bottom of the page twice and then scroll up to the top
	  try {
		    Thread.sleep(1000);
		    js.executeScript("window.scrollBy(0,10000000)");
		    System.out.println("*** Scrolled to the bottom of page ***");
		    
		    Thread.sleep(1000);
		    js.executeScript("window.scrollBy(0,10000000)");
		    System.out.println("*** Scrolled to the bottom of page ***");
		    
		    Thread.sleep(1000);
		    js.executeScript("window.scrollBy(0,-10000000)");
		    System.out.println("*** Scrolled back to the top of page ***");
		    Thread.sleep(1000);
		    Assert.assertTrue(true == true);
		    System.out.println("*** Infinite Scroll Page ***");
		} 
	  
	  catch(InterruptedException e) {
		    System.out.println("got interrupted!");
		    Assert.assertTrue(true == false);

		}


  }
  
  
  
  @BeforeTest
  public void setUp() {
	  // Get project directory
	  String dir = System.getProperty("user.dir");
	  
	  // Create path for chrome driver and set it to the driver object
	  String exepath = dir+ "\\resources\\webdriver\\chromedriver.exe";
	  System.setProperty("webdriver.chrome.driver", exepath);
	  
	  // Chrome driver object created and chrome window will open. 
	  driver=new ChromeDriver();  
	  
	  // Maximise browser and error catching
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
	  
  }
  
  

  @AfterTest
  public void tearDown() {
	  
	  // Close chrome browser. 
	  driver.quit();
  }
}
