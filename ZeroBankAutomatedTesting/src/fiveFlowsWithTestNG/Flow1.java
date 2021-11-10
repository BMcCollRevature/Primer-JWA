package fiveFlowsWithTestNG;

import static org.testng.Assert.assertEquals;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Flow1 {
	
	public WebDriver driver;
		
		@BeforeTest
		public void setUp() {
			// system path for browser web driver
			System.setProperty("webdriver.chrome.driver","C:\\SeleniumBrowserDrivers\\chromedriver.exe");
					
			//initialize web driver
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);				
			driver.get("http://zero.webappsecurity.com/");
			
			String actualTitle =driver.getTitle();
			String expectedTitle = "Zero - Personal Banking - Loans - Credit Cards";
		
			assertEquals(actualTitle, expectedTitle, "Title did not match!!! \n");
		}
		
		@Test
		public void logInTest() {
			driver.findElement(By.id("signin_button")).click();
			
			assertEquals(driver.getTitle(), "Zero - Log in","Login page title did not match!! \n" );
			
			driver.findElement(By.name("user_login")).sendKeys("username");
			driver.findElement(By.name("user_password")).sendKeys("password");
			
			//click on checkbox called remember me
			WebElement checkbox = driver.findElement(By.id("user_remember_me"));
			
				boolean isDisplayed =checkbox.isDisplayed();
				if(isDisplayed) {
					System.out.println("Checkbox is displayed");
					boolean isEnabled = checkbox.isEnabled();
					if(isEnabled) {
						System.out.println("Checkbox is enabled");
						boolean isChecked = checkbox.isSelected();
						if(!isChecked) {
							checkbox.click();
							System.out.println("Checked the checkbox");
						}else {
							System.out.println("Checkbox is already checked");
						}
					}else {
						System.out.println("Checkbox is not enabled");
					}
				}else {
					System.out.println("Checkbox is not displayed");
				}
				
			//click on sign in button
			driver.findElement(By.name("submit")).click();
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
			
			driver.findElement(By.id("details-button")).click();
			driver.findElement(By.id("proceed-link")).click();	
				
		}
		
		@Test
		public void newWebpage(){

			String pageHandle = driver.getWindowHandle();
			System.out.println(pageHandle);
			
			driver.findElement(By.cssSelector("#terms_of_use_link")).click();
			
			Set <String> handles = driver.getWindowHandles();
			
			for(String h: handles) {
				System.out.println(h);
				driver.switchTo().window(h);
			}
			
			assertEquals(driver.getTitle(), "Legal Information | Micro Focus");
	
		}
		
		@AfterTest
		public void cleanUp() {
			
			driver.close();
			driver.quit();
			
		}
}	
