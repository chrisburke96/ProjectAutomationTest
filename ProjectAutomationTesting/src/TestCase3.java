import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;  
import org.openqa.selenium.interactions.Actions;


public class TestCase3 {
	
	// Create a reference of web driver. 
	public static WebDriver driver; 
	
  @Test
  public void pressKeysTest() {
	  
	  // Navigate to the website
	  driver.navigate().to("http://the-internet.herokuapp.com/"); 
	  
	  // Navigate to Key presses on menu
	  WebElement keyPressPage = driver.findElement(By.linkText("Key Presses"));
	  Actions actionProvider = new Actions(driver);
	  actionProvider.moveToElement(keyPressPage).build().perform();
	  keyPressPage.click();
	  
	  String resultString;
	  

	  
	  // Press 4 keys and for each case assert the text generated on the alert
	  try {
		  	// press key 'q' and get the result from element
		    driver.findElement(By.id("target")).sendKeys("q");
		    Thread.sleep(1000);
		    resultString = driver.findElement(By.id("result")).getText();
		    System.out.println(resultString);
		    Thread.sleep(1000);
		    
		    // press key 'w' and get the result from element
		    driver.findElement(By.id("target")).sendKeys("w");
		    Thread.sleep(1000);
		    resultString = driver.findElement(By.id("result")).getText();
		    System.out.println(resultString);
		    Thread.sleep(1000);
		    
		    // press key 'e' and get the result from element
		    driver.findElement(By.id("target")).sendKeys("e");
		    Thread.sleep(1000);
		    resultString = driver.findElement(By.id("result")).getText();
		    System.out.println(resultString);
		    Thread.sleep(1000);
		    
		    // press key 'r' and get the result from element
		    driver.findElement(By.id("target")).sendKeys("r");
		    Thread.sleep(1000);
		    resultString = driver.findElement(By.id("result")).getText();
		    System.out.println(resultString+ "\n\n PASSED");
		    Thread.sleep(1000);
		    
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
