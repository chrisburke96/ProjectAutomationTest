import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.asserts.SoftAssert;

import org.openqa.selenium.By;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;  
import org.openqa.selenium.interactions.Actions;


public class TestCase1 {
	
	// Create a reference of web driver. 
	public static WebDriver driver; 
	
	
  @BeforeTest
  public void setUp() {
	  // Get project directory
	  String dir = System.getProperty("user.dir");
	  
	  // Create path for chrome driver and set it to the driver object
	  String exepath = dir+ "\\resources\\webdriver\\chromedriver.exe";
	  System.setProperty("webdriver.chrome.driver", exepath);
	  
	  // Chrome driver object created and chrome window will open. 
	  driver=new ChromeDriver();  
	  
	  // Maximise browser window
	  driver.manage().window().maximize();	  
  }
  	
  	// Create a data provider to pass parameters through
	public class DP {
		 @DataProvider (name = "data-provider")
		 public Object[][] dpMethod() {
			
			 Object[][] myData = {{"tomsmith","password","Scenario 1"}, {"username","SuperSecretPassword!","Scenario 2"}, {"tomsmith","SuperSecretPassword!","Scenario 3"}};
			 return myData;
	}
  
  @Test (dataProvider = "data-provider")
  public void login(String reUsername, String rePassword, String scenario) {
	  
	  System.out.println("*** Running Test Case 1 - "+ scenario+ " ***");
	  
	  // Navigate to the website
	  driver.navigate().to("http://the-internet.herokuapp.com/"); 
	  
	  // Navigate to form authentication
	  WebElement FormAuthLink = driver.findElement(By.linkText("Form Authentication"));
	  Actions actionProvider = new Actions(driver);
	  actionProvider.moveToElement(FormAuthLink).build().perform();
	  FormAuthLink.click();
	  
	  // Get username field and type in username in the username field
	  WebElement usernameField = driver.findElement(By.id("username"));
	  usernameField.sendKeys(reUsername);
	  
	  // Get password field and type in password in the password field
	  WebElement passwordField = driver.findElement(By.id("password"));
	  passwordField.sendKeys(rePassword);
	  
	  // Get login button element and submit 
	  WebElement loginButton = driver.findElement( By.xpath("//button[@type = 'submit']"));
	  loginButton.click();
	  
	  // Declare variables for correct username and password
	  String actualUsername = "tomsmith";
	  String actualPassword = "SuperSecretPassword!";
	  
	  // Create a soft assert to generate tests for username and password
	  SoftAssert softAssert = new SoftAssert();
	  softAssert.assertTrue(reUsername.equals(actualUsername), "*** Incorrect Username ***");
	  softAssert.assertTrue(rePassword.equals(actualPassword), "*** Incorrect Password ***");
	  softAssert.assertAll();
  }
  
  @AfterTest
  public void tearDown() {
	  
	  // Declare variables checking if user is logged in. 
	  String expectedUrl= driver.getCurrentUrl();
	  String actualUrl = "http://the-internet.herokuapp.com/secure";
	  
	  // Log out functionality
	  if (expectedUrl.equals(actualUrl)) {
		  
		  System.out.println("*** Logged in Successfully ***");
		  
		  // Get logout button element
		  WebElement logoutButton = driver.findElement( By.xpath("//a[@href = '/logout']"));
		  logoutButton.click();
		  
		  // Test logout functionality. 
		  String testUrl= driver.getCurrentUrl();
		  System.out.println("Current Url - "+ testUrl+ " - Logout Successful");
		  
		  // Close chrome driver
		  driver.quit();
		} 
	  
	  // User did not log out. 
	  else {
		  System.out.println("*** Unable to log in ***");
		  // Close chrome driver
		  driver.quit();
		}
	  }
  
	} // Data provider
	
} // class
